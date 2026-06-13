package com.paczek224.cvaigenerator.service;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitUntilState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Renders HTML to PDF with headless Chromium via Playwright.
 *
 * <p>Playwright objects are not thread-safe and must be used from the single
 * thread that created them. We therefore keep a pool of {@link Worker}s, each
 * owning its own {@link Playwright}/{@link Browser} pinned to a dedicated
 * single-thread executor. {@link #render(String)} borrows a worker, runs the
 * job on that worker's thread, then returns it to the pool. The browser is
 * launched once and reused across requests.
 */
@Service
public class PdfRenderService implements AutoCloseable {

    private static final Logger log = LoggerFactory.getLogger(PdfRenderService.class);

    private static final String SIDEBAR_FIX_JS =
            "() => {"
            + "  const sb = document.querySelector('.cv-sidebar');"
            + "  if (!sb) return;"
            + "  const w = sb.offsetWidth;"
            + "  const c = getComputedStyle(sb).backgroundColor;"
            + "  const s = document.createElement('style');"
            + "  s.textContent = '.cv-doc-body{background:linear-gradient(to right,' + c + ' ' + w + 'px,#fff ' + w + 'px)!important}'"
            + "    + '.cv-sidebar{background:transparent!important}';"
            + "  document.head.appendChild(s);"
            + "}";

    private final int poolSize;
    private final BlockingQueue<Worker> pool = new LinkedBlockingQueue<>();
    private boolean started = false;

    public PdfRenderService(@Value("${app.pdf.pool-size:1}") int poolSize) {
        this.poolSize = Math.max(1, poolSize);
    }

    /** Lazily launches the Chromium worker pool on first use. */
    private synchronized void ensureStarted() {
        if (started) {
            return;
        }
        for (int i = 0; i < poolSize; i++) {
            Worker w = new Worker();
            w.start();
            pool.add(w);
        }
        started = true;
        log.info("PDF render pool started with {} Chromium worker(s)", poolSize);
    }

    public byte[] render(String html) {
        ensureStarted();
        Worker w;
        try {
            w = pool.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while waiting for a PDF worker", e);
        }
        try {
            return w.render(html);
        } finally {
            pool.add(w);
        }
    }

    @Override
    public synchronized void close() {
        if (!started) {
            return;
        }
        pool.forEach(Worker::close);
        pool.clear();
        started = false;
        log.info("PDF render pool stopped");
    }

    /** A single Chromium browser bound to its own thread. */
    private static final class Worker {

        private final ExecutorService exec = Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r, "pdf-worker");
            t.setDaemon(true);
            return t;
        });

        private Playwright playwright;
        private Browser browser;

        void start() {
            run(() -> {
                playwright = Playwright.create();
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                        .setHeadless(true)
                        // --no-sandbox / dev-shm are required to run Chromium in containers.
                        .setArgs(List.of("--no-sandbox", "--disable-dev-shm-usage")));
                return null;
            });
        }

        byte[] render(String html) {
            return run(() -> {
                BrowserContext ctx = browser.newContext();
                Page page = ctx.newPage();
                try {
                    page.setContent(html, new Page.SetContentOptions()
                            .setWaitUntil(WaitUntilState.NETWORKIDLE));
                    // Make sure web fonts have loaded before measuring/printing.
                    try {
                        page.evaluate("() => document.fonts && document.fonts.ready");
                    } catch (RuntimeException ignored) {
                        // older content without document.fonts — proceed anyway
                    }
                    // Mirror the client print fix: in print media a flex sidebar's
                    // background can stop short of the page bottom, so paint the
                    // sidebar colour as a full-height left band on the body instead.
                    page.evaluate(SIDEBAR_FIX_JS);
                    // @page in the document (preferCSSPageSize) drives the single-page size.
                    return page.pdf(new Page.PdfOptions()
                            .setPrintBackground(true)
                            .setPreferCSSPageSize(true));
                } finally {
                    page.close();
                    ctx.close();
                }
            });
        }

        void close() {
            try {
                run(() -> {
                    if (browser != null) {
                        browser.close();
                    }
                    if (playwright != null) {
                        playwright.close();
                    }
                    return null;
                });
            } catch (RuntimeException e) {
                log.warn("Error while closing PDF worker", e);
            } finally {
                exec.shutdownNow();
            }
        }

        private <T> T run(java.util.concurrent.Callable<T> task) {
            try {
                return exec.submit(task).get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException("PDF worker interrupted", e);
            } catch (java.util.concurrent.ExecutionException e) {
                Throwable cause = e.getCause();
                throw new RuntimeException("PDF worker task failed: " + cause.getMessage(), cause);
            }
        }
    }
}

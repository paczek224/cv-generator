package com.github.paczek224.tests.config;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PlaywrightContext {

    private static final ThreadLocal<Playwright> PLAYWRIGHT = new ThreadLocal<>();
    private static final ThreadLocal<Browser> BROWSER = new ThreadLocal<>();
    private static final ThreadLocal<Page> PAGE = new ThreadLocal<>();

    private final PlaywrightConfig config;

    public PlaywrightContext(PlaywrightConfig config) {
        this.config = config;
    }

    public void init() {
        Playwright playwright = Playwright.create();
        PLAYWRIGHT.set(playwright);

        var launchOptions = new BrowserType.LaunchOptions().setHeadless(config.isHeadless());
        Browser browser = selectBrowserType(playwright).launch(launchOptions);
        BROWSER.set(browser);

        var options = new Browser.NewPageOptions().setViewportSize(config.getBrowserWidth(), config.getBrowserHeight());
        Page page = browser.newPage(options);
        page.setDefaultTimeout(config.getTimeoutInMs());
        PAGE.set(page);
    }

    public Page getPage() {
        return PAGE.get();
    }

    public String getBaseUrl() {
        return config.getBaseUrl();
    }

    public void close() {

        Optional.ofNullable(PAGE.get())
                .ifPresent(page -> {
                    page.close();
                    PAGE.remove();
                });

        Optional.ofNullable(BROWSER.get())
                .ifPresent(browser -> {
                    browser.close();
                    BROWSER.remove();
                });

        Optional.ofNullable(PLAYWRIGHT.get())
                .ifPresent(playwright -> {
                    playwright.close();
                    PLAYWRIGHT.remove();
                });
    }

    private BrowserType selectBrowserType(Playwright playwright) {
        return switch (config.getBrowser().toLowerCase()) {
            case "firefox" -> playwright.firefox();
            case "webkit", "safari" -> playwright.webkit();
            default -> playwright.chromium();
        };
    }
}

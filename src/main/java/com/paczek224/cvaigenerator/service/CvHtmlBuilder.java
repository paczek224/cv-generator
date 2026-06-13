package com.paczek224.cvaigenerator.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;

/**
 * Assembles a standalone, print-ready HTML document from the CV markup rendered
 * by the browser. The same {@code styles.css} the live preview uses is inlined,
 * so headless Chrome produces a PDF identical to what the user sees on screen.
 */
@Component
public class CvHtmlBuilder {

    // Mirrors the font set loaded by index.html so any selected family resolves.
    private static final String FONTS_LINK =
            "https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700"
            + "&family=Poppins:wght@300;400;500;600;700"
            + "&family=Montserrat:wght@300;400;500;600;700"
            + "&family=Raleway:wght@300;400;500;600;700"
            + "&family=Lato:wght@300;400;700"
            + "&family=Nunito:wght@300;400;500;600;700&display=swap";

    private final String stylesCss;

    public CvHtmlBuilder() {
        this.stylesCss = readClasspath("static/css/styles.css");
    }

    /**
     * @param clientCvDocHtml outerHTML of the {@code .cv-doc} element from the client
     * @param heightMm        single-page height in mm (null → fall back to A4)
     * @param watermark       whether to stamp the "paczeklab.pl" watermark
     * @return a complete HTML document ready to hand to Chrome
     */
    public String build(String clientCvDocHtml, Double heightMm, boolean watermark) {
        Document doc = Jsoup.parseBodyFragment(clientCvDocHtml);
        doc.outputSettings().prettyPrint(false);

        Element cv = doc.selectFirst(".cv-doc");
        if (cv == null) {
            throw new IllegalArgumentException("Supplied HTML does not contain a .cv-doc element");
        }

        // Strip edit-mode artifacts and any scripts; re-stamp the watermark so the
        // server — not the client — decides whether it appears.
        cv.removeClass("editing");
        cv.select("[contenteditable]").removeAttr("contenteditable");
        cv.select("script").remove();
        cv.select(".cv-watermark").remove();
        if (watermark) {
            cv.append("<div class=\"cv-watermark\" aria-hidden=\"true\"></div>");
        }

        String pageRule = (heightMm != null && heightMm > 0)
                ? "@page{size:210mm " + mm(heightMm) + "mm;margin:0}"
                : "@page{size:A4;margin:0}";

        return "<!doctype html><html lang=\"en\"><head><meta charset=\"UTF-8\">"
                + "<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">"
                + "<link href=\"" + FONTS_LINK + "\" rel=\"stylesheet\">"
                + "<style>" + stylesCss + "</style>"
                + "<style>" + pageRule
                + "html,body{margin:0;padding:0;background:#fff}"
                + ".cv-doc{box-shadow:none!important;border:none!important;border-radius:0!important}"
                + "</style></head><body>" + cv.outerHtml() + "</body></html>";
    }

    private static String mm(double v) {
        return BigDecimal.valueOf(v).setScale(2, RoundingMode.HALF_UP).toPlainString();
    }

    private static String readClasspath(String path) {
        try (InputStream in = new ClassPathResource(path).getInputStream()) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Could not read " + path + " from classpath", e);
        }
    }
}

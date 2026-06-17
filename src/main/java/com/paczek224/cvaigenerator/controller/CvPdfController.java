package com.paczek224.cvaigenerator.controller;

import com.paczek224.cvaigenerator.dto.PdfRequest;
import com.paczek224.cvaigenerator.service.CvHtmlBuilder;
import com.paczek224.cvaigenerator.service.PdfRenderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/cv")
public class CvPdfController {

    private static final double MIN_HEIGHT_MM = 50;
    private static final double MAX_HEIGHT_MM = 5000;

    private final PdfRenderService renderer;
    private final CvHtmlBuilder htmlBuilder;

    @Value("${app.pdf.server-side:false}")
    private boolean serverPdfEnabled;

    @Value("${app.features.watermark:false}")
    private boolean     watermarkEnabled;

    public CvPdfController(PdfRenderService renderer, CvHtmlBuilder htmlBuilder) {
        this.renderer = renderer;
        this.htmlBuilder = htmlBuilder;
    }

    @PostMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> pdf(@RequestBody PdfRequest req) {
        if (!serverPdfEnabled) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Server-side PDF is disabled");
        }
        if (req.getHtml() == null || req.getHtml().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing CV html");
        }

        Double height = req.getHeightMm();
        if (height != null) {
            height = Math.max(MIN_HEIGHT_MM, Math.min(MAX_HEIGHT_MM, height));
        }

        final String fullHtml;
        try {
            fullHtml = htmlBuilder.build(req.getHtml(), height, shouldWatermark());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        final byte[] pdf;
        try {
            pdf = renderer.render(fullHtml);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "PDF rendering failed", e);
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"CV.pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    /**
     * Single decision point for whether the watermark is stamped. Today it
     * follows the global {@code app.features.watermark} flag; replace this with a
     * per-user check once payment is wired up (e.g. {@code return !user.hasPaid();}).
     */
    private boolean shouldWatermark() {
        return watermarkEnabled;
    }
}

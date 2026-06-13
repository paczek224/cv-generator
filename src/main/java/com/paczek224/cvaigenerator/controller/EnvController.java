package com.paczek224.cvaigenerator.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/env")
public class EnvController {

    @Value("${app.features.sample-data:false}")
    private boolean sampleDataEnabled;

    @Value("${app.features.watermark:false}")
    private boolean watermarkEnabled;

    @Value("${app.pdf.server-side:false}")
    private boolean serverPdfEnabled;

    @GetMapping("/features")
    public Map<String, Boolean> features() {
        return Map.of(
                "sampleData", sampleDataEnabled,
                "watermark", watermarkEnabled,
                "serverPdf", serverPdfEnabled
        );
    }
}

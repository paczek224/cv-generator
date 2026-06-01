package com.example.cvaigenerator.controller;

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

    @GetMapping("/features")
    public Map<String, Boolean> features() {
        return Map.of("sampleData", sampleDataEnabled);
    }
}

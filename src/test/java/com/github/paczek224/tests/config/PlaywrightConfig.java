package com.github.paczek224.tests.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "playwright")
public class PlaywrightConfig {

    private String baseUrl;
    private String browser;
    private int browserWidth;
    private int browserHeight;
    private boolean headless;
    private int timeoutInMs;
    private int longTimeoutInMs;
}

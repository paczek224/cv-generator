package com.github.paczek224.tests.e2e.hooks;

import com.github.paczek224.tests.config.PlaywrightContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.util.Objects;

@Slf4j
@AllArgsConstructor
public class Hooks {

    private static final String SEPARATOR = "------------------------------------------------";
    private final PlaywrightContext playwrightContext;

    @Before
    public void setUp() {
        playwrightContext.init();
    }

    @After
    public void tearDown(Scenario scenario) {
        log.info("{}: {} {}", scenario.getName(), scenario.getStatus(), SEPARATOR);
        if (scenario.isFailed() && Objects.nonNull(playwrightContext.getPage())) {
            Allure.addAttachment(scenario.getName(), "image/png", new ByteArrayInputStream(playwrightContext.getPage().screenshot()), "png");
        }
        playwrightContext.close();
    }
}

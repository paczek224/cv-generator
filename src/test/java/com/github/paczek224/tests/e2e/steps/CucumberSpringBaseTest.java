package com.github.paczek224.tests.e2e.steps;

import com.github.paczek224.tests.config.PlaywrightConfig;
import com.github.paczek224.tests.config.PlaywrightContext;
import com.github.paczek224.tests.e2e.assertions.ElementAssertions;
import com.github.paczek224.tests.page.AbstractPage;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest
public class CucumberSpringBaseTest {

    @Autowired
    PlaywrightConfig playwrightConfig;

    protected <V extends AbstractPage>ElementAssertions<V> assertThat(V page) {
        return ElementAssertions.assertThat(page, playwrightConfig);
    }
}

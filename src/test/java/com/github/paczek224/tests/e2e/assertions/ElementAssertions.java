package com.github.paczek224.tests.e2e.assertions;

import com.github.paczek224.tests.config.PlaywrightConfig;
import com.github.paczek224.tests.page.AbstractPage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.assertj.core.api.Assertions;

import java.util.function.Function;
import java.util.stream.Stream;

public class ElementAssertions<V extends AbstractPage> {

    private final V page;
    PlaywrightConfig playwrightConfig;

    private ElementAssertions(V page, PlaywrightConfig playwrightConfig) {
        this.page = page;
        this.playwrightConfig = playwrightConfig;
    }

    public static <V extends AbstractPage> ElementAssertions<V> assertThat(V page, PlaywrightConfig playwrightConfig) {
        return new ElementAssertions<>(page, playwrightConfig);
    }

    public ElementAssertions<V> elementHasValue(Function<V, Locator> getter, String expected) {
        PlaywrightAssertions.assertThat(getter.apply(page)).hasValue(expected);
        return this;
    }

    public ElementAssertions<V> containsText(AriaRole role, String... expected) {
        Locator byRole = page.getPage().getByRole(role);
        Stream.of(expected).forEach(text -> waitUntilThereIsAtLeastOneLocatorWithText(byRole, text));

        Assertions.assertThat(byRole.allInnerTexts())
                .map(String::trim)
                .contains(expected);

        return this;
    }

    private void waitUntilThereIsAtLeastOneLocatorWithText(Locator locator, String text) {
        PlaywrightAssertions.assertThat(locator.filter(new Locator.FilterOptions().setHasText(text)))
                .not()
                .hasCount(0);
    }

    public ElementAssertions<V> elementIsDisplayed(Function<V, Locator> getter) {
        PlaywrightAssertions.assertThat(getter.apply(page)).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(playwrightConfig.getLongTimeoutInMs()));
        return this;
    }
}

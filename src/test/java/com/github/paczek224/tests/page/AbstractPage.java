package com.github.paczek224.tests.page;

import com.github.paczek224.tests.config.PlaywrightContext;
import com.microsoft.playwright.Page;

public abstract class AbstractPage {

    protected final PlaywrightContext playwrightContext;

    protected AbstractPage(PlaywrightContext playwrightContext) {
        this.playwrightContext = playwrightContext;
    }

    public final void openPage() {
        playwrightContext.getPage().navigate(playwrightContext.getBaseUrl() + getPageUrl());
    }

    public Page getPage() {
        return playwrightContext.getPage();
    }

    protected abstract String getPageUrl();
}

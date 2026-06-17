package com.github.paczek224.tests.page.cv.generator.components;

import com.github.paczek224.tests.page.Component;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

@Getter
public class SocialMediaRowComponent extends Component {

    private final Locator nameInput;
    private final Locator urlInput;
    private final Locator removeButton;

    public SocialMediaRowComponent(Page page, int index) {
        super(page);
        this.nameInput = byTestId("social-name-" + index);
        this.urlInput = byTestId("social-url-" + index);
        this.removeButton = byTestId("social-remove-" + index);
    }

    public SocialMediaRowComponent fill(String name, String url) {
        nameInput.fill(name);
        urlInput.fill(url);
        return this;
    }

    public void remove() {
        removeButton.click();
    }
}

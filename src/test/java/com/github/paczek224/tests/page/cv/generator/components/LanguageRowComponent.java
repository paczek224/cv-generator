package com.github.paczek224.tests.page.cv.generator.components;

import com.github.paczek224.tests.page.Component;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

@Getter
public class LanguageRowComponent extends Component {

    private final Locator nameInput;
    private final Locator levelSelect;
    private final Locator removeButton;

    public LanguageRowComponent(Page page, int index) {
        super(page);
        this.nameInput = byTestId("lang-name-" + index);
        this.levelSelect = byTestId("lang-level-" + index);
        this.removeButton = byTestId("lang-remove-" + index);
    }

    public LanguageRowComponent fill(String name, String level) {
        nameInput.fill(name);
        levelSelect.selectOption(level);
        return this;
    }

    public void remove() {
        removeButton.click();
    }
}

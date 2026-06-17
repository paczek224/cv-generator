package com.github.paczek224.tests.page.cv.generator.components;

import com.github.paczek224.tests.page.Component;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

@Getter
public class SkillsComponent extends Component {

    private final Locator skillsTextarea;

    public SkillsComponent(Page page) {
        super(page);
        this.skillsTextarea = byTestId("textarea-skills");
    }

    public SkillsComponent fill(String skills) {
        skillsTextarea.fill(skills);
        return this;
    }
}

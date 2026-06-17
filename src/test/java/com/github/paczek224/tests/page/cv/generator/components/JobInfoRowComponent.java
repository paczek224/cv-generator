package com.github.paczek224.tests.page.cv.generator.components;

import com.github.paczek224.tests.page.Component;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

/**
 * A single work-experience (job) row of the generator form.
 */
@Getter
public class JobInfoRowComponent extends Component {

    private final Locator titleInput;
    private final Locator companyInput;
    private final Locator fromPicker;
    private final Locator toPicker;
    private final Locator currentJobCheckbox;
    private final Locator dutiesTextarea;
    private final Locator removeButton;

    public JobInfoRowComponent(Page page, int index) {
        super(page);
        this.titleInput = byTestId("work-title-" + index);
        this.companyInput = byTestId("work-company-" + index);
        this.fromPicker = byTestId("work-from-" + index);
        this.toPicker = byTestId("work-to-" + index);
        this.currentJobCheckbox = byTestId("work-current-" + index);
        this.dutiesTextarea = byTestId("work-duties-" + index);
        this.removeButton = byTestId("work-remove-" + index);
    }

    public JobInfoRowComponent fill(String title, String company, String duties) {
        titleInput.fill(title);
        companyInput.fill(company);
        dutiesTextarea.fill(duties);
        return this;
    }

    public JobInfoRowComponent setCurrentJob(boolean current) {
        setToggle(currentJobCheckbox, current);
        return this;
    }

    public JobInfoRowComponent selectFrom(int year, int month) {
        selectDate(fromPicker, year, month);
        return this;
    }

    public JobInfoRowComponent selectTo(int year, int month) {
        selectDate(toPicker, year, month);
        return this;
    }

    public void remove() {
        removeButton.click();
    }
}

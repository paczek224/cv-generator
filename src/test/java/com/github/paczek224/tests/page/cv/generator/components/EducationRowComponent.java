package com.github.paczek224.tests.page.cv.generator.components;

import com.github.paczek224.tests.page.Component;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

@Getter
public class EducationRowComponent extends Component {

    private final Locator schoolInput;
    private final Locator fromPicker;
    private final Locator toPicker;
    private final Locator removeButton;

    public EducationRowComponent(Page page, int index) {
        super(page);
        this.schoolInput = byTestId("edu-school-" + index);
        this.fromPicker = byTestId("edu-from-" + index);
        this.toPicker = byTestId("edu-to-" + index);
        this.removeButton = byTestId("edu-remove-" + index);
    }

    public EducationRowComponent fill(String school) {
        schoolInput.fill(school);
        return this;
    }

    public EducationRowComponent selectFrom(int year, int month) {
        selectDate(fromPicker, year, month);
        return this;
    }

    public EducationRowComponent selectTo(int year, int month) {
        selectDate(toPicker, year, month);
        return this;
    }

    public void remove() {
        removeButton.click();
    }
}

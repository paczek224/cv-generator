package com.github.paczek224.tests.page.cv.generator.components;

import com.github.paczek224.tests.page.Component;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

@Getter
public class CertificationRowComponent extends Component {

    private final Locator nameInput;
    private final Locator issuerInput;
    private final Locator datePicker;
    private final Locator removeButton;

    public CertificationRowComponent(Page page, int index) {
        super(page);
        this.nameInput = byTestId("cert-name-" + index);
        this.issuerInput = byTestId("cert-issuer-" + index);
        this.datePicker = byTestId("cert-date-" + index);
        this.removeButton = byTestId("cert-remove-" + index);
    }

    public CertificationRowComponent fill(String name, String issuer) {
        nameInput.fill(name);
        issuerInput.fill(issuer);
        return this;
    }

    public CertificationRowComponent selectDate(int year, int month) {
        selectDate(datePicker, year, month);
        return this;
    }

    public void remove() {
        removeButton.click();
    }
}

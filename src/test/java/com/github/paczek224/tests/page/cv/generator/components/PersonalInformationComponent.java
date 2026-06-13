package com.github.paczek224.tests.page.cv.generator.components;

import com.github.paczek224.tests.page.Component;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.paczek224.cvaigenerator.dto.CvRequest;
import lombok.Getter;

@Getter
public class PersonalInformationComponent extends Component {

    private final Locator firstNameInput;
    private final Locator lastNameInput;
    private final Locator positionInput;
    private final Locator addressInput;
    private final Locator phoneInput;
    private final Locator emailInput;
    private final Locator phoneError;
    private final Locator emailError;

    public PersonalInformationComponent(Page page) {
        super(page);
        this.firstNameInput = byTestId("input-first-name");
        this.lastNameInput = byTestId("input-last-name");
        this.positionInput = byTestId("input-position");
        this.addressInput = byTestId("input-address");
        this.phoneInput = byTestId("input-phone");
        this.emailInput = byTestId("input-email");
        this.phoneError = byTestId("error-phone");
        this.emailError = byTestId("error-email");
    }

    public PersonalInformationComponent fill(CvRequest data) {
        firstNameInput.fill(data.getFirstName());
        lastNameInput.fill(data.getLastName());
        positionInput.fill(data.getPosition());
        addressInput.fill(data.getAddress());
        phoneInput.fill(data.getPhone());
        emailInput.fill(data.getEmail());
        return this;
    }
}

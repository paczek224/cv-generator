package com.github.paczek224.tests.page.cv.generator;

import com.github.paczek224.tests.config.PlaywrightContext;
import com.github.paczek224.tests.page.AbstractPage;
import com.github.paczek224.tests.page.cv.generator.components.CertificationRowComponent;
import com.github.paczek224.tests.page.cv.generator.components.EducationRowComponent;
import com.github.paczek224.tests.page.cv.generator.components.JobInfoRowComponent;
import com.github.paczek224.tests.page.cv.generator.components.LanguageRowComponent;
import com.github.paczek224.tests.page.cv.generator.components.PersonalInformationComponent;
import com.github.paczek224.tests.page.cv.generator.components.SkillsComponent;
import com.github.paczek224.tests.page.cv.generator.components.SocialMediaRowComponent;
import com.microsoft.playwright.Locator;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

/**
 * Page object for the CV generator page ({@code /generator.html}).
 * <p>
 * Section behaviour lives in dedicated components. {@code *Row} components are created per
 * index — each instance is bound to its own {@code data-testid} suffix so every action runs
 * against that specific row. The dynamic list sections render a 1-based index matching the
 * badge shown in the UI.
 */
@Component
public class GeneratorPage extends AbstractPage {

    public GeneratorPage(PlaywrightContext playwrightContext) {
        super(playwrightContext);
    }

    @Override
    protected String getPageUrl() {
        return "/generator.html";
    }

    private Locator byTestId(String testId) {
        return getPage().getByTestId(testId);
    }

    // ---------------------------------------------------------------------
    // Header
    // ---------------------------------------------------------------------

    public Locator homeLink() {
        return byTestId("link-home");
    }

    public Locator loadSampleButton() {
        return byTestId("btn-load-sample");
    }

    public Locator form() {
        return byTestId("form-cv");
    }

    // ---------------------------------------------------------------------
    // Section components
    // ---------------------------------------------------------------------

    public PersonalInformationComponent personalInformation() {
        return new PersonalInformationComponent(getPage());
    }

    public SkillsComponent skills() {
        return new SkillsComponent(getPage());
    }

    public SocialMediaRowComponent socialMediaRow(int index) {
        return new SocialMediaRowComponent(getPage(), index);
    }

    public JobInfoRowComponent jobInfoRow(int index) {
        return new JobInfoRowComponent(getPage(), index);
    }

    public EducationRowComponent educationRow(int index) {
        return new EducationRowComponent(getPage(), index);
    }

    public CertificationRowComponent certificationRow(int index) {
        return new CertificationRowComponent(getPage(), index);
    }

    public LanguageRowComponent languageRow(int index) {
        return new LanguageRowComponent(getPage(), index);
    }

    // ---------------------------------------------------------------------
    // Add-row actions
    // ---------------------------------------------------------------------

    public Locator socialList() {
        return byTestId("social-list");
    }

    public GeneratorPage addSocialMedia() {
        byTestId("btn-add-social-link").click();
        return this;
    }

    public Locator workList() {
        return byTestId("work-list");
    }

    public GeneratorPage addJobInfo() {
        byTestId("btn-add-work").click();
        return this;
    }

    public Locator eduList() {
        return byTestId("edu-list");
    }

    public GeneratorPage addEducation() {
        byTestId("btn-add-education").click();
        return this;
    }

    public Locator certList() {
        return byTestId("cert-list");
    }

    public GeneratorPage addCertification() {
        byTestId("btn-add-cert").click();
        return this;
    }

    public Locator langList() {
        return byTestId("lang-list");
    }

    public GeneratorPage addLanguage() {
        byTestId("btn-add-language").click();
        return this;
    }

    // ---------------------------------------------------------------------
    // Appearance — photo
    // ---------------------------------------------------------------------

    public Locator photoFileInput() {
        return byTestId("input-photo-file");
    }

    public Locator photoRemoveButton() {
        return byTestId("btn-photo-remove");
    }

    public GeneratorPage uploadPhoto(Path file) {
        photoFileInput().setInputFiles(file);
        return this;
    }

    public GeneratorPage removePhoto() {
        photoRemoveButton().click();
        return this;
    }

    // ---------------------------------------------------------------------
    // Generate options
    // ---------------------------------------------------------------------

    public Locator jobOfferInput() {
        return byTestId("input-job-offer");
    }

    public Locator languageSelect() {
        return byTestId("select-language");
    }

    public Locator enhanceCvCheckbox() {
        return byTestId("checkbox-enhance-cv");
    }

    public GeneratorPage fillJobOffer(String jobOffer) {
        jobOfferInput().fill(jobOffer);
        return this;
    }

    public GeneratorPage selectOutputLanguage(String value) {
        languageSelect().selectOption(value);
        return this;
    }

    public GeneratorPage setEnhanceCv(boolean enabled) {
        // The toggle's checkbox is visually collapsed (0x0), so the click is dispatched on the
        // wrapping label instead. Only act when the current state differs from the desired one.
        Locator checkbox = enhanceCvCheckbox();
        if (checkbox.isChecked() != enabled) {
            checkbox.locator("xpath=ancestor::label[1]").click();
        }
        return this;
    }

    // ---------------------------------------------------------------------
    // Submit & result
    // ---------------------------------------------------------------------

    public Locator generateButton() {
        return byTestId("btn-generate");
    }

    public GeneratorPage generate() {
        generateButton().click();
        return this;
    }

    public Locator editFormButton() {
        return byTestId("btn-edit-form");
    }

    public Locator editTextButton() {
        return byTestId("btn-edit-text");
    }

    public Locator downloadPdfButton() {
        return byTestId("btn-download-pdf");
    }

    public Locator getGeneratedCv() {
        return byTestId("cv-doc");
    }

    public Locator getEditCvButton() {
        return byTestId("btn-edit-form");
    }

    public Locator getDownloadPdfButton() {
        return byTestId("btn-download-pdf");
    }

    public Locator getEditTextButton() {
        return byTestId("btn-edit-text");
    }
}

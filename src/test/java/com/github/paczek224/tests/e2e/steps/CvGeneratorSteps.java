package com.github.paczek224.tests.e2e.steps;

import com.github.paczek224.tests.e2e.data.CvDataFactory;
import com.github.paczek224.tests.page.cv.generator.GeneratorPage;
import com.github.paczek224.tests.page.cv.generator.components.CertificationRowComponent;
import com.github.paczek224.tests.page.cv.generator.components.EducationRowComponent;
import com.github.paczek224.tests.page.cv.generator.components.JobInfoRowComponent;
import com.paczek224.cvaigenerator.dto.CvRequest;
import com.paczek224.cvaigenerator.dto.CvRequest.CertEntry;
import com.paczek224.cvaigenerator.dto.CvRequest.EducationEntry;
import com.paczek224.cvaigenerator.dto.CvRequest.LangEntry;
import com.paczek224.cvaigenerator.dto.CvRequest.SocialLinkEntry;
import com.paczek224.cvaigenerator.dto.CvRequest.WorkEntry;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.github.paczek224.tests.e2e.assertions.ElementAssertions.assertThat;

@AllArgsConstructor
public class CvGeneratorSteps extends CucumberSpringBaseTest{

    private final GeneratorPage generatorPage;
    private final CvDataFactory cvDataFactory;

    @Given("user is on the cv generator page")
    public void userIsOnTheCvGeneratorPage() {
        generatorPage.openPage();
    }

    @When("user fills out the form with default data")
    public void userFillsOutTheFormWithDefaultData() {
        CvRequest data = cvDataFactory.getDefaultCvData();

        fillPersonalInfo(data);
        fillSocialLinks(data.getSocialLinks());
        fillWorkHistory(data.getWorkHistory());
        fillEducation(data.getEducationList());
        fillSkills(data);
        fillCertifications(data.getCertifications());
        fillLanguages(data.getLanguages());
        fillGenerateOptions(data);
    }

    @When("I click generate")
    public void iClickGenerate() {
        generatorPage.generate();
    }

    @Then("Cv is correctly generated")
    public void cvIsCorrectlyGenerated() {
        assertThat(generatorPage).elementIsDisplayed(GeneratorPage::getGeneratedCv);
    }

    @Then("Action buttons are displayed")
    public void actionButtonsAreDisplayed() {
        assertThat(generatorPage)
                .elementIsDisplayed(GeneratorPage::getEditCvButton)
                .elementIsDisplayed(GeneratorPage::getEditTextButton)
                .elementIsDisplayed(GeneratorPage::getDownloadPdfButton);
    }

    private void fillGenerateOptions(CvRequest data) {
        generatorPage
                .fillJobOffer(data.getJobOffer())
                .selectOutputLanguage(toSelectableLanguage(data.getLanguage()))
                .setEnhanceCv(data.isEnhanceCv());
    }

    private void fillPersonalInfo(CvRequest data) {
        generatorPage.personalInformation().fill(data);
    }

    private void fillSkills(CvRequest data) {
        generatorPage.skills().fill(String.join("\n", data.getSkills()));
    }

    private void fillSocialLinks(List<SocialLinkEntry> links) {
        for (int i = 0; i < links.size(); i++) {
            if (i > 0) {
                generatorPage.addSocialMedia();
            }
            SocialLinkEntry entry = links.get(i);
            generatorPage.socialMediaRow(i + 1).fill(entry.getName(), entry.getUrl());
        }
    }

    private void fillWorkHistory(List<WorkEntry> entries) {
        for (int i = 0; i < entries.size(); i++) {
            if (i > 0) {
                generatorPage.addJobInfo();
            }
            WorkEntry entry = entries.get(i);
            JobInfoRowComponent row = generatorPage.jobInfoRow(i + 1)
                    .fill(entry.getTitle(), entry.getCompanyName(), entry.getDuties());
            row.selectFrom(year(entry.getFrom()), month(entry.getFrom()));
            if (entry.isCurrentJob()) {
                row.setCurrentJob(true);
            } else {
                row.selectTo(year(entry.getTo()), month(entry.getTo()));
            }
        }
    }

    private void fillEducation(List<EducationEntry> entries) {
        for (int i = 0; i < entries.size(); i++) {
            if (i > 0) {
                generatorPage.addEducation();
            }
            EducationEntry entry = entries.get(i);
            EducationRowComponent row = generatorPage.educationRow(i + 1).fill(entry.getSchool());
            row.selectFrom(year(entry.getFrom()), month(entry.getFrom()));
            row.selectTo(year(entry.getTo()), month(entry.getTo()));
        }
    }

    private void fillCertifications(List<CertEntry> entries) {
        for (int i = 0; i < entries.size(); i++) {
            if (i > 0) {
                generatorPage.addCertification();
            }
            CertEntry entry = entries.get(i);
            CertificationRowComponent row = generatorPage.certificationRow(i + 1)
                    .fill(entry.getName(), entry.getIssuer());
            row.selectDate(year(entry.getDate()), month(entry.getDate()));
        }
    }

    private void fillLanguages(List<LangEntry> entries) {
        for (int i = 0; i < entries.size(); i++) {
            if (i > 0) {
                generatorPage.addLanguage();
            }
            LangEntry entry = entries.get(i);
            generatorPage.languageRow(i + 1).fill(entry.getName(), entry.getLevel());
        }
    }

    private int year(String value) {
        return Integer.parseInt(value.trim().split("-")[0]);
    }

    private int month(String value) {
        return Integer.parseInt(value.trim().split("-")[1]);
    }

    private String toSelectableLanguage(String language) {
        return "pl".equals(language) ? "pol" : language;
    }
}

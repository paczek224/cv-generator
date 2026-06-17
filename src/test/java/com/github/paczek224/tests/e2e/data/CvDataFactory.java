package com.github.paczek224.tests.e2e.data;

import com.paczek224.cvaigenerator.dto.CvRequest;
import com.paczek224.cvaigenerator.dto.CvRequest.CertEntry;
import com.paczek224.cvaigenerator.dto.CvRequest.EducationEntry;
import com.paczek224.cvaigenerator.dto.CvRequest.LangEntry;
import com.paczek224.cvaigenerator.dto.CvRequest.SocialLinkEntry;
import com.paczek224.cvaigenerator.dto.CvRequest.WorkEntry;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CvDataFactory {

    public CvRequest getDefaultCvData() {
        return CvRequest.builder()
                .firstName("Łukasz")
                .lastName("Pączek")
                .position("Test Automation Engineer")
                .address("Warsaw, Poland")
                .phone("796-457-984")
                .email("paczeklukasz90@gmail.com")
                .socialLinks(List.of(
                        socialLink("LinkedIn", "linkedin.com/in/Lukasz-Paczek"),
                        socialLink("GitHub", "https://github.com/paczek224/spring-rest-assured-playground")))
                .workHistory(List.of(
                        work("Senior QA Lead", "Sii Poland, Rzeszów, Polska", "2019-08", null, true,
                                "fintech projects, QA, Test automation, Test management, API testing, "
                                        + "UI testing, UAT, Manual testing, CI/CD"),
                        work("Quality Assurance Specialist", "Makeitright sp. z o.o", "2018-09", "2019-08", false,
                                "logistic projects, Selenium testing, SAFE framework, Jenkins, CI/CD"),
                        work("Quality Assurance Specialist", "PGS Software S.A.", "2017-03", "2018-09", false,
                                "ecommerce projects, Scrum/agile/Kanban, Manual testing, Accessibility testing"),
                        work("Software Tester", "SoftSystem Sp. z o.o", "2015-11", "2017-03", false,
                                "medical projects, Manual Testing, Regression Testing, SOAP testing")))
                .educationList(List.of(
                        education("WSiZ – Big Data, Rzeszów", "2025-03", "2026-02"),
                        education("University of Rzeszów – Political Science", "2011-10", "2014-07"),
                        education("Tadeusz Rejtan Technical College – Economy", "2006-09", "2010-06")))
                .skills(List.of(
                        "Java EXPERT",
                        "Spring ADVANCED",
                        "Selenium / Selenium Grid MASTER",
                        "Playwright EXPERT",
                        "TestNG / JUnit 5 EXPERT",
                        "Cucumber ADVANCED",
                        "Rest Assured EXPERT",
                        "SQL INTERMEDIATE",
                        "Docker ADVANCED",
                        "OpenShift INTERMEDIATE",
                        "Jenkins / GitHub Actions ADVANCED",
                        "TestContainers ADVANCED",
                        "WireMock INTERMEDIATE",
                        "Kafka INTERMEDIATE",
                        "Elasticsearch / Kibana BEGINNER"))
                .certifications(List.of(
                        cert("Cisco Python Essentials 1", "NetAcad.com", "2025-06"),
                        cert("ISTQB Foundation Level", "SJSI", "2017-12"),
                        cert("ISTQB Agile Tester", "SJSI", "2017-12")))
                .languages(List.of(
                        language("Polish", "Native"),
                        language("English", "B2"),
                        language("German", "A1")))
                .jobOffer("Senior Test Automation Engineer")
                .enhanceCv(true)
                .language("eng")
                .build();
    }

    private SocialLinkEntry socialLink(String name, String url) {
        SocialLinkEntry entry = new SocialLinkEntry();
        entry.setName(name);
        entry.setUrl(url);
        return entry;
    }

    private WorkEntry work(String title, String companyName, String from, String to,
                           boolean currentJob, String duties) {
        WorkEntry entry = new WorkEntry();
        entry.setTitle(title);
        entry.setCompanyName(companyName);
        entry.setFrom(from);
        entry.setTo(to);
        entry.setCurrentJob(currentJob);
        entry.setDuties(duties);
        return entry;
    }

    private EducationEntry education(String school, String from, String to) {
        EducationEntry entry = new EducationEntry();
        entry.setSchool(school);
        entry.setFrom(from);
        entry.setTo(to);
        return entry;
    }

    private CertEntry cert(String name, String issuer, String date) {
        CertEntry entry = new CertEntry();
        entry.setName(name);
        entry.setIssuer(issuer);
        entry.setDate(date);
        return entry;
    }

    private LangEntry language(String name, String level) {
        LangEntry entry = new LangEntry();
        entry.setName(name);
        entry.setLevel(level);
        return entry;
    }
}

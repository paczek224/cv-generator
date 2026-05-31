package com.example.cvaigenerator.dto;

import dev.langchain4j.model.output.structured.Description;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Description("""
        Describes user Job info, position, company, when he occupied this position and what duties he had and what skills he was using
        example:
        Senior QA Lead                                                                                  2022-08-present
        Sii Poland, Rzeszów, Polska
        Company name.
        - Working with fund management systems
        - Planning and estimation of testing activities
        - Designing and developing automated test scripts
        - Test automation using Java 17, Spring, Playwright, Selenium, Selenium Grid, TestNG, and Cucumber
        - API testing with Rest Assured
        - Test management using QTest
        - Continuous integration and delivery with Jenkins
        - Managing a team of 4 automation testers
        - Migrating test framework from Selenium to Playwright
        """)
@Getter
@ToString
public class JobInfo {

    private String title;
    private String companyName;
    private List<String> duties;
    private String from;
    private String to;
    private boolean currentJob;
}

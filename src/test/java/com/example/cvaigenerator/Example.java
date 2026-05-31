package com.example.cvaigenerator;

import com.example.cvaigenerator.dto.CvResponse;
import com.example.cvaigenerator.service.CvGeneratorAiClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class Example extends CvAiGeneratorApplicationTests{

    @Autowired
    private CvGeneratorAiClient client;

    @Test
    void test() {
        String dataInput = """
                Łukasz Pączek, Test automation engineer
                ADDRESS
                Warsaw, Poland
                
                LINKEDIN
                linkedin.com/in/Lukasz-Paczek
              
                PHONE
                796-457-984
                
                EMAIL
                paczeklukasz90@gmail.com
                
                GITHUB
                https://github.com/paczek224/spring-rest-assured-playground
                
                WORK HISTORY:
                1) current job: Senior QA Lead
                started: 2019-08
                company: Sii Poland, Rzeszów, Polska
                duties: fintech projects, Qa, Test automation, Test management, API testing, UI testing, UAT, Manual testing, CI/CD
                
                2) Quality Assurance Specialist
                company: Makeitright sp. z o.o
                hired: 2018-09- 2019-08
                logistic projects, Selenium testing, SAFE framework, Jenkins, CI/CD
                
                3) Quality Assurance Specialist
                company: PGS Software S.A.
                hired: 2017-03- 2018-09
                ecommerce projects, Scrum/agile/Kanban, Manual testing, Accessibility testing
                
                4) Software Tester
                company: SoftSystem Sp. z o.o
                hired: 2015-11- 2017-03
                medical projects, Manual Testing, Regression Testing, SOAP testing
                
                EDUCATION:
                WSiZ - Big data, Rzeszów                 2025-03- 2026-02
                Professional English School                2016-01- 2018-01
                University of Rzeszow  – Political Science                2011-10- 2014-07
                Tadeusz Rejtan Technical College - Economy                2006-09- 2010-06
                
                SKILLS
                Java
                Spring
                Selenium / Selenium Grid
                Playwright
                TestNG / JUnit 5
                Cucumber
                Rest Assured
                SQL
                Continuous integration / Git / Jenkins / GitLab / GitHub Actions
                Docker
                OpenShift
                TestContainers
                WireMock
                Pact
                Prometheus and Grafana
                Test framework development and maintenance
                Scrum, Kanban, SAFE, Waterfall
                API testing, Web application testing, Manual testing
                Kafka
                Elasticsearch, Kibana
                
                CERTIFICATES:
                NetAcad.com  - Cisco Python Essentials 1 - 2025-06
                SJSI - ISQTB Foundation Level - 2017-12
                SJSI - ISTQB Agile Tester - 2017-12
                """;


        CvResponse cvResponse = client.generateStructuredCv(dataInput, "Test automation engineer", true, "eng");
        System.out.println(cvResponse);
    }
}

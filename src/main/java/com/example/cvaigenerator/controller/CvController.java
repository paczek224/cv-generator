package com.example.cvaigenerator.controller;

import com.example.cvaigenerator.dto.CvRequest;
import com.example.cvaigenerator.dto.CvResponse;
import com.example.cvaigenerator.service.CvGeneratorAiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cv")
public class CvController {

    @Autowired
    private CvGeneratorAiClient client;

    @PostMapping("/generate")
    public CvResponse generate(@RequestBody CvRequest req) {
        return client.generateStructuredCv(
                buildUserData(req),
                req.getJobOffer(),
                req.isEnhanceCv(),
                req.getLanguage() != null ? req.getLanguage() : "eng"
        );
    }

    private String buildUserData(CvRequest req) {
        var sb = new StringBuilder();

        sb.append(req.getFirstName()).append(" ").append(req.getLastName());
        if (notBlank(req.getPosition())) {
            sb.append(", ").append(req.getPosition());
        }
        sb.append("\n\n");

        if (notBlank(req.getAddress())) {
            sb.append("ADDRESS\n").append(req.getAddress()).append("\n\n");
        }
        if (notBlank(req.getPhone())) {
            sb.append("PHONE\n").append(req.getPhone()).append("\n\n");
        }
        if (notBlank(req.getEmail())) {
            sb.append("EMAIL\n").append(req.getEmail()).append("\n\n");
        }

        if (req.getSocialLinks() != null) {
            for (var link : req.getSocialLinks()) {
                if (notBlank(link.getName())) {
                    sb.append(link.getName().toUpperCase()).append("\n");
                    sb.append(link.getUrl()).append("\n\n");
                }
            }
        }

        if (req.getWorkHistory() != null && !req.getWorkHistory().isEmpty()) {
            sb.append("WORK HISTORY:\n");
            int i = 1;
            for (var job : req.getWorkHistory()) {
                sb.append(i++).append(") ");
                if (job.isCurrentJob()) sb.append("current job: ");
                sb.append(job.getTitle()).append("\n");
                if (job.isCurrentJob()) {
                    sb.append("started: ").append(job.getFrom()).append("\n");
                } else {
                    sb.append("hired: ").append(job.getFrom()).append("- ").append(job.getTo()).append("\n");
                }
                sb.append("company: ").append(job.getCompanyName()).append("\n");
                if (notBlank(job.getDuties())) {
                    sb.append(job.getDuties()).append("\n");
                }
                sb.append("\n");
            }
        }

        if (req.getEducationList() != null && !req.getEducationList().isEmpty()) {
            sb.append("EDUCATION:\n");
            for (var edu : req.getEducationList()) {
                sb.append(edu.getSchool())
                        .append("    ").append(edu.getFrom())
                        .append("- ").append(edu.getTo()).append("\n");
            }
            sb.append("\n");
        }

        if (req.getSkills() != null && !req.getSkills().isEmpty()) {
            sb.append("SKILLS\n");
            req.getSkills().forEach(s -> sb.append(s).append("\n"));
            sb.append("\n");
        }

        if (req.getCertifications() != null && !req.getCertifications().isEmpty()) {
            sb.append("CERTIFICATES:\n");
            for (var cert : req.getCertifications()) {
                sb.append(cert.getIssuer()).append(" - ")
                        .append(cert.getName()).append(" - ")
                        .append(cert.getDate()).append("\n");
            }
        }

        return sb.toString();
    }

    private boolean notBlank(String s) {
        return s != null && !s.isBlank();
    }
}

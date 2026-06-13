package com.paczek224.cvaigenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CvRequest {

    private String firstName;
    private String lastName;
    private String position;
    private String address;
    private String phone;
    private String email;
    private List<SocialLinkEntry> socialLinks;
    private List<WorkEntry> workHistory;
    private List<EducationEntry> educationList;
    private List<String> skills;
    private List<CertEntry> certifications;
    private List<LangEntry> languages;
    private String jobOffer;
    private boolean enhanceCv;
    private String language = "eng";

    @Data
    public static class SocialLinkEntry {
        private String name;
        private String url;
    }

    @Data
    public static class WorkEntry {
        private String title;
        private String companyName;
        private String from;
        private String to;
        private boolean currentJob;
        private String duties;
    }

    @Data
    public static class EducationEntry {
        private String school;
        private String from;
        private String to;
    }

    @Data
    public static class CertEntry {
        private String name;
        private String issuer;
        private String date;
    }

    @Data
    public static class LangEntry {
        private String name;
        private String level;
    }
}

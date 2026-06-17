package com.paczek224.cvaigenerator.dto;

import dev.langchain4j.model.output.structured.Description;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Description("""
        Represents user cv model, with a elegant and professional summary
        Use concise and professional language, try to be as attractive to the jobOffer as much as you can
        Very important: Summary needs to be written from 3rd person perspective so 'He is' instead of 'I am' etc
        """)
@Getter
@ToString
public class CvResponse {

    private String firstName;
    private String lastName;
    private String position;
    private String summary;
    private PersonalInfo personalInfo;
    private List<JobInfo> jobInfos;
    private List<Skill> skills;
    private List<Language> languages;
    private List<Education> educations;
    private List<Certification> certifications;
    private String footerConsent;
}

package com.example.cvaigenerator.dto;

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

    String firstName;
    String lastName;
    String position;
    String summary;
    PersonalInfo personalInfo;
    List<JobInfo> jobInfos;
    List<Skill> skills;
    List<Education> educations;
    List<Certification> certifications;
}

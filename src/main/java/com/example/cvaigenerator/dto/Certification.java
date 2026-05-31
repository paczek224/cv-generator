package com.example.cvaigenerator.dto;

import dev.langchain4j.model.output.structured.Description;
import lombok.Getter;
import lombok.ToString;

@Description("""
        Describing user certifications date format 2017-12
        example:
        name=ISTQB Foundation Level
        issuer=SJSI
        date=2017-12
        """)
@Getter
@ToString
public class Certification {

    private String name;
    private String issuer;
    private String date;
}

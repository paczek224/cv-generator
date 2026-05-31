package com.example.cvaigenerator.dto;

import dev.langchain4j.model.output.structured.Description;
import lombok.Getter;
import lombok.ToString;

@Description("""
        Describes user education
        dateformat 2011-10
        
        example:
        school=Tadeusz Rejtan Technical College - Economy
        from=2011-10
        to=2014-07
        """)
@Getter
@ToString
public class Education {

    String school;
    String from;
    String to;
}

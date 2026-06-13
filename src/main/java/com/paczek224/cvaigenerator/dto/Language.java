package com.paczek224.cvaigenerator.dto;

import dev.langchain4j.model.output.structured.Description;
import lombok.Getter;
import lombok.ToString;

@Description("""
        Describes the language user can speak
        Level: fom lowest to highest: A1, A2, B1, B2, C1, C2
        example:
        - English C2
        - German B1
        """)
@Getter
@ToString
public class Language {

    private String language;
    private String level;
}

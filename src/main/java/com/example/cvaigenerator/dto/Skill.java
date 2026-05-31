package com.example.cvaigenerator.dto;

import dev.langchain4j.model.output.structured.Description;
import lombok.Getter;
import lombok.ToString;

@Description("""
        Describes the skill user has with the level, min level 1, max level 5
        example:
        - Java 5/5
        """)
@Getter
@ToString
public class Skill {

    String skill;
    String level;
}

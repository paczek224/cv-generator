package com.paczek224.cvaigenerator.dto;

import dev.langchain4j.model.output.structured.Description;
import lombok.Getter;
import lombok.ToString;

@Description("""
        Describes the skill user has with the level, min level 1, max level 5
        Use SkillLevel enum values: BEGINNER, INTERMEDIATE, ADVANCED, EXPERT, MASTER
        example:
        - Java MASTER
        - Selenium EXPERT
        """)
@Getter
@ToString
public class Skill {

    private String skill;
    private SkillLevel level;
}

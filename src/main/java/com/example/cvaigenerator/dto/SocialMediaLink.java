package com.example.cvaigenerator.dto;


import dev.langchain4j.model.output.structured.Description;
import lombok.Getter;
import lombok.ToString;

@Description("""
        Describes the user social media
        example:
        LINKEDIN
        linkedin.com/in/Lukasz-Paczek
        
        GITHUB
        https://github.com/paczek224/
        """)
@Getter
@ToString
public class SocialMediaLink {

    String name;
    String link;
}

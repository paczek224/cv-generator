package com.paczek224.cvaigenerator.dto;

import dev.langchain4j.model.output.structured.Description;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Description("Describes user personal information and his social media links")
@Getter
@ToString
public class PersonalInfo {

    private String address;
    private String phone;
    private String email;
    private List<SocialMediaLink> socialMediaLinks;
}

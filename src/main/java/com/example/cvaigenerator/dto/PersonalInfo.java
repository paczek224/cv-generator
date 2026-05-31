package com.example.cvaigenerator.dto;


import dev.langchain4j.model.output.structured.Description;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Description("Describes user personal information and his social media links")
@Getter
@ToString
public class PersonalInfo {

    String address;
    String phone;
    String email;
    List<SocialMediaLink> socialMediaLinks;
}

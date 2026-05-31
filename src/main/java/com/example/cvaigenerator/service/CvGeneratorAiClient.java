package com.example.cvaigenerator.service;

import com.example.cvaigenerator.dto.CvResponse;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface CvGeneratorAiClient {

    @SystemMessage("""
            You are a professional recruiter. Your task is to create a CV.
            VERY IMPORTANT: If enhanceCv is true (value: {{enhanceCv}}), improve phrasing and highlight achievements — but do NOT invent facts.
            VERY IMPORTANT: Generate the entire document content in the following language: {{language}}.
            VERY IMPORTANT: Pay attention to the skill level that user sets
            skill level can be passed in scale from 1 to 5 or by SkillLevel enum
            VERY IMPORTANT: Group related skills together
            The generated CV must pass potential AI filters and be attractive to a potential employer — it should stand out from other CVs.
            Adjust phrasing and formatting to the recruitment standards applicable for that language.
            At the end of every generated response, append a footer
            containing a personal data processing consent clause (GDPR) in language {{language}}.
            Separate it from the main content with a horizontal line or a
            blank line, and prefix it with the label "Consent:".
            """)
    @UserMessage("""
            Here is my raw data: {{userData}}
            Here is the job offer: {{jobOffer}}
            """)
    CvResponse generateStructuredCv(
            @V("userData") String userData,
            @V("jobOffer") String jobOffer,
            @V("enhanceCv") boolean enhanceCv,
            @V("language") String language
    );
}

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
            VERY IMPORTANT: Depending on the {{enhanceCv}} parameter, enhance the CV with attractive additions, but only based on the user-provided data and the target position.
            VERY IMPORTANT: Generate the entire document content in the following language: {{language}}.
            The generated CV must pass potential AI filters and be attractive to a potential employer — it should stand out from other CVs.
            Adjust phrasing and formatting to the recruitment standards applicable for that language.
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

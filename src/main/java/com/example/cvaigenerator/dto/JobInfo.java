package com.example.cvaigenerator.dto;

import dev.langchain4j.model.output.structured.Description;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Description("""
        Describes user Job info, position, company, when he occupied this position and what duties he had and what skills he was using
        Highlight concrete achievements and measurable results accomplished in this role. Prioritize accomplishments over routine responsibilities.
        Base every statement strictly on the provided facts — do not invent, exaggerate, or fabricate any metrics, outcomes,
        or experience. If quantitative data is unavailable, describe the achievement qualitatively without inventing numbers
        """)
@Getter
@ToString
public class JobInfo {

    private String title;
    private String companyName;
    private List<String> duties;
    private String from;
    private String to;
    private boolean currentJob;
}

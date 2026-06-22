package com.paczek224.cvaigenerator.entity;

import com.paczek224.cvaigenerator.dto.CvResponse;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@ToString
@Document(collection = "cv_responses")
public class CvDocument {

    @Id
    private String id;
    private Instant createdAt;
    private CvResponse cv;

    public CvDocument() {
    }

    public CvDocument(CvResponse cv, Instant createdAt) {
        this.cv = cv;
        this.createdAt = createdAt;
    }
}

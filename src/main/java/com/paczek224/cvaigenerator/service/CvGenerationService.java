package com.paczek224.cvaigenerator.service;

import com.paczek224.cvaigenerator.dto.CvResponse;
import com.paczek224.cvaigenerator.entity.CvDocument;
import com.paczek224.cvaigenerator.repository.CvResponseRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CvGenerationService {

    private final CvGeneratorAiClient client;
    private final CvResponseRepository repository;

    public CvGenerationService(CvGeneratorAiClient client, CvResponseRepository repository) {
        this.client = client;
        this.repository = repository;
    }

    public CvResponse generate(String userData, String jobOffer, boolean enhanceCv, String language) {
        CvResponse response = client.generateStructuredCv(userData, jobOffer, enhanceCv, language);
        repository.save(new CvDocument(response, Instant.now()));
        return response;
    }
}

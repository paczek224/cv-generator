package com.paczek224.cvaigenerator.repository;

import com.paczek224.cvaigenerator.entity.CvDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CvResponseRepository extends MongoRepository<CvDocument, String> {
}

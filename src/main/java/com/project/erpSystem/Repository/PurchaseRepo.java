package com.project.erpSystem.Repository;

import com.project.erpSystem.model.PurchaseCounter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PurchaseRepo extends MongoRepository<PurchaseCounter, String> {
}

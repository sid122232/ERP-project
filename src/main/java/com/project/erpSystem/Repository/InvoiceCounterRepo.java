package com.project.erpSystem.Repository;

import com.project.erpSystem.model.InvoiceCounter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InvoiceCounterRepo extends MongoRepository<InvoiceCounter, String> {
}

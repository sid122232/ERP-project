package com.project.erpSystem.Repository;

import com.project.erpSystem.model.invoiceData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InvoiceDataRepo extends MongoRepository<invoiceData,String> {
}

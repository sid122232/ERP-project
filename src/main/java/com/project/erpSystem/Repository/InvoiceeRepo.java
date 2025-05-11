package com.project.erpSystem.Repository;

import com.project.erpSystem.model.InvoiceModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InvoiceeRepo extends MongoRepository<InvoiceModel,String> {

}

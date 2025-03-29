package com.project.erpSystem.Repository;

import com.project.erpSystem.model.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface productRepo extends MongoRepository<ProductModel,String> {
}

package com.project.erpSystem.Repository;


import com.project.erpSystem.model.CustomerModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface customerRepo extends MongoRepository<CustomerModel, Integer> {
}

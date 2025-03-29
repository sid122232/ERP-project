package com.project.erpSystem.Repository;

import com.project.erpSystem.model.EmployeeModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface employeeRepo extends MongoRepository<EmployeeModel, ObjectId> {
}

package com.project.erpSystem.Repository;

import com.project.erpSystem.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepo extends MongoRepository<UserModel, String> {
    UserModel findByUserName(String userName);
}

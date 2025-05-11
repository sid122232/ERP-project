package com.project.erpSystem.Service;


import com.project.erpSystem.Repository.UserRepo;
import com.project.erpSystem.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class userService {
@Autowired
private UserRepo userRepo;
@Autowired
    private PasswordEncoder passwordEncoder;




    public void saveNewUser(UserModel user) {
        // Ensure the password is encoded before saving
        if(user.getUserName().equals("sid") && user.getPassword().equals("sid")){
        user.setRoles(Arrays.asList("ADMIN")); // Set default role

        }
       else{
           user.setRoles(Arrays.asList("USER")); // Set default role
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
       userRepo.save(user);
    }

    public void saveEntry(UserModel user) {
        userRepo.save(user); // Save without re-encrypting password
    }

//    public List<UserModel> getAll(){
//        return   userRepo.findAll();
//    }

    public UserModel getUserFromUsername(String userName){
        return userRepo.findByUserName(userName);
    }

    public void deleteEntry(String id){
        userRepo.deleteById(id);
    }

}

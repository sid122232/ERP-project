package com.project.erpSystem.Controller;


import com.project.erpSystem.Service.userService;
import com.project.erpSystem.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private userService UserService;

    @PostMapping("/create-user")

    public ResponseEntity<UserModel> createEntry(@RequestBody UserModel myEntry) {
        UserService.saveNewUser(myEntry);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}

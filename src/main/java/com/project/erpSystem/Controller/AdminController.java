package com.project.erpSystem.Controller;



import com.project.erpSystem.Service.userService;
import com.project.erpSystem.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
//@RequestMapping("/admin")
//public class AdminController {
//    @Autowired
//    private userService UsersService;
//    @GetMapping
//    public ResponseEntity<?> getAll(){
////        List<UserModel> all = UsersService.getAll();
//       if (all != null || !all.isEmpty()){
// return new ResponseEntity<>(all,HttpStatus.OK);
//       }
//else {
//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//       }
//    }
//}

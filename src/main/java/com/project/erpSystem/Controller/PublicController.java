package com.project.erpSystem.Controller;


import com.project.erpSystem.Service.UserDetailServiceImpl;
import com.project.erpSystem.Service.userService;
import com.project.erpSystem.model.UserModel;

import com.project.erpSystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/public")
@CrossOrigin(origins = "http://localhost:5173") // Allow React app to access

public class PublicController {
    @Autowired
    private userService UserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;



    @PostMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }
    @PostMapping("/signup")
    public ResponseEntity<UserModel> signUp(@RequestBody UserModel myEntry) {
        UserService.saveNewUser(myEntry);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserModel user) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());

            // Fix: Fetch actual roles from DB
            UserModel dbUser = UserService.getUserFromUsername(user.getUserName());
            String role = dbUser.getRoles().get(0);

            String jwtToken = jwtUtil.generateToken(userDetails.getUsername(), role);

            Map<String, String> response = new HashMap<>();
            response.put("jwtToken", jwtToken);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", "Incorrect username or password"));
        }
    }}

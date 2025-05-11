package com.project.erpSystem.Package;


import com.project.erpSystem.Repository.customerRepo;
import com.project.erpSystem.Service.CustomerService;
import com.project.erpSystem.model.CustomerModel;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173") // Allow React app to access

public class CustomerAPI {
    @Autowired
    private CustomerService CustomerService;
    @GetMapping


    public ResponseEntity<List<CustomerModel> > getCustomer(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        System.out.println("User Role: " + role); // Optional: Just to see the role


        if (role.contains("ADMIN"))
        {
            System.out.println("Customers: " + CustomerService.getAllCustomer());
            return new ResponseEntity<>( CustomerService.getAllCustomer(), HttpStatus.OK);


        }
        return new ResponseEntity<>(  HttpStatus.NOT_FOUND);
    }
    @GetMapping("/customerCounter")
    public List<Document>  customerCounter(){
        return CustomerService.getAllCustomerByState();
    }
    @PostMapping
    public void saveCustomer(@RequestBody CustomerModel customer){
        CustomerService.saveCustomer(customer);
    }



}

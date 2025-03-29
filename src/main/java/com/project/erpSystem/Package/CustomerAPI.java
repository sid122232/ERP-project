package com.project.erpSystem.Package;


import com.project.erpSystem.Repository.customerRepo;
import com.project.erpSystem.Service.CustomerService;
import com.project.erpSystem.model.CustomerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173") // Allow React app to access

public class CustomerAPI {
    @Autowired
    private CustomerService CustomerService;
    @GetMapping
    public List<CustomerModel> getCustomer(){
        return   CustomerService.getAllCustomer();
    }
    @PostMapping
    public void saveCustomer(@RequestBody CustomerModel customer){
        CustomerService.saveCustomer(customer);
    }



}

package com.project.erpSystem.Controller;



import com.project.erpSystem.Service.CustomerService;
import com.project.erpSystem.model.CustomerModel;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")

public class CustomerController {
    @Autowired
    private CustomerService CustomerService;
    @GetMapping
    public List<CustomerModel> getCustomer(){
        return   CustomerService.getAllCustomer();
    }
    @GetMapping("/{id}")

    public Optional<CustomerModel> getCustomer(@PathVariable ObjectId id){
        return CustomerService.customerFromId(id);
    }


    @PostMapping
    public void saveCustomer(@RequestBody CustomerModel customer){
        CustomerService.saveCustomer(customer);
    }

 @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable ObjectId id){
        CustomerService.deleteCustomer(id);
 }


}





package com.project.erpSystem.Controller;



import com.project.erpSystem.Service.CustomerService;
import com.project.erpSystem.model.CustomerModel;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:5173") // Allow React app to access

public class CustomerController {
    @Autowired
    private CustomerService CustomerService;
    @GetMapping


    public ResponseEntity<List<CustomerModel> >  getCustomer(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        System.out.println("User Role: " + role); // Optional: Just to see the role
        if (role.contains("ADMIN"))
        {
return new ResponseEntity<>( CustomerService.getAllCustomer(), HttpStatus.OK);

        }
        return new ResponseEntity<>(  HttpStatus.NOT_FOUND);


    }
    @GetMapping("/{id}")

    public Optional<CustomerModel> getCustomer(@PathVariable int id){
        return CustomerService.customerFromId(id);
    }


    @PostMapping
    public void saveCustomer(@RequestBody CustomerModel customer){
        CustomerService.saveCustomer(customer);
    }

 @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable int id){
        CustomerService.deleteCustomer(id);
 }


}





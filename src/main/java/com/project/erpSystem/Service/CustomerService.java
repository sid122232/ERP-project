package com.project.erpSystem.Service;

import com.project.erpSystem.Repository.customerRepo;
import com.project.erpSystem.model.CustomerModel;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;



    @Component
    public class CustomerService {
        @Autowired
        private customerRepo CustomerRepository;

        //Getting All the customers

        public List<CustomerModel> getAllCustomer(){
            return CustomerRepository.findAll();
        }

        // Getting Customer from Id

        public Optional<CustomerModel> customerFromId(ObjectId id){
            return CustomerRepository.findById(id);
        }

        // Saving Customer into DB


        public void saveCustomer(CustomerModel customer){
            CustomerRepository.save(customer);
        }

        // Delete Customer from DB

        public void deleteCustomer(ObjectId id){
          CustomerRepository.deleteById(id);

        }
    }


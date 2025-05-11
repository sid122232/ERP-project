package com.project.erpSystem.Service;

import com.project.erpSystem.Repository.customerRepo;
import com.project.erpSystem.model.CustomerModel;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;



    @Component
    public class CustomerService {
        @Autowired
        private customerRepo CustomerRepository;
        @Autowired
        private MongoTemplate mongoTemplate;
        //Getting All the customers

        public List<CustomerModel> getAllCustomer(){
            return CustomerRepository.findAll();
        }

        // Getting Customer from Id

        public Optional<CustomerModel> customerFromId(int id){
            return CustomerRepository.findById(id);
        }

        // Saving Customer into DB


        public void saveCustomer(CustomerModel customer){
 List<CustomerModel> customers = CustomerRepository.findAll();
 int newId = customers.isEmpty()?1:customers.stream().mapToInt(CustomerModel::getId).max().orElse(0)+1;
            customer.setId(newId);
            CustomerRepository.save(customer);
            System.out.println("Received customer data: " + customer);

        }

        // Delete Customer from DB

        public void deleteCustomer(int id){
          CustomerRepository.deleteById(id);

        }
        public List<Document> getAllCustomerByState() {
            Aggregation aggregation = Aggregation.newAggregation(
                    Aggregation.group("state")
                            .count().as("totalCustomers"),
                    Aggregation.sort(Sort.Direction.DESC, "totalCustomers") // converts the customers number in desc order

            );
            AggregationResults<Document> results = mongoTemplate.aggregate(
                    aggregation,
                    "customers",
                    Document.class
            );
            List<Document> mapped = results.getMappedResults();
            for (Document doc : mapped) {
                String state = doc.getString("_id");
                doc.put("state", state);
                doc.remove("_id");
            }
            return mapped;
        }
    }


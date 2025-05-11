package com.project.erpSystem.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
@Data
public class CustomerModel {
        @Id
        private int id;
        private String companyName;
        private String zone;// south,east,west or north
        private String state;
        private String city;
        private String classification;// Trader, OEM
        private String name;
        private String number;//phone number

}

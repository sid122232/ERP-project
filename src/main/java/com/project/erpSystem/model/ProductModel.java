package com.project.erpSystem.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "products")
public class ProductModel {
    @Id
    private String id;
    private String name;
    private String category;
    private int stockQuantity;
    private double price;
}

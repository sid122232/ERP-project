package com.project.erpSystem.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;

@Collation
@Data
public class InvoiceCounter {
    @Id
    private String id;
    private int sequence;

}

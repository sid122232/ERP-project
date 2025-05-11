package com.project.erpSystem.model;


import lombok.Data;
import org.springframework.data.annotation.Id;

@Data

public class invoiceData {
    @Id
    private String id;
   private String month;
   private int numbers;
}

package com.project.erpSystem.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
public class InvoiceItems {
    @Id
    private String id;
    private String description;
    private String hsn;
    private String unitPrice;
    private int quantity;


}

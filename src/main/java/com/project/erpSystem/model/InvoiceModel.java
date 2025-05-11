package com.project.erpSystem.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

    @Data
public class InvoiceModel {
    @Id
    private String id;
  private String companyName;
    private String customerName;
       private String phoneNumber;
       private String streetAddress;
       private String city;
        private String state;
        private String gstNumber;
         private String email;
    private String invoiceNumber;
    private String poNumber;
    private String date;
    private String poDate;
        private String gstRate = "18";
        private String tax1;  // Add tax1
        private String tax2;  // Add tax2
    private List<InvoiceItems> items;
    private double totalAmount;
    private LocalDateTime createdAt;

        public LocalDate getParsedPoDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(this.poDate, formatter);
    }
}

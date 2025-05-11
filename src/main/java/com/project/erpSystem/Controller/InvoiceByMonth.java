package com.project.erpSystem.Controller;


import com.project.erpSystem.Repository.InvoiceeRepo;
import com.project.erpSystem.Service.InvoiceService;
import com.project.erpSystem.model.InvoiceModel;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:5173")
public class InvoiceByMonth {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/api/invoiceCounter")

    public List<Document> invoiceCounter(){

        return invoiceService.getMonthlyInvoiceCountsWithNames();
    }
}

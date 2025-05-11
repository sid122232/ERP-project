package com.project.erpSystem.Controller;


import com.project.erpSystem.Service.InvoiceService;
import com.project.erpSystem.Service.PurchaseService;
import com.project.erpSystem.model.InvoiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoice")
@CrossOrigin(origins = "http://localhost:5173") // Allow React app to access

public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<InvoiceModel> createInvoice(@RequestBody InvoiceModel invoice) {
        InvoiceModel savedInvoice = invoiceService.createInvoice(invoice);
        return ResponseEntity.ok(savedInvoice);
    }

}

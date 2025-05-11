package com.project.erpSystem.Service;

import com.project.erpSystem.Repository.InvoiceCounterRepo;
import com.project.erpSystem.Repository.InvoiceDataRepo;
import com.project.erpSystem.Repository.InvoiceeRepo;
import com.project.erpSystem.model.InvoiceCounter;
import com.project.erpSystem.model.InvoiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.bson.Document; // ✅ Use this
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private InvoiceCounterRepo counterRepo;
    @Autowired
    private InvoiceeRepo invoiceRepo;
    @Autowired
    private PurchaseService purchaseService;
 @Autowired
 private InvoiceDataRepo invoiceDataRepo;

    public String generateInvoiceNumber(){
        String financialYear = getCurrentFinancialYear();
        String prefix = "AA";


InvoiceCounter counter = counterRepo.findById(financialYear) .orElseGet(() -> {
            InvoiceCounter newCounter = new InvoiceCounter();
            newCounter.setId(financialYear);
            newCounter.setSequence(0);
            return newCounter;
        });

int nextSequence = counter.getSequence()+1;
counter.setSequence(nextSequence);
counterRepo.save(counter);

        String serial = String.format("%03d", nextSequence); // e.g. 028

        return prefix + "/" + financialYear + "/" + serial;


    }
    private String getCurrentDate (){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return currentDate.format(formatter);
    }

    private String getCurrentFinancialYear(){
        LocalDate today = LocalDate.now();
        int year = today.getMonthValue()<4? today.getYear()-1 : today.getYear();
        return year + "-" + (year + 1) %100;
    }
    public InvoiceModel createInvoice( InvoiceModel invoice){
        invoice.setInvoiceNumber(generateInvoiceNumber());
        invoice.setPoNumber(purchaseService.generatePurchaseOrderNumber());
        invoice.setDate(getCurrentDate());

        if (invoice.getCreatedAt() == null) {
            invoice.setCreatedAt(LocalDateTime.now());
        }

        return invoiceRepo.save(invoice);
    }
    public List<Document> getMonthlyInvoiceCountsWithNames() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project()
                        .andExpression("month(createdAt)").as("month"), // Extract month number from createdAt
                Aggregation.group("month") // Group by extracted month
                        .count().as("totalInvoices"), // Count invoices per month
                Aggregation.sort(Sort.Direction.ASC, "_id") // Sort by month number (which becomes _id)
        );

        AggregationResults<Document> results = mongoTemplate.aggregate(
                aggregation,
                "invoiceModel", // Collection name, must be a string
                Document.class
        );

        List<Document> mapped = results.getMappedResults();

        // Map month number to name in Java (1 = January, etc.)
        String[] monthNames = {
                "", "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };

        for (Document doc : mapped) {
            int monthNumber = doc.getInteger("_id", 0); // Safe fallback to 0 if null
            String monthName = (monthNumber >= 1 && monthNumber <= 12)
                    ? monthNames[monthNumber]
                    : "Unknown";

            doc.put("monthName", monthName);
            doc.remove("_id"); // Optional: remove numeric month
        }

        return mapped;
    }

}

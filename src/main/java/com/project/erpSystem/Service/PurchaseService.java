package com.project.erpSystem.Service;

import com.project.erpSystem.Repository.InvoiceeRepo;
import com.project.erpSystem.Repository.PurchaseRepo;
import com.project.erpSystem.model.InvoiceModel;
import com.project.erpSystem.model.PurchaseCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PurchaseService {
    @Autowired
    private InvoiceeRepo invoiceRepo;
    @Autowired
    private PurchaseRepo purchaseRepo;


    public String generatePurchaseOrderNumber(){
        String financialYear = getCurrentFinancialYear();
        String prefix = "PO";


        PurchaseCounter counter = purchaseRepo.findById(financialYear) .orElseGet(() -> {
            PurchaseCounter newCounter = new PurchaseCounter();
            newCounter.setId(financialYear);
            newCounter.setSequence(0);
            return newCounter;
        });

        int nextSequence = counter.getSequence()+1;
        counter.setSequence(nextSequence);
        purchaseRepo.save(counter);

        String serial = String.format("%03d", nextSequence); // e.g. 028

        return prefix+"/" + "M" + "/" + financialYear + "/" + serial;


    }
    private String getCurrentFinancialYear(){
        LocalDate today = LocalDate.now();
        int year = today.getMonthValue()<4? today.getYear()-1 : today.getYear();
        return year + "-" + (year + 1) %100;
    }


}

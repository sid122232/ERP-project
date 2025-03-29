package com.project.erpSystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Document(collection = "salary-slip")
public class SalarySlipModel {
    @Id
    private String id;
    private String username; // Link to Employee's username
    private double basicSalary;
    private double hra;
    private double deductions;
    private double netSalary;
    private Date salaryMonth; // Month & Year
}

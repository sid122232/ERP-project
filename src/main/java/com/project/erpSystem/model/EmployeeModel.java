package com.project.erpSystem.model;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;


import java.util.List;

@Document(collection = "employees")
@Data
public class EmployeeModel {
    @Id
    private ObjectId Id;
    @Indexed(unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String name;
    @NonNull
    private int age;
    @NonNull
    private String email;
    @NonNull
    private  int phoneNumber;
    @NonNull
    private String department;
    @NonNull
    private String designation;
    @NonNull
    private List<SkillsModel> skills;
    @NonNull
    private List<EducationModel> education;
    @NonNull
    private List<WorkExperienceModel> workExperience;


}

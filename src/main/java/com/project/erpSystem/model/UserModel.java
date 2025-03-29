package com.project.erpSystem.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;


import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data

public class UserModel {
    @Id
    private String id;
    @Indexed(unique = true)
    private String userName;
    private String password;
    private List<String> roles;
    @DBRef
    private List<EmployeeModel> employees = new ArrayList<>();

}

package com.project.erpSystem.Service;

import com.project.erpSystem.Repository.employeeRepo;
import com.project.erpSystem.model.EmployeeModel;
import com.project.erpSystem.model.UserModel;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class employeeService {
@Autowired
    private employeeRepo employeeRepo;
@Autowired
private  userService UserService;
//Getting All The employees
  public List<EmployeeModel> getAll(){
      return employeeRepo.findAll();
  }

  //Saving Employee

    @Transactional
    public void saveEntry (EmployeeModel employeeModel, String userName){
        try {

            UserModel user = UserService.getUserFromUsername(userName);// Getting user
            if (user == null) {
                throw new RuntimeException("User not found with username: " + userName);
            }


            EmployeeModel saved =  employeeRepo.save(employeeModel);// Saving journal entry
            // Prevent duplicate entries
            if (!user.getEmployees().contains(saved)) {
                user.getEmployees().add(saved);
            }
            // Adding Journal Entries In user Journal entries list

            UserService.saveEntry(user);
        } catch (Exception e) {

            System.out.println(e);
            throw new RuntimeException("Error while saving ", e);

        }

    }



    // Getting Employee from its id
    public Optional<EmployeeModel> employeeFromId(ObjectId id){
      return employeeRepo.findById(id) ;
    }
}

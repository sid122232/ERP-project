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

@Component
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
public void saveEmployee(EmployeeModel employee,String userName){
try {
    UserModel user = UserService.getUserFromUsername(userName);
    if (user == null) {
        throw new RuntimeException("User not found with username: " + userName);
    }
      EmployeeModel saveEmployee = employeeRepo.save(employee);//10:35 PM 3/15/2025 day end
if(!user.getEmployees().contains(saveEmployee)){
    user.getEmployees().add(saveEmployee);
}

UserService.saveUser(user);

}
catch (Exception e){
    System.out.println(e);
    throw new RuntimeException("Error while saving ", e);
}
}


// Getting Employee from its id
    public Optional<EmployeeModel> employeeFromId(ObjectId id){
      return employeeRepo.findById(id) ;
    }
}

package com.project.erpSystem.Controller;

import com.project.erpSystem.Service.employeeService;
import com.project.erpSystem.Service.userService;
import com.project.erpSystem.model.EmployeeModel;
import com.project.erpSystem.model.UserModel;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private employeeService EmployeeService;
    @Autowired
    private userService userService;




    // Getting All employees controller---------------------------------------------------
 @GetMapping
    public ResponseEntity<?> getAllEmployees(){
     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
     String userName=  authentication.getName();// Gets username stored in securityContext

     UserModel user = userService.getUserFromUsername(userName);
     List<EmployeeModel> employeeDetails = user.getEmployees();

if(employeeDetails!=null && !employeeDetails.isEmpty())
{
    return new ResponseEntity<>(employeeDetails,HttpStatus.OK);
}
     return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    //--------------------------------------------------------------------------//
    @GetMapping("/{id}")
    public Optional<EmployeeModel> emoloyeeFromID(@PathVariable ObjectId id){

       return EmployeeService.employeeFromId(id);
    }

    @PostMapping
    public ResponseEntity<EmployeeModel> saveEmployee(@RequestBody EmployeeModel employee){
     try{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=  authentication.getName();// Gets username stored in securityContext


    // Saving employees
        EmployeeService.saveEmployee(employee,userName);
         return new ResponseEntity<>(employee,HttpStatus.CREATED);
     }
     catch (Exception e){
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
     }

    }



}

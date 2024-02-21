package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.Setter;
import net.javaguides.springboot.expection.ResourceNotFoundException;
import net.javaguides.springboot.module.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;


//@CrossOrigin(origins = "http://localhost:5173")
@RestController
//@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	//get all employee
	@GetMapping("/employee")
	public List<Employee> getAllEmployee(){
		
		return employeeRepository.findAll();
	}
	
	//create employee rest api
//	@PostMapping("/employee")
//	public Employee createEmployee(@RequestBody Employee employee) {
//		return employeeRepository.save(employee);
//	}
//	
	
	@PostMapping("/employee")
	public ResponseEntity<?> createEmployee(@RequestBody @Valid Employee employee, BindingResult result) {
	    if (result.hasErrors()) {
	    	// Handle validation errors
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation error: " + result.getFieldError().getDefaultMessage());
	    }

	    // Custom validation for firstName being null
	       
//	    if (employee.getFirstName() == null || employee.getFirstName().equals("")) {
//	        throw new ResourceNotFoundException("First Name cannot be null");
//	    }
//	    String firstName = employee.getFirstName();
//	    if (firstName == null || firstName.length() < 3 || firstName.length() > 20) {
//	    	throw new ResourceNotFoundException("First Name cannot be null");
//
//	    	//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("First Name must be between 3 and 20 characters");
//	    }

	    Employee savedEmployee = employeeRepository.save(employee);
	    return ResponseEntity.ok(savedEmployee);
	  //  return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
	}

	//get employee by id rest api
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee=employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		return ResponseEntity.ok(employee);
		
	}
	//update employee rest api
	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		
		Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	// delete employee rest api
		@DeleteMapping("/employee/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
			Employee employee = employeeRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
			
			employeeRepository.delete(employee);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
}

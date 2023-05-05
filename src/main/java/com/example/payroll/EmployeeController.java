package com.example.payroll;

import java.util.*;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

public class EmployeeController {

	private final EmployeeResipotory res;
	
	EmployeeController(EmployeeResipotory res) {
		this.res = res;
	}
	
	
//	Aggregate root
	// tag::get-aggregate
	@GetMapping("/employees")
	List<Employee> all() {
		return res.findAll();
	}
	
	
	
	//new Employee
	
	@PostMapping("/employees")
	Employee newEmployee(@RequestBody Employee newEmployee) {
		return res.save(newEmployee);
	}
	
//	Single item
	
	@GetMapping("/employees/{id}")
	Employee one(@PathVariable Long id) {
		return res.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException(id));
	}
	
	@PutMapping("/employees/{id}")
	Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		return res.findById(id)
				.map(employee -> {
					employee.setName(newEmployee.getName());
					employee.setRole(newEmployee.getRole());
					return res.save(employee);
				})
				.orElseGet(() -> {
					newEmployee.setId(id);
					return res.save(newEmployee);
				});
	}
	
	
	@DeleteMapping("/employees/{id}")
	void deleteEmployee(@PathVariable Long id) {
		res.deleteById(id);
	}
	
}

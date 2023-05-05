package com.example.payroll;

import java.util.*;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class EmployeeController {

	private final EmployeeResipotory res;
	
	EmployeeController(EmployeeResipotory res) {
		this.res = res;
	}
	
	@GetMapping("/employees")
	List<Employee> all() {
		return res.findAll();
	}
}

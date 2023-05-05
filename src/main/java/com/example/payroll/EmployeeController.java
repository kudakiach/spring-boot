package com.example.payroll;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class EmployeeController {

	private final EmployeeResipotory res;
	
	private final EmployeeModelAssembler assembler;
	
	EmployeeController(EmployeeResipotory res, EmployeeModelAssembler assembler) {
		this.res = res;
		this.assembler = assembler;
	}
	
	
	
	
//	Aggregate root
	// tag::get-aggregate
	@GetMapping("/employees")
	CollectionModel<EntityModel<Employee>> all() {
		List<EntityModel<Employee>> employees = res.findAll().stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());

			return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
	}
	

	


//    The return type of the method has changed from Employee to EntityModel<Employee>. 
	 			// EntityModel<T> is a generic container from Spring HATEOAS that includes not only the data but a collection of links.
//
//    linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel() 
				// asks that Spring HATEOAS build a link to the EmployeeController 's one() method, and flag it as a self link.
//
//    linkTo(methodOn(EmployeeController.class).all()).withRel("employees") 
				// asks Spring HATEOAS to build a link to the aggregate root, all(), and call it "employees".


	
	//new Employee
	
	@PostMapping("/employees")
	
	ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) {
		
		EntityModel<Employee> entityModel = assembler.toModel(res.save(newEmployee));
		
		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}
	
	
	
//	Single item
	@GetMapping("/employees/{id}")
	EntityModel<Employee> one(@PathVariable Long id) {

	  Employee employee = res.findById(id) //
	      .orElseThrow(() -> new EmployeeNotFoundException(id));

	  return assembler.toModel(employee);
	}
	
	
	@PutMapping("/employees/{id}")
	ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		Employee updateEmployee = res.findById(id)
				.map(employee -> {
					employee.setName(newEmployee.getName());
					employee.setRole(newEmployee.getRole());
					return res.save(employee);
				})
				.orElseGet(() -> {
					newEmployee.setId(id);
					return res.save(newEmployee);
				});
		
		EntityModel<Employee> entityModel = assembler.toModel(updateEmployee);
		
		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}
	
	
	@DeleteMapping("/employees/{id}")
	ResponseEntity<?> deleteEmployee(@PathVariable Long id) {

	  res.deleteById(id);

	  return ResponseEntity.noContent().build();
	}
	
}

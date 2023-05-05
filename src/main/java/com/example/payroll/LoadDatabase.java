package com.example.payroll;

import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
	
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	
	@Bean
	CommandLineRunner initDatabase(EmployeeResipotory employeeRepository, OrderRepository orderRepository) {
		
		return args -> {
			
			employeeRepository.save(new Employee("Samuel", "Kiarie", "Analyst"));
			employeeRepository.save(new Employee("Martin", "Kinuthia", "S.Engeneer"));
			
			employeeRepository.findAll().forEach(employee -> log.info("Preloading" + employee));
			
			orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
		      orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

		      orderRepository.findAll().forEach(order -> {
		        log.info("Preloaded " + order);
		      });
			
		};
	}

}

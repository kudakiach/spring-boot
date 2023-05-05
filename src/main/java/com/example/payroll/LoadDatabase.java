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
	CommandLineRunner initDatabase(EmployeeResipotory repository) {
		
		return args -> {
			log.info("Preloading" + repository.save(new Employee("Samuel Kiarie", "Analyst")));
			log.info("Preloading" + repository.save(new Employee("Martin Kinuthia", "S.Engeneer")));
		};
	}

}

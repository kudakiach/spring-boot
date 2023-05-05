package com.example.payroll;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeResipotory extends JpaRepository<Employee, Long> {

}

package com.springbootbackend.springbootbackend.repository;

import com.springbootbackend.springbootbackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

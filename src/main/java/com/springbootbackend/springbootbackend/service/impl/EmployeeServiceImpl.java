package com.springbootbackend.springbootbackend.service.impl;

import com.springbootbackend.springbootbackend.exception.ResourceNotFoundException;
import com.springbootbackend.springbootbackend.model.Employee;
import com.springbootbackend.springbootbackend.repository.EmployeeRepository;
import com.springbootbackend.springbootbackend.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
//        Optional<Employee> employee = employeeRepository.findById(id);
//        if(employee.isPresent()){
//            return employee.get();
//        } else {
//            throw new ResourceNotFoundException("Employee", "id", id);
//        }

      return employeeRepository.findById(id).orElseThrow(() ->
              new ResourceNotFoundException("Employee", "id", id));

    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        // check if the employee with the provided id exists
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Employee", "Id", id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

        //save existing employee to DB
        employeeRepository.save(existingEmployee);
        return existingEmployee;

    }

    @Override
    public void deleteEmployee(long id) {
        //Check whether an employee exists in the DB or not

        employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employeeRepository.deleteById(id);
    }
}

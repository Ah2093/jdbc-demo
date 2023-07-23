package com.example.jdbcdemo.controller;

import com.example.jdbcdemo.model.Employee;
import com.example.jdbcdemo.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Qualifier("employeeNamedParameterJDBCRepo")
    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping("/count")
    public int countEmployee()
    {
        return employeeRepo.count();
    }
    @GetMapping("/{id}")
    public Employee findById(@PathVariable Long id)
    {
        return employeeRepo.findById(id);
    }
    @GetMapping("")
    public List<Employee> findAll()
    {
        return employeeRepo.findAll();
    }
}

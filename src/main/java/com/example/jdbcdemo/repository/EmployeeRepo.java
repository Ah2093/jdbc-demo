package com.example.jdbcdemo.repository;

import com.example.jdbcdemo.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface EmployeeRepo {
    int count ();
    Employee findById(Long id);

    List<Employee> findByNmaeAndSalary(String name, Double salary);

    List<Employee> findAll();
    int insert(Employee employee);
    int update (Employee employee);
    int delete(Long id);
}

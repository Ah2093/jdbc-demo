package com.example.jdbcdemo.config;

import com.example.jdbcdemo.model.Employee;
import com.example.jdbcdemo.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class StartUp implements CommandLineRunner {


    @Autowired
    @Qualifier("employeeJDBCRepo")
    private EmployeeRepo employeeRepo ;
    @Autowired

    private JdbcTemplate jdbcTemplate;
    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.execute("DROP TABLE IF EXISTS employees");
        jdbcTemplate.execute("CREATE TABLE employees(id INT,name VARCHAR(255),salary NUMERIC(15,2))");
        if(employeeRepo.count()==0) {
            employeeRepo.insert(new Employee(1L, "ahmed", 10000.0));
            employeeRepo.insert(new Employee(2L, "yaso", 10000.0));
            employeeRepo.insert(new Employee(3L, "osama", 10000.0));
        }
    }
}

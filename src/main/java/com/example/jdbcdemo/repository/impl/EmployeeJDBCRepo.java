package com.example.jdbcdemo.repository.impl;

import com.example.jdbcdemo.mapper.EmployeeMapper;
import com.example.jdbcdemo.model.Employee;
import com.example.jdbcdemo.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
@Qualifier("employeeJDBCRepo")
public class EmployeeJDBCRepo implements EmployeeRepo {

    // api to work with jdbc api
    @Autowired
    private JdbcTemplate jdbcTemplate ;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from employees",Integer.class);
    }

    @Override
    public Employee findById(Long id) {
        return jdbcTemplate.queryForObject("select id , name , salary  from employees where id = ?",new Object[]{id},
                //mapping of result
                new EmployeeMapper()
        );
    }

    @Override
    public List<Employee> findByNmaeAndSalary(String name, Double salary) {
        return Collections.singletonList(jdbcTemplate.queryForObject("select id , name , salary from employees where name =? and salary =?", new Object[]{name, salary}, new EmployeeMapper()));
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query("select id , name , salary  from employees"
                , new EmployeeMapper()
        );
                //mapping of result
    }

    @Override
    public int insert(Employee employee) {
        return jdbcTemplate.update("insert into employees (id , name , salary) values (?,?,?)",
                new Object[] {employee.getEmployeeId(),employee.getName(),employee.getSalary()});
    }

    @Override
    public int update(Employee employee) {
        return jdbcTemplate.update("update employees set name = ? , salary =? ",
                new Object[]{employee.getName(),employee.getSalary()});
    }

    @Override
    public int delete(Long id) {
        return jdbcTemplate.update("delete from employees where id =? ",new Object[]{id});
    }
}

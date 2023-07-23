package com.example.jdbcdemo.repository.impl;

import com.example.jdbcdemo.mapper.EmployeeMapper;
import com.example.jdbcdemo.model.Employee;
import com.example.jdbcdemo.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@Qualifier("employeeNamedParameterJDBCRepo")
public class EmployeeNamedParameterJDBCRepo implements EmployeeRepo {

    // api to work with jdbc api
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate ;

    @Override
    public int count() {
       /* return namedParameterJdbcTemplate.queryForObject("select count(*) from employees",);
    */
        return 0;
    }

    @Override
    public Employee findById(Long id) {
        return namedParameterJdbcTemplate.queryForObject("select id , name , salary  from employees where id = :passedId"
                ,new MapSqlParameterSource("passedId",id)
                ,
                //mapping of result
                new EmployeeMapper()
        );
    }
    @Override
    public List<Employee> findByNmaeAndSalary(String name, Double salary) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource() ;
        mapSqlParameterSource.addValue("name",name);
        mapSqlParameterSource.addValue("salary" ,salary);
        return Collections.singletonList(namedParameterJdbcTemplate.queryForObject("select id , name , salary  from employees where name = :name and salary =:salary "
                , mapSqlParameterSource
                ,
                new EmployeeMapper()
                //mapping of result
        ));
    }
    @Override
    public List<Employee> findAll() {
        return namedParameterJdbcTemplate.query("select id , name , salary  from employees"
                , new EmployeeMapper()
        );
                //mapping of result
    }

    @Override
    public int insert(Employee employee) {
        return namedParameterJdbcTemplate.update("insert into employees (id , name , salary) values (:employeeId:name,:salary)",
               new BeanPropertySqlParameterSource(employee));
    }

    @Override
    public int update(Employee employee) {
        return namedParameterJdbcTemplate.update("update employees set name = :name , salary =:sal ",
                new BeanPropertySqlParameterSource(employee));
    }

    @Override
    public int delete(Long id) {
        return namedParameterJdbcTemplate.update("delete from employees where id =:id ",new MapSqlParameterSource("id",id));
    }
}

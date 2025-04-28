package com.example.dao;

import com.example.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class NamedQueryTest {

    EmployeeDAO dao;
    @BeforeEach
    void setDao(){
        dao = new EmployeeDAOimpl();

    }


    @Test
    void seniorNamedQueryTest() {
        List<Employee> employeeList = dao.findEmployeesSenior();
        System.out.println(employeeList);
    }


    @Test
    void marriedNamedQueryTest() {
        List<Employee> employeeList = dao.findEmployeeMarried();
        System.out.println(employeeList);
    }








}

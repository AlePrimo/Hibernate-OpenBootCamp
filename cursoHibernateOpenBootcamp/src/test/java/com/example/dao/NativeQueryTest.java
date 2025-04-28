package com.example.dao;

import com.example.dto.EmployeeDTO;
import com.example.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class NativeQueryTest {

    EmployeeDAO dao;

    @BeforeEach
    void setUp(){
        dao = new EmployeeDAOimpl();
    }

    @Test
    void findByIdNative() {
        Employee employee  = dao.findByIdNative(1L);
        System.out.println(employee);
    }

    @Test
    void findAllNative() {
        List<Employee> employees = dao.findAllNative();
        for (Employee employee : employees){
            System.out.println(employee);
        }

    }


    @Test
    void findAllProjections() {
        List<EmployeeDTO> employees = dao.findAllProjections();


            System.out.print(employees);




    }
}

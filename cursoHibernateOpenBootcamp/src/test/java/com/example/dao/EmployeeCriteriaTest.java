package com.example.dao;

import com.example.entities.Employee;
import com.example.entities.EmployeeCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EmployeeCriteriaTest {

    EmployeeDAO dao;

    @BeforeEach
    void setUp() {
        dao = new EmployeeDAOimpl();
    }




    //METODOS DE BUSQUEDA (READ O RETRIEVE)
    @Test
    void findAllCriteria() {
        List<Employee> employees = dao.findAllCriteria();
        System.out.println(employees);


    }

    @Test
    void findByIdCriteria() {
        Employee employee = dao.findByIdCriteria(2L);
        System.out.println(employee);
    }

    @Test
    void findByLastNameLikeCriteria() {
        List<Employee> employees = dao.findByLastNameLikeCriteria("Perez");
        System.out.println(employees);
    }

    @Test
    void findByOlderAgeCriteria () {
        List<Employee> employees = dao.findByOlderAgeCriteria(45);
        System.out.println(employees);
    }

    @Test
    void findByBetweenAgeCriteria() {
        List<Employee> employees = dao.findByBetweenAgeCriteria(22, 45);
        System.out.println(employees);
    }

    @Test

    void findByAgeBetweenAndCategory(){

        List<Employee> employees = dao.findByAgeBetweenAndCategory(34,62, EmployeeCategory.SENIOR);
        System.out.println(employees);
    }


    @Test
    void calcAvgAgeCriteria() {


        Integer edadMedia = dao.calcAvgAgeCriteria();
        System.out.println(edadMedia);


    }
}

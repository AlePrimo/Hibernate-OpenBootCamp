package com.example.dao;

import com.example.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


class EmployeeCRUDTest {



EmployeeDAO dao;

    @BeforeEach
    void setUp() {
        dao = new EmployeeDAOimpl();
    }

//    @Test
//    void findAll() {
//
//        List<Employee> employees = dao.findAll();
//        System.out.println(employees);
//
//    }
//
//    @Test
//    void findById() {
//
//        Employee employee = dao.findById(1L);
//        System.out.println(employee);
//
//    }

//    @Test
//    void findByEmail() {
//        Employee employee = dao.findByEmail("petela@mail.com");
//        System.out.println(employee);
//    }

    @Test
    void create() {

        Employee employee3 = new Employee(null, "Roberto", "Parna","parnaroli@gmail.com", 22, 250000D, LocalDate.of(2002,12,1),false);

        dao.create(employee3);
        System.out.println(employee3);
//        Employee employee1= new Employee(null, "Robertino", "Perez","pipo@gmail.com");
//        Employee employee2 = new Employee(null, "Checo", "Perez","checo@gmail.com");
//        Employee employee4= new Employee(null, "Perico", "Perez","perico@gmail.com");
//        dao.create(employee1);
//        dao.create(employee2);
//        dao.create(employee4);
    }

//    @Test
//    void update() {
//        //Employee employee1 = new Employee(1L,"Pedro","Argentino","pepe@mail.com", 62, 2500000D,LocalDate.of(1947,1,1),true);
//        //dao.update(employee1);
//
//        //COMPROBANDO LA ASOCIACION ENTRE EMPLOYEE Y ADRESS
//        Employee employee1 = dao.findById(1L);
//        AdressDAO dao2 = new AdressDAOimpl();
//
//        employee1.setAdress(dao2.findById(2L));
//        dao.update(employee1);
//
//    }
//
//    @Test
//    void deleteById() {
//        boolean result = dao.deleteById(4L);
//
//        System.out.println(result);
//
//
//    }


//    @Test
//    void countTest() {
//        Long employeesCount = dao.count();
//        System.out.println(employeesCount);
//    }



}






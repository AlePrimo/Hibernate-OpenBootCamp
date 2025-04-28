package com.example.dao;

import com.example.entities.Adress;
import com.example.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

class EmployeeCRUDTest {



EmployeeDAO dao;

    @BeforeEach
    void setUp() {
        dao = new EmployeeDAOimpl();
    }

    @Test
    void findAll() {

        List<Employee> employees = dao.findAll();
        System.out.println(employees);

    }

    @Test
    void findById() {

        Employee employee = dao.findById(1L);
        System.out.println(employee);

    }

    @Test
    void findByEmail() {
        Employee employee = dao.findByEmail("petela@mail.com");
        System.out.println(employee);
    }

    @Test
    void create() {

        //Employee employee3 = new Employee(null, "Roberto", "Parna","parnaroli@gmail.com", 22, 250000D, LocalDate.of(2002,12,1),false);

        //dao.create(employee3);
        //System.out.println(employee3);
        Employee employee4 = new Employee(null, "Robertino", "Perez","pipo@gmail.com", 45, 50000D, LocalDate.of(1978,12,14),true);
        Employee employee5 = new Employee(null, "Checo", "Perez","checo@gmail.com", 34,350000D, LocalDate.of(1988,11,1),false);
        Employee employee6 = new Employee(null, "Perico", "Perez","perico@gmail.com", 52, 150000D, LocalDate.of(1968,7,4),true);
        dao.create(employee4);
        dao.create(employee5);
        dao.create(employee6);
    }

    @Test
    void update() {
        //Employee employee1 = new Employee(1L,"Pedro","Argentino","pepe@mail.com", 62, 2500000D,LocalDate.of(1947,1,1),true);
        //dao.update(employee1);

        //COMPROBANDO LA ASOCIACION ENTRE EMPLOYEE Y ADRESS
        Employee employee1 = dao.findById(1L);
        AdressDAO dao2 = new AdressDAOimpl();

        employee1.setAdress(dao2.findById(2L));
        dao.update(employee1);

    }

    @Test
    void deleteById() {
        boolean result = dao.deleteById(4L);

        System.out.println(result);


    }


    @Test
    void countTest() {
        Long employeesCount = dao.count();
        System.out.println(employeesCount);
    }



}






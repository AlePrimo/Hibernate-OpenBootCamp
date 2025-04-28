package com.example.relationshipTest;

import com.example.dao.*;
import com.example.entities.Company;
import com.example.entities.Customer;
import com.example.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class OneToManyTest {


    EmployeeDAO daoEmplo;
    CustomerDAO daoCustomer;
    CompanyDAO daoCompany;

    @BeforeEach

    void setUp(){
        daoEmplo = new EmployeeDAOimpl();
        daoCustomer = new CustomerDAOimpl();
        daoCompany = new CompanyDAOimpl();

    }



    @Test

    void oneToManyTest() {


//        Employee employeeY = daoEmplo.findById(5L);
//        List<Customer> customers = new ArrayList<>();
//
//        customers.add(daoCustomer.findById(1L));
//        customers.add(daoCustomer.findById(2L));
//
//        employeeY.setCustomers(customers);
//
//        daoEmplo.update(employeeY);




        Employee employeeDB = daoEmplo.findByIdEager(5L);
        System.out.println(employeeDB);
        List<Customer> customersDB = employeeDB.getCustomers();
        System.out.println(customersDB);




    }


    @Test
    void listOfEmployeesTest(){

       List<Employee> employees = daoCompany.finfByIdEager(1L);

        System.out.println(employees);

    }











}

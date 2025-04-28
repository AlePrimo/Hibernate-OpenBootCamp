package com.example.relationshipTest;

import com.example.dao.CompanyDAO;
import com.example.dao.CompanyDAOimpl;
import com.example.dao.EmployeeDAO;
import com.example.dao.EmployeeDAOimpl;
import com.example.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ManyToOneTest {

    EmployeeDAO daoEmplo;
    CompanyDAO daoCompa;

    @BeforeEach
    void setUp(){
        daoEmplo = new EmployeeDAOimpl();
        daoCompa = new CompanyDAOimpl();

    }


    @Test
    void manyToOneTest(){

        Employee employeex = daoEmplo.findById(1L);
        Employee employeeY = daoEmplo.findById(2L);
        Employee employeeZ = daoEmplo.findById(5L);

        employeex.setCompany(daoCompa.findById(1L));
        employeeY.setCompany(daoCompa.findById(1L));
        employeeZ.setCompany(daoCompa.findById(2L));
        daoEmplo.update(employeeY);
        daoEmplo.update(employeex);
        daoEmplo.update(employeeZ);



    }






}

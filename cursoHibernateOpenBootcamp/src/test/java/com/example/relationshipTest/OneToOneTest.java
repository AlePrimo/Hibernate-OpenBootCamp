package com.example.relationshipTest;

import com.example.dao.AdressDAO;
import com.example.dao.AdressDAOimpl;
import com.example.dao.EmployeeDAO;
import com.example.dao.EmployeeDAOimpl;
import com.example.entities.Adress;
import com.example.entities.Employee;
import com.example.entities.EmployeeCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class OneToOneTest {

    EmployeeDAO daoEmplo;
    AdressDAO daoAdress;

    @BeforeEach

    void setUp(){
        daoEmplo = new EmployeeDAOimpl();
        daoAdress = new AdressDAOimpl();
    }



    @Test

    void oneToOneTest(){

        Employee employeeX = new Employee(null,"Peter", "Lanzani","lanza@mail.com",45, 524000D, LocalDate.of(1979,5,2),false);

        Adress adressLanza = new Adress(null,"Camacua 2345","Buenos Aires", "Argentina");




          //daoEmplo.create(employeeX);
          //daoAdress.createAdress(adressLanza);

        Employee employeeY = daoEmplo.findById(13L);
        //employeeY.setAdress(daoAdress.findById(6L)); ///IMPORTANTE SIEMPRE BUSCAR EL OBJETO CON EL METODO FIND
        employeeY.setCategory(EmployeeCategory.SENIOR);
          daoEmplo.update(employeeY);



    }








}

package com.example.entities;

import com.example.dao.CustomerDAO;
import com.example.dao.CustomerDAOimpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CustomerEventsTest {



    CustomerDAO dao;

    @BeforeEach
    void setUp() {
        dao = new CustomerDAOimpl();

    }


    Customer customer = new Customer(null,"Orlando","Gatti","gatti@gmail.com", LocalDate.of(1938,7,15),"Pedernera 724");
    @Test
    void prePersist() {
     dao.create(customer);

    }


    @Test

    void preRemove() {
        dao.deleteById(6L);

    }

    @Test
    void preUpdate() {


        Customer custo = dao.findById(6L);
        custo.setAdress("Pepiri 1245");
        dao.update(custo);


    }
}

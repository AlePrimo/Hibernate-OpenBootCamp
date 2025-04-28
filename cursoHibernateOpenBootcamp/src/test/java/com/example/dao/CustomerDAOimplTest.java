package com.example.dao;

import com.example.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;


class CustomerDAOimplTest {


    CustomerDAO dao;

    @BeforeEach
    void setUp() {
        dao = new CustomerDAOimpl();

    }

    @Test
    void findAll() {
        List<Customer> clientes = dao.findAll();
        System.out.println(clientes);
    }

    @Test
    void findById() {
        Customer cliente = dao.findById(1L);
        System.out.println(cliente);
    }

    @Test
    void create() {
        Customer cliente3 = new Customer(null,"Ciro","Perro","cirote@mail.com", LocalDate.of(2022,2,15),"Palma 4578");
        dao.create(cliente3);
    }

    @Test
    void update() {
        Customer cliente3 = new Customer(3L,"Cirote","Perrini","cirote2@gmail.com",LocalDate.of(2021,2,5),"Calle 17 nro :2628");
        dao.update(cliente3);

    }

    @Test
    void deleteById() {
        dao.deleteById(3L);
    }
}
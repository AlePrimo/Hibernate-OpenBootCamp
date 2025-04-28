package com.example.entities;

import com.example.dao.CustomerDAO;
import com.example.dao.CustomerDAOimpl;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CustomerTest {

    @Test

    void createTableCustomerTest(){

        Customer cliente1 = new Customer(null,"Octavio", "Perrutia","toto@hotmail.com",LocalDate.of(2002,5,14),"Salala 1542");
        Customer cliente2 = new Customer(null,"Valentin", "Pirulin","valentina@gmail.com",LocalDate.of(1947,2,24),"Ascasubi 2045");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(cliente1);
        session.save(cliente2);

        session.getTransaction().commit();

        session.close();
        sessionFactory.close();
        HibernateUtil.shutdown();


    }


    @Test
    void creditCardsTest() {

        Customer cliente3 = new Customer(null,"Raul", "Tarufetti","tarufe@gmail.com",LocalDate.of(1978,2,24),"Salala 4567");


        CustomerDAO dao = new CustomerDAOimpl();

        cliente3.getCreditCards().add("4521-1234-4578");
        cliente3.getCreditCards().add("7894-4562-7413");

        dao.create(cliente3);








    }
}

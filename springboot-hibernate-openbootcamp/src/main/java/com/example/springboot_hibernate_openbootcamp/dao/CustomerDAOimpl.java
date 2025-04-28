package com.example.springboot_hibernate_openbootcamp.dao;

import com.example.springboot_hibernate_openbootcamp.entities.Customer;


import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOimpl implements CustomerDAO{

    private Session session;


    public CustomerDAOimpl(Session session) {

        this.session = session;
    }



    @Override
    public List<Customer> findAll() {
        long start = System.currentTimeMillis();
        List<Customer> customers = session.createQuery("FROM Customer", Customer.class).list();
        long end = System.currentTimeMillis();
        System.out.println("El tiempo que tardo la consulta Customer findAll es : " +(end - start) +" ms");
        return customers;
    }

    @Override
    public Customer findById(Long id) {
        return session.find(Customer.class, id);
    }

    @Override
    public boolean createCustomer(Customer customer) {
        try {

            session.beginTransaction();
            session.persist(customer);
            session.getTransaction().commit();

        } catch (PersistenceException e) {
            e.printStackTrace();
            session.getTransaction().rollback();

            return false;
        }
        return true;
    }

    @Override
    public  Customer upDate(Customer customer) {
        try {

            session.beginTransaction();
            session.merge(customer);
            session.getTransaction().commit();

        } catch (PersistenceException e) {
            e.printStackTrace();
            session.getTransaction().rollback();


        }
        return customer;

    }

    @Override
    public boolean deleteCustomerById(Long id) {
       Customer customer= session.find(Customer.class, id);
        try {


            session.beginTransaction();
            session.remove(customer);
            session.getTransaction().commit();

        } catch (PersistenceException e) {
            e.printStackTrace();
            session.getTransaction().rollback();


            return false;
        }
        return true;


    }




}

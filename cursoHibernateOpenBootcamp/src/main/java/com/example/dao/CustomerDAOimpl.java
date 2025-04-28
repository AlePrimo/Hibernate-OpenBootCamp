package com.example.dao;

import com.example.entities.Customer;
import com.example.entities.Employee;
import com.example.util.HibernateUtil;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;

import java.util.List;

public class CustomerDAOimpl implements CustomerDAO{


    @Override
    public List<Customer> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Customer> customers = session.createQuery("FROM Customer", Customer.class).list();//ESTA CONSULTA ESTA HECHA EN HQL

        session.close();

        return customers;



    }

    @Override
    public Customer findById(Long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Customer customer = session.find(Customer.class, id);
        session.close();
        return customer;
    }



    @Override
    public Customer create(Customer customer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(customer);
            session.getTransaction().commit();

        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return customer;
    }

    @Override
    public Customer update(Customer customer) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(customer);
            session.getTransaction().commit();

        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return customer;





    }

    @Override
    public boolean deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Customer customer = session.find(Customer.class, id);
        try {
            session.beginTransaction();
            session.delete(customer);
            session.getTransaction().commit();
        }catch (PersistenceException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }finally {
            session.close();
        }

        return true;
    }
}

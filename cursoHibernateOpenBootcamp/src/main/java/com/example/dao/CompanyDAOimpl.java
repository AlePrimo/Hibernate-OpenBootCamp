package com.example.dao;


import com.example.entities.Company;
import com.example.entities.Employee;
import com.example.util.HibernateUtil;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CompanyDAOimpl implements CompanyDAO {



    @Override
    public Company findById(Long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Company company = session.find(Company.class, id);
        session.close();

        return company;


    }

    @Override
    public List<Employee> finfByIdEager(Long id) {
        List<Employee> employees = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Employee> query = session.createQuery("SELECT employees FROM Company c JOIN  c.employees WHERE c.id = :id", Employee.class);
        query.setParameter("id", id);

        employees = query.list();
        session.close();
        return employees;
    }





    @Override
    public Company createCompany(Company company) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(company);
            session.getTransaction().commit();

        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return company;
    }

    @Override
    public Company updateCompany(Company company) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(company);
            session.getTransaction().commit();

        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return company;
    }

    @Override
    public boolean deleteCompany(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Company company = session.find(Company.class, id);
        try {
            session.beginTransaction();
            session.delete(company);
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


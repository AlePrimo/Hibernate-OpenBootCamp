package com.example.dao;

import com.example.entities.Adress;

import com.example.util.HibernateUtil;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;

import java.util.List;


public class AdressDAOimpl implements AdressDAO{





    @Override
    public List<Adress> findAllAdresses() {


        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Adress> adresses = session.createQuery("FROM Adress", Adress.class).list();
        session.close();
        return adresses;


    }

    @Override
    public Adress findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Adress adress = session.find(Adress.class, id);
        session.close();

        return adress;
    }

    @Override
    public Adress createAdress(Adress adress) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(adress);
            session.getTransaction().commit();

        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return adress;




    }

    @Override
    public Adress updateAdress(Adress adress) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(adress);
            session.getTransaction().commit();

        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return adress;
    }

    @Override
    public boolean deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       Adress adress = session.find(Adress.class, id);
        try {
            session.beginTransaction();
            session.delete(adress);
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

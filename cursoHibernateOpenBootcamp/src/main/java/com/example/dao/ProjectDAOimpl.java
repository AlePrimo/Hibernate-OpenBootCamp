package com.example.dao;


import com.example.entities.Project;
import com.example.util.HibernateUtil;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;

import java.util.List;

public class ProjectDAOimpl implements ProjectDAO {


    @Override
    public List<Project> findAllProjects() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Project> projects = session.createQuery("FROM Project", Project.class).list();
        session.close();
        return projects;
    }

    @Override
    public Project findById(Long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Project project = session.find(Project.class, id);
        session.close();

        return project;



    }

    @Override
    public Project createProject(Project project) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(project);
            session.getTransaction().commit();

        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return project;




    }

    @Override
    public Project updateProject(Project project) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(project);
            session.getTransaction().commit();

        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return project;
    }

    @Override
    public boolean deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Project project= session.find(Project.class, id);
        try {
            session.beginTransaction();
            session.delete(project);
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

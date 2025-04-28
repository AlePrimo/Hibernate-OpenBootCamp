package com.example.dao;


import com.example.entities.Employee;
import com.example.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class EmployeeDAOimpl implements EmployeeDAO{


    @Override
    public void createEmployees(List<Employee> employees) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            session.beginTransaction(); // ABRIMOS LA TRANSACCION
            for (Employee employee : employees){
                session.persist(employee); //RECORREMOS LA LISTA PERSISTIENDO CADA ELEMENTO
            }
           session.getTransaction().commit();//HACEMOS EL COMMIT

        }catch (RuntimeException e){
         if (session.getTransaction() != null && session.getTransaction().isActive()) {
             session.getTransaction().rollback();
             throw e;
         }
        }finally {

            if(session != null){
                session.close();
            }
        }



    }
}

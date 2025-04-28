package com.example.entities;

import com.example.dao.EmployeeDAO;
import com.example.dao.EmployeeDAOimpl;
import com.example.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeTest {

    @Test

    void createEmployeeTableTest(){


        Employee empleado1 = new Employee(null, "Pepe", "Argento", "pepe@mail.com", 65, 1500000d, LocalDate.of(1947,1, 1),true);

        Employee empleado2 = new Employee(null, "Juan Carlos", "Petela", "petela@mail.com", 22, 500000d, LocalDate.of(2000,1, 1),false);

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(empleado1);
        session.save(empleado2);

        session.getTransaction().commit();

        session.close();
        sessionFactory.close();
        HibernateUtil.shutdown();



    }


    @Test
    void skillsTest() {
        //Employee empleado8 = new Employee(null, "Alberto", "Gutierrez", "guti@mail.com", 28, 150000d, LocalDate.of(1998,7, 1),true);

       //OPCION 1 PARA AGREGAR LA COLECCION AL OBJETO
        //PRIMERO CREAMOS LA LISTA Y LUEGO LA COMPLETAMOS PARA DESPUES PASARSELA AL OBJETO
        // List<String> skills = new ArrayList<>();
        //skills.add("Mecanic");
        //skills.add("Janitor");
        //skills.add("Pool cleaner");

        //empleado8.setSkills(skills);


        //OPCION 2 PARA AGREGAR LA COLECCION AL OBJETO
        //MAS SIMPLE, LLAMAMOS A LA LISTA ATRIBUTO DEL OBJETO Y LE AGREGAMOS LOS ELEMENTOS

        //empleado8.getSkills().add("Mecanic");
        //empleado8.getSkills().add("Cleaner");
        //empleado8.getSkills().add("Bombs Making");

        //POR ULTIMO PARA INGRESAR ESTE OBJETO EN LA BASE DE DATOS DIRECTAMENTE CREAMOS UN OBJETO DAO

        EmployeeDAO dao = new EmployeeDAOimpl();

        Employee employee = dao.findById(7L);


        employee.setCategory(EmployeeCategory.TRAINEE);
        dao.update(employee);











    }
}

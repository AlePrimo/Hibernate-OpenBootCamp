package com.example.springboot_hibernate_openbootcamp.config;


import jakarta.persistence.EntityManagerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaConfig {

    @Autowired //HACE QUE SPRING MANEJE ESTE OBJETO

    private EntityManagerFactory entityManagerFactory; // JPA

    @Bean

    public Session getSession(){

        System.out.println("Creando una bean session");
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);

        return sessionFactory.openSession();
    }


}

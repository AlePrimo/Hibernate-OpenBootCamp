package com.example.Proyecto_Final_Hibernate.dao;

import com.example.Proyecto_Final_Hibernate.entities.User;

import java.util.List;

public interface UserDAO {

    List<User> findAllActive();

}

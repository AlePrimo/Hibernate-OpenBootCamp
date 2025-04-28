package com.example.Proyecto_Final_Hibernate.dao;

import com.example.Proyecto_Final_Hibernate.entities.Tag;

import java.util.List;

public interface TagDAO {
    List<Tag> findByColorBlue();

}

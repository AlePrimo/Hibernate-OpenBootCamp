package com.example.Proyecto_Final_Hibernate.repository;

import com.example.Proyecto_Final_Hibernate.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository  extends JpaRepository<Tag,Long> {
}

package com.example.Proyecto_Final_Hibernate.repository;

import com.example.Proyecto_Final_Hibernate.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
}

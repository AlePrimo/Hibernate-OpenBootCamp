package com.example.Proyecto_Final_Hibernate.repository;

import com.example.Proyecto_Final_Hibernate.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository  extends JpaRepository<Task,Long> {
}

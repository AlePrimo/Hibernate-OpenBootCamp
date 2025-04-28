package com.example.Proyecto_Final_Hibernate.dao;

import com.example.Proyecto_Final_Hibernate.entities.Task;

import java.util.List;

public interface TaskDAO {
     List<Task> findByStatus (Boolean status);
}

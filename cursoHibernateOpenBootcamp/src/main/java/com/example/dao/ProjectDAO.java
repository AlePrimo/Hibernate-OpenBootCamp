package com.example.dao;

import com.example.entities.Adress;
import com.example.entities.Project;

import java.util.List;

public interface ProjectDAO {
    List<Project> findAllProjects();
    Project findById(Long id);
    Project createProject(Project project);
    Project updateProject(Project project);
    boolean deleteById(Long id);

}

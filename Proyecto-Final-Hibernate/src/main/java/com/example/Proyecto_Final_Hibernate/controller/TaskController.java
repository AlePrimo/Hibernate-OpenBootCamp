package com.example.Proyecto_Final_Hibernate.controller;

import com.example.Proyecto_Final_Hibernate.dao.TaskDAO;
import com.example.Proyecto_Final_Hibernate.entities.Task;
import com.example.Proyecto_Final_Hibernate.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private TaskRepository taskRepository;
    private TaskDAO taskDAO;


    public TaskController(TaskRepository taskRepository, TaskDAO taskDAO) {
        this.taskRepository = taskRepository;
        this.taskDAO = taskDAO;
    }
    @GetMapping("/api/tasks")
    private List<Task> findAll(){
        return this.taskRepository.findAll();
    }

    @GetMapping("/api/tasks/id/{id}")
    private Task findById(@PathVariable Long id){
        return this.taskRepository.findById(id).get();
    }

    @GetMapping("/api/tasks/byStatus/{status}")
    private List<Task> findByStatus(@PathVariable Boolean status){
        return this.taskDAO.findByStatus(status);
    }



    @PostMapping("/api/tasks")
    private Task create (@RequestBody Task task){
        return this.taskRepository.save(task);
    }

    @PutMapping("/api/tasks")
    private Task update (@RequestBody Task task){
        return this.taskRepository.save(task);
    }

@DeleteMapping("api/tasks/delete/{id}")
    private String delete(@PathVariable Long id){

    if(taskRepository.existsById(id)){
        this.taskRepository.deleteById(id);
        return "Borrado Exitoso";
    }
    return "No existe ese elemento ";

}










}

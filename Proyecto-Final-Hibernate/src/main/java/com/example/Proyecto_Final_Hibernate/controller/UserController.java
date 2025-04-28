package com.example.Proyecto_Final_Hibernate.controller;


import com.example.Proyecto_Final_Hibernate.dao.UserDAO;
import com.example.Proyecto_Final_Hibernate.entities.User;
import com.example.Proyecto_Final_Hibernate.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserRepository userRepository;
    private UserDAO userDAO;


    public UserController(UserRepository userRepository, UserDAO userDAO){

        this.userRepository = userRepository;
        this.userDAO = userDAO;
    }

    @GetMapping("/api/users")

    private List<User> findAll(){
        return this.userRepository.findAll();
    }



    @GetMapping("/api/users/id/{id}")

    private User findById(@PathVariable Long id){
        return this.userRepository.findById(id).get();
    }


    @PostMapping("/api/users")
    private User create(@RequestBody User user) {

        return this.userRepository.save(user);
    }


    @GetMapping("/api/users/active")
    private List<User> findAllActive(){

        return this.userDAO.findAllActive();
    }


    @PutMapping("/api/users")
    private User update(@RequestBody User user) {

        return this.userRepository.save(user);
    }

    @DeleteMapping("/api/users/{id}")
    private String delete(@PathVariable Long id){

        if(userRepository.existsById(id)){
            this.userRepository.deleteById(id);
            return "Borrado Exitoso";
        }
        return "No existe ese elemento ";
    }






}

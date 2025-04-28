package com.example.Proyecto_Final_Hibernate.controller;


import com.example.Proyecto_Final_Hibernate.dao.TagDAO;
import com.example.Proyecto_Final_Hibernate.entities.Tag;
import com.example.Proyecto_Final_Hibernate.repository.TagRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagController {

    private TagRepository tagRepository;
    private TagDAO tagDAO;

    public TagController(TagRepository tagRepository, TagDAO tagDAO) {
        this.tagRepository = tagRepository;
        this.tagDAO = tagDAO;
    }

    @GetMapping("/api/tags")
    private List<Tag> findAll(){
        return this.tagRepository.findAll();
    }
    @GetMapping("/api/tags/id/{id}")
    private Tag findById(@PathVariable Long id){
        return this.tagRepository.findById(id).get();
    }

    @PostMapping("/api/tags")
    private Tag create(@RequestBody Tag tag){
        return this.tagRepository.save(tag);
    }



    @PutMapping("/api/tags")
    private Tag update(@RequestBody Tag tag) {

        return this.tagRepository.save(tag);
    }

    @DeleteMapping("/api/tags/{id}")
    private String delete(@PathVariable Long id ){
        if(tagRepository.existsById(id)){
            this.tagRepository.deleteById(id);
            return "Borrado Exitoso";
        }
        return "No existe ese elemento ";

    }

    @GetMapping("/api/tags/byColorBlue")
    private List<Tag> findByColorBlue(){
        return this.tagDAO.findByColorBlue();
    }






}

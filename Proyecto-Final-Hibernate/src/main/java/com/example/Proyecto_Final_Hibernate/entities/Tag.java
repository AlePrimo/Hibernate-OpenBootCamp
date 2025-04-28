package com.example.Proyecto_Final_Hibernate.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tags")
@JsonIgnoreProperties({"taskList"})
public class Tag implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column
    @Enumerated(EnumType.STRING)
    private TagColor color;

    @ManyToMany(mappedBy = "tagList")
    private List<Task> taskList = new ArrayList<>();


    public Tag() {
    }

    public Tag(Long id, String name, TagColor color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TagColor getColor() {
        return color;
    }

    public void setColor(TagColor color) {
        this.color = color;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color=" + color +
                '}';
    }
}

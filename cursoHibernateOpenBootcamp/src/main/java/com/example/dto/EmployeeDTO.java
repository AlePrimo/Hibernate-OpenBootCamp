package com.example.dto;

public class EmployeeDTO {

    //LAS CLASES DTO(DATA TRANSFER OBJECTS) SON CLASES SIMPLIFICADAS O REDUCIDAS DE LAS CLASES ENTIDADES EXISTENTES
    //QUE SE USAN PARA INTERACTUAR CON EL FRONTEND MANEJANDO LA MENOR CANTIDAD DE ATRIBUTOS

    private Long id;
    private String email;


    public EmployeeDTO() {
    }


    public EmployeeDTO(Long id, String email) {
        this.id = id;

        this.email = email;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}

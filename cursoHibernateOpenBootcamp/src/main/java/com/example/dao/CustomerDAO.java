package com.example.dao;

import com.example.entities.Customer;


import java.util.List;

public interface CustomerDAO {


    //METODOS DE BUSQUEDA EN LA BASE DE DATOS

    List<Customer> findAll();
    Customer  findById(Long id);


    //METODO DE INSERCION DE UN CLIENTE EN LA BASE DE DATOS

  Customer create(Customer customer);

    //METODO DE ACTUALIZACION DE UN CLIENTE EN LA BASE DE DATOS

    Customer update(Customer customer);

    //METODO PARA BORRAR UN CLIENTE DE LA BASE DE DATOS POR ID

    boolean deleteById(Long id);//devuelve true si lo borra exitosamente







}

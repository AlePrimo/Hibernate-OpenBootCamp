package com.example.dao;

import com.example.entities.Adress;

import java.util.List;

public interface AdressDAO {

    List<Adress> findAllAdresses();
    Adress findById(Long id);
    Adress createAdress(Adress adress);
    Adress updateAdress(Adress adress);
    boolean deleteById(Long id);

}

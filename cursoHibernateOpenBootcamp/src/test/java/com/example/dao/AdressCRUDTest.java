package com.example.dao;

import com.example.entities.Adress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AdressCRUDTest {



    AdressDAO dao;

    @BeforeEach
    void setUp() {
        dao = new AdressDAOimpl();
    }

    @Test
    void findAllAdresses() {
        List<Adress> adresses = dao.findAllAdresses();
        System.out.println(adresses);

    }


    @Test
    void findById(){

        Adress adress1 = dao.findById(1L);
        System.out.println(adress1);

    };

    @Test
    void createAdress(){
        //Adress adress1 = new Adress(null,"Marzopiano","Cocomarola","Argentina");
        Adress adress2 = new Adress(null,"Bolivar 124","Esperanza","Argentina");
        Adress adress3 = new Adress(null,"SiempreViva 124","Springfield","US");
        Adress adress4 = new Adress(null,"Via Appia 12","Roma","Italia");
       dao.createAdress(adress3);
        //dao.createAdress(adress2);
        dao.createAdress(adress4);
    };

    @Test
    void updateAdress(){
        Adress adress1 = dao.findById(1L);
        adress1.setCity("transito");
        dao.updateAdress(adress1);
        System.out.println(adress1);

    };

    @Test
    void deleteById(){
        dao.deleteById(1L);
    };
















}

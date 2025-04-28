package com.example.dao;

import com.example.entities.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompanyTest {

    CompanyDAO dao;

    @BeforeEach
    void setDao(){
        dao = new CompanyDAOimpl();
    }

    @Test
    void createCompanyTest(){
        Company company1 = new Company(null,"DelPrimo","Lucas Delprimo S.A.");
        Company company2 = new Company(null,"Vanderlay","Maximus Vanderlay S.A.");
        dao.createCompany(company2);


    }





}

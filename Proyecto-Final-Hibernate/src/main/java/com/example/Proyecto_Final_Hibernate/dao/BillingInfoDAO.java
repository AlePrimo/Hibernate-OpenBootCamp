package com.example.Proyecto_Final_Hibernate.dao;

import com.example.Proyecto_Final_Hibernate.entities.BillingInfo;

import java.util.List;

public interface BillingInfoDAO {

    List<BillingInfo> findByCountry(String countrySearched);


}

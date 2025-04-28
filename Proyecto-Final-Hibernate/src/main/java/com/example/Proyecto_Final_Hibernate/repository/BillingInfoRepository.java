package com.example.Proyecto_Final_Hibernate.repository;

import com.example.Proyecto_Final_Hibernate.entities.BillingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingInfoRepository extends JpaRepository<BillingInfo, Long>{



}

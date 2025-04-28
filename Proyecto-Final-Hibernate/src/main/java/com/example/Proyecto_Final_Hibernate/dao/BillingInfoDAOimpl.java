package com.example.Proyecto_Final_Hibernate.dao;

import com.example.Proyecto_Final_Hibernate.entities.BillingInfo;
import com.example.Proyecto_Final_Hibernate.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class BillingInfoDAOimpl implements BillingInfoDAO{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<BillingInfo> findByCountry(String countrySearched) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BillingInfo> criteriaQuery = builder.createQuery(BillingInfo.class);
        Root<BillingInfo> root = criteriaQuery.from(BillingInfo.class);

        String likePattern = "%" + countrySearched.toLowerCase() + "%";
        Predicate byCountry = builder.like(builder.lower(root.get("country")), likePattern);

        criteriaQuery.select(root).where(byCountry);
        List<BillingInfo> billingInfoList = entityManager.createQuery(criteriaQuery).getResultList();

        return billingInfoList;
    }


}

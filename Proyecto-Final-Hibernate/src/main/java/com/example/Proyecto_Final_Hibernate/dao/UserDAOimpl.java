package com.example.Proyecto_Final_Hibernate.dao;

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
public class UserDAOimpl implements UserDAO{


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> findAllActive() {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery <User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        Predicate isActive = builder.isTrue(root.get("active"));

        criteriaQuery.select(root).where(isActive);
        List<User> userList = entityManager.createQuery(criteriaQuery).getResultList();

        return userList;
    }







}

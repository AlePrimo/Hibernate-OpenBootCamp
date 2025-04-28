package com.example.Proyecto_Final_Hibernate.dao;

import com.example.Proyecto_Final_Hibernate.entities.Tag;

import com.example.Proyecto_Final_Hibernate.entities.TagColor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagDAOimpl implements TagDAO{


    @PersistenceContext
    private EntityManager entityManager;




    @Override
    public List<Tag> findByColorBlue() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> criteriaQuery = builder.createQuery(Tag.class);
        Root<Tag> root = criteriaQuery.from(Tag.class);

        Predicate byColor = builder.equal(root.get("color"), TagColor.BLUE);

        criteriaQuery.select(root).where(byColor);
        List<Tag> tagList = entityManager.createQuery(criteriaQuery).getResultList();

        return tagList;
    }
}

package com.example.Proyecto_Final_Hibernate.dao;

import com.example.Proyecto_Final_Hibernate.entities.Task;
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
public class TaskDAOimpl implements  TaskDAO{



    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Task> findByStatus(Boolean status) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> criteriaQuery = builder.createQuery(Task.class);
        Root<Task> root = criteriaQuery.from(Task.class);

        Predicate byStatus = builder.equal(root.get("finished") ,status);

        criteriaQuery.select(root).where(byStatus);
         return  entityManager.createQuery(criteriaQuery).getResultList();


    }
}

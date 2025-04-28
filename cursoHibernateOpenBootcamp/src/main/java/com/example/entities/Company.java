package com.example.entities;

import com.example.dao.CompanyDAO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name = "fantasy_name", unique = true)
    private String fantasyName;
    @Column(name = "legal_name" , unique = true)
    private String legalName;


    @OneToMany(mappedBy = "company")
     private List<Employee> employees = new ArrayList<>();





    public Company() {
    }

    public Company(Long id, String fantasyName, String legalName) {
        this.id = id;
        this.fantasyName = fantasyName;
        this.legalName = legalName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }


    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", fantasyName='" + fantasyName + '\'' +
                ", legalName='" + legalName + '\'' +
                '}';
    }






}

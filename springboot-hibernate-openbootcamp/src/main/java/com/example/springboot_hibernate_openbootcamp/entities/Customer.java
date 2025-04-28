package com.example.springboot_hibernate_openbootcamp.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;



@Entity
@Table(name = "customers")

public class Customer implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "country_code")
    private String countryCode;

    public Customer() {
    }

    public Customer(Long id, String fullName, LocalDate birthDate, String countryCode) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.countryCode = countryCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthDate=" + birthDate +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}

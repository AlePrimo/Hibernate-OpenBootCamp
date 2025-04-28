package com.example.springboot_hibernate_openbootcamp.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    private Integer age;
    @Column(unique = true)
    private String email;
    @Column(name = "register_date")
    private LocalDate registerDate;
    private Boolean active;
    private Double salary;

    public Employee() {
    }

    public Employee(Long id, String fullName, Integer age, String email, LocalDate registerDate, Boolean active, Double salary) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.registerDate = registerDate;
        this.active = active;
        this.salary = salary;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", registerDate=" + registerDate +
                ", active=" + active +
                ", salary=" + salary +
                '}';
    }
}

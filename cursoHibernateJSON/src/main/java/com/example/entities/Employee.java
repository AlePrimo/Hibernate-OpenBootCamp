package com.example.entities;


import com.example.converters.JsonConverter;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Map;


@Entity  //ANOTACION PARA QUE HIBERNATE SEPA QUE ES UNA ENTIDAD
@Table(name = "employees") // ANOTACION PARA QUE HIBERNATE SEPA QUE ES UNA TABLA DE LA BASE DE DATOS(OPCIONAL)


public class Employee implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @Column(name = "first_name", length = 15, nullable = false)
    private String firstName;
   @Column(name = "last_name", length = 15, nullable = false)
    private String lastName;
   @Column(unique = true)
    private String email;

   @Convert(converter = JsonConverter.class)
   private Map<String, Object> json;





   public Employee() {}

    public Employee(Long id, String firstName, String lastName, String email, Map<String, Object> json) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.json = json;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, Object> getJson() {
        return json;
    }

    public void setJson(Map<String, Object> json) {
        this.json = json;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", json=" + json +
                '}';
    }
}

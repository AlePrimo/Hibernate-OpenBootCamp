package com.example.entities;


import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
* CLASE QUE REPRESENTA LA TABLA EN BASE DE DATOS
* */

@Entity  //ANOTACION PARA QUE HIBERNATE SEPA QUE ES UNA ENTIDAD
@Table(name = "employees") // ANOTACION PARA QUE HIBERNATE SEPA QUE ES UNA TABLA DE LA BASE DE DATOS(OPCIONAL)
@NamedQuery(name = "Employee.seniors", query = "FROM Employee e WHERE e.category = SENIOR")
@NamedQuery(name = "Employee.married", query = "FROM Employee e WHERE e.married = true")

public class Employee implements Serializable {


    /*EN HIBERNATE CONVIENE QUE LAS CLASES QUE REPRESENTAN UNA TABLA IMPLEMENTEN LA CLASE SERIALIZABLE*/


    //CADA ATRIBUTO QUE LE DEMOS A LA CLASE REPRESENTARA UNA COLUMNA EN LA TABLA DE LA BASE DE DATOS

    //EL PRIMER ATRIBUTO QUE SE LE VA A ASIGNAR A LAS ENTIDADES QUE REPRESENTEN UNA TABLA SERA EL DE LA
    //CLAVE PRIMARIA , QUE EN ESTE CASO SERA UN ID, PARA ELLO DEBEMOS INDICAR CON UNA ANOTACION
    //LUEGO DEBEMOS INDICARLE COMO SE GENERARA ESE VALOR DE ID Y DE QUE MANERA, EN ESTE CASO SERA IDENTITY


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //SE USA EL TIPO DE DATO ENVOLTORIO Y NO EL PRIMITIVO(long) PORQUE  ESTE PERMITE QUE EL CAMPO SEA NULO
   @Column(name = "first_name", length = 15, nullable = false)
    private String firstName;
   @Column(name = "last_name", length = 15, nullable = false)
    private String lastName;



    @Column(unique = true)//CON ESTA ANOTACION LE INDICAMOS A HIBERNATE QUE EL ATRIBUTO SERA UNA COLUMNA Y QUE EL DATO NO PODRA REPETIRSE
    private String email;

    private Integer age;
    private Double salary;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    private Boolean married;

    @ElementCollection
    private List<String> skills = new ArrayList<>();


    @Column
    @Enumerated(EnumType.STRING)
    private EmployeeCategory category;


    @OneToOne
     private Adress adress;

    @OneToMany
     private List<Customer> customers = new ArrayList<>();

    @ManyToOne
     private Company company;



    @ManyToMany
    private List<Project> projects = new ArrayList<>();




    

    public Employee() {

    }


    public Employee(Long id, String firstName, String lastName, String email, Integer age, Double salary, LocalDate birthDate, Boolean married) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.salary = salary;
        this.birthDate = birthDate;
        this.married = married;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }


    public EmployeeCategory getCategory() {
        return category;
    }

    public void setCategory(EmployeeCategory category) {
        this.category = category;
    }


    public Adress getAdress() {

        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;

    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", birthDate=" + birthDate +
                ", married=" + married +
                '}';
    }
}

package com.example.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "customers")
@Audited (targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)//ANOTACIOM DE HIBERNATE ENVERS PARA AUDITAR LOS MOVIMIENTOS DE UNA ENTIDAD EN BASE DE DATOS
//COMO LA ENTIDAD ESTA RELACIONADA CON OTRAS ENTIDADES, SE DEBE ESPECIFICAR EL NOT AUDITED RELATION O HACER QUE LAS OTRAS ENTIDADES TAMBIEN SEAN AUDITED
//ESTA ANOTACION CREA TABLAS ESPECIALES EN LA BASE DE DATOS CON LOS MOVIMIENTOS QUE SE HAYAN HECHO SOBRE ESA ENTIDAD
//--------MUY IMPORTANTE : AUDITED TAMBIEN NOS PERMITE REALIZAR AUDITORIA Y SEGUIMIENTO DE LOS MOVIMIENTOS DE UNO O MAS ATRIBUTOS SOLAMENTE ,
//SIN QUE SE HAGA  DE TODA LA ENTIDAD





public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 15, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 15, nullable = false)
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    private String adress;

    @Column(name = "creation_date")
    @CreationTimestamp
    private LocalDate creationDate;
//    @CreationTimestamp es una anotación de Hibernate que se utiliza para marcar un campo en una entidad como el momento en que se creó el
//    registro en la base de datos. Hibernate se encarga de establecer automáticamente el valor de este campo cuando se inserta un nuevo registro.

    @Column(name = "edit_date")
    private LocalDateTime edit_Date;



    @ElementCollection //USAMOS UN SET CUANDO NECESITAMOS QUE NO SE REPITAN ELEMENTOS DENTRO DE LA COLECCION
    private Set<String> creditCards = new HashSet<>();

    @OneToMany
    private List<Project> projects;


    public Customer() {}

    public Customer(Long id, String firstName, String lastName, String email, LocalDate birthDate, String adress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.adress = adress;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Set<String> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(Set<String> creditCards) {
        this.creditCards = creditCards;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getEdit_Date() {
        return edit_Date;
    }

    public void setEdit_Date(LocalDateTime edit_Date) {
        this.edit_Date = edit_Date;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", adress='" + adress + '\'' +
                '}';
    }












    //METODOS DE EVENTOS O LIFECYCLE CALLBACKS : ESTOS METODOS EJECUTAN UNA FUNCION ANTES O DESPUES  DE CREAR, ACTUALIZAR O BORRAR UN REGISTRO,
    // POR LO TANTO SI QUEREMOS QUE ALGO SE CONFIGURE, EJECUTE O SE REALICE AUTOMATICAMENTE , LO IMPLEMENTAREMOS EN ALGUNO
    //DE ESTOS METODOS.
    //@PrePersist-@PreUpdate-@PreRemove-----> EJECUTAN ANTES
    //@PostPersist-@PostUpdate-@PostRemove-----> EJECUTAN LUEGO
    //

    //METODO PREPERSIST SE EJECUTA SIEMPRE ANTES DE INSERTAR UNA ENTIDAD EN BASE DE DATOS
    @PrePersist

    public void prePersist(){
        this.creationDate = LocalDate.now();
        System.out.println("prePersist");
    }

    //METODO PREREMOVE SE EJECUTA SIEMPRE ANTES DE ELIMINAR UNA ENTIDAD DE LA BASE DE DATOS
    @PreRemove

    public void preRemove(){

        System.out.println("preRemove");

    }



//METODO PREUPDATE SE EJECUTA SIEMPRE ANTES DE ACTUALIZAR UNA ENTIDAD DE LA BASE DE DATOS
    @PreUpdate

    public void preUpdate(){

        this.edit_Date = LocalDateTime.now();


        System.out.println("preUpdate");
    }









}

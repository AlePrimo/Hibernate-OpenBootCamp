package com.example.entities;









import java.io.Serializable;



@Entity  //ANOTACION PARA QUE HIBERNATE SEPA QUE ES UNA ENTIDAD
@Table(name = "employees") // ANOTACION PARA QUE HIBERNATE SEPA QUE ES UNA TABLA DE LA BASE DE DATOS(OPCIONAL)


public class Employee implements Serializable {


    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) //CON ESTE GENERADOR SE TARDO 21 seg EN CARGAR 50000
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_sequence")
    @SequenceGenerator(name = "employee_sequence", sequenceName ="employee_sequence", allocationSize = 100 )
    //CON ESTA ESTRATEGIA DE GENERACION DE ID CUSTOMIZADA SE CONECTA A BASE DE DATOS PARA COMPROBAR LA SECUENCIA CADA
    // 100 REGISTROS Y NO CON CADA REGISTRO COMO EN EL OTRO GENERADOR, ESTO OPTIMIZA LOS TIEMPOS
    private Long id;
   @Column(name = "first_name", length = 15, nullable = false)
    private String firstName;
   @Column(name = "last_name", length = 15, nullable = false)
    private String lastName;
   @Column(unique = true)
    private String email;







   public Employee() {}

    public Employee(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

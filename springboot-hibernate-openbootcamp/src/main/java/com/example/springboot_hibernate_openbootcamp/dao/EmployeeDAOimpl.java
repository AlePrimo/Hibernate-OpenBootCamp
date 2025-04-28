package com.example.springboot_hibernate_openbootcamp.dao;

import com.example.springboot_hibernate_openbootcamp.dto.EmployeeDTO;
import com.example.springboot_hibernate_openbootcamp.entities.Employee;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class EmployeeDAOimpl implements EmployeeDAO {

    private final Session session;  //HIBERNATE


    public EmployeeDAOimpl(Session session) {

        this.session = session;
    }

    @Override
    public List<Employee> findAll() {
        long start = System.currentTimeMillis();
        List<Employee> employees = session.createQuery("FROM Employee", Employee.class).list();
        long end = System.currentTimeMillis();
        System.out.println("El tiempo que tardo la consulta Employee es : " +(end - start) +" ms");
        return employees;
    }

    @Override
    public Employee findById(Long id) {

        return session.find(Employee.class, id);
    }

    @Override
    public Employee findByEmail(String email) {
        Query<Employee> query = session.createQuery("FROM Employee WHERE email = :email", Employee.class);
        query.setParameter("email", email);
        Employee employeeX = query.getSingleResult();


        return employeeX;
    }

    @Override
    public boolean createEmployee(Employee employee) {
        try {

            session.beginTransaction();
            session.persist(employee);
            session.getTransaction().commit();

        } catch (PersistenceException e) {
            e.printStackTrace();
            session.getTransaction().rollback();

            return false;
        }
        return true;
    }

    @Override
    public boolean deleteEmployeeById(Long id) {
        Employee employee = session.find(Employee.class, id);
        try {


            session.beginTransaction();
            session.remove(employee);
            session.getTransaction().commit();

        } catch (PersistenceException e) {
            e.printStackTrace();
            session.getTransaction().rollback();


            return false;
        }
        return true;
    }

    @Override
    public List<EmployeeDTO> findAllDTO() {

        long start = System.currentTimeMillis();
        List<EmployeeDTO> employeeDTOS = session.createQuery("SELECT new com.example.springboot_hibernate_openbootcamp.dto.EmployeeDTO(e.id, e.email) FROM Employee e").list();
        long end = System.currentTimeMillis();
        System.out.println("El tiempo que tardo la consulta DTO es : " +(end - start) +" ms");

        return employeeDTOS;

    }

    @Override
    public Employee findByFullName(String fullName) {
        long start = System.currentTimeMillis();
       Query<Employee> query = session.createQuery("FROM Employee e WHERE e.fullName = :fullName",Employee.class);
       query.setParameter("fullName", fullName);
       Employee employee = query.getSingleResult();
       long end = System.currentTimeMillis();
        System.out.println("El tiempo que tardo la consulta es : " +(end - start) +" ms");

        return employee;
    }


    @Override
    public Employee upDate(Employee employee) {
        try {

            session.beginTransaction();
            session.merge(employee);
            session.getTransaction().commit();

        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();

        }


        return employee;
    }




                                /* -------------------PAGINACION-------------------------- */


   /* Paginacion es la cantidad de resultados a mostrar en una pagina, en este caso se pueden crear metodos a traves de
   JAVA para mostrar por ejemplo los resultados de la ultima pagina de una tabla de una base de datos */


    @Override
    public List<Employee> findAllLastPage() {

        int size = 20;  /* CANTIDAD DE REGISTROS POR PAGINA*/
        String countHQL = "SELECT COUNT(e.id) FROM Employee e"; /*QUERY EN HQL QUE NOS PERMITE CONTAR LA CANTIDAD DE EMPLEADOS USANDO LA FUNCION COUNT*/

        Long countResult = (Long) session.createQuery(countHQL).uniqueResult(); /*CON ESTA SENTENCIA CONSEGUIMOS EL NUMERO TOTAL DE EMPLEADOS*/


        int lastPageNumber = (int) Math.ceil((double) countResult / size); /*AQUI CALCULAMOS EL NUMERO DE LA ULTIMA PAGINA ,
        DIVIDIMOS LA CANTIDAD DE EMPLEADOS POR LA CANTIDAD DE REGISTROS QUE QUEREMOS POR PAGINA , COMO ESTE NUMERO
        PUEDE LLEGAR A SER DECIMAL USAMOS LA FUNCION MATH.CEIL QUE NOS DEVUELVE UN RESULTADO REDONDEADO HACIA ARRIBA,
        POR EJEMPLO SI LA DIVISION ES 4.6 NOS DEVUELVE 5.0. EN ESTE CASO COMO ESTAMOS DIVIDIENDO UN LONG POR UN INT
        DEBEMOS CASTEAR EL RESULTADO A DOUBLE Y LUEGO A INT PARA OBTENER EL RESULTADO FINAL*/

        Query query = session.createQuery("FROM Employee"); /*RECUPERAMOS TODOS LOS EMPLEADOS*/

        query.setFirstResult((lastPageNumber - 1) * size); /*A TRAVES DE LA FUNCION setFirstResult() LE INDICAMOS
        A LA QUERY A PARTIR DE DONDE EMPEZAR, EN ESTE CASO LO QUE HACEMOS ES AL RESTARLE  1 A LA CANTIDAD DE PAGINAS
        OBTENEMOS EL NUMERO DE LA ANTEULTIMA PAGINA QUE AL MULTIPLICARLO POR LA CANTIDAD DE REGISTROS POR PAGINA
        OBTENEMOS LA CANTIDAD DE EMPLEADOS QUE HAY HASTA ESA PAGINA QUE SERIA A PARTIR DE DONDE DEBEMOS RECUPERAR
        DATOS */

        query.setMaxResults(size);/*EN ESTE CASO EL NUMERO MAXIMO DE RESULTADOS QUE QUEREMOS RECUPERAR ES EL NUMERO
        DE REGISTROS POR PAGINA , O SEA LOS ULTIMOS 20 EMPLEADOS*/


        return (List<Employee>) query.list();

    }



    /*-------------------------------ANOTACION TRANSACTIONAL-------------------------------------------------------*/
    /*Esta anotacion se usa para Spring y no para Hibernate, puede ir tanto antes de crear la clase(arriba) como antes de un metodo que requiera
     hacer una operacion de transaccion , como por ejemplo create delete o update.
     Al poner la anotacion @Transactional antes de los metodos nos evitamos tener que poner
            session.beginTransaction();
            session.persist(employee);
            session.getTransaction().commit();

                   Solamente hay que poner : entityManager.persist(objeto);
            */



}


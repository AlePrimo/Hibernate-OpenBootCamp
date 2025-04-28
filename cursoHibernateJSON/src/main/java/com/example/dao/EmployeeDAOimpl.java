package com.example.dao;


import com.example.entities.Employee;
import com.example.util.HibernateUtil;
import jakarta.persistence.PersistenceException;


import org.hibernate.Session;

import org.hibernate.query.Query;


import java.util.List;



public class EmployeeDAOimpl implements EmployeeDAO{



    //METODOS CRUD CON HIBERNATE

    @Override
    public List<Employee> findAll() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Employee> empleados = session.createQuery("FROM Employee", Employee.class).list();//ESTA CONSULTA ESTA HECHA EN HQL

        session.close();

        return empleados;


        
    }




    @Override
    public Employee findById(Long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Employee employee = session.find(Employee.class, id);

        session.close();

        return employee;
    }

    @Override
    public Employee findByIdEager(Long id) {


        //ESTE METODO CARGA UNICAMENTE LOS DATOS QUE NOSOTROS LE PASEMOS EN LA QUERY, EN ESTE CASO SERIA LA LISTA DE CLIENTES DE ESE EMPLEADO
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Employee> query = session.createQuery("SELECT DISTINCT e FROM Employee e JOIN FETCH e.customers WHERE e.id = :id", Employee.class);
        query.setParameter("id", id);

        Employee employeeX = query.getSingleResult();
        session.close();
        return employeeX;

    }


    @Override
    public Employee findByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Employee> query = session.createQuery("FROM Employee WHERE email = :email", Employee.class);
        query.setParameter("email", email);
        Employee employee = query.uniqueResult();

        //EN ESTE METODO CREAMOS UNA QUERY CON EL FILTRO QUE QUERAMOS APLICAR, EN ESTE CASO EL EMAIL, SI FUESEN VARIOS
        // FILTROS DEBEMOS AGREGAR EL AND EN LA CONSULTA HQL DE ARRIBA.
        //EN ESTE CASO COMO EL EMAIL ES UNICO PARA CADA EMPLEADO LA BUSQUEDA NOS DEVOLVERA UN SOLO OBJETO EMPLEADO,
        //SI EL FILTRO APLICADO EN LA CONSULTA NOS DEVOLVIESE VARIOS OBJETOS DEBERIAMOS RETORNARLOS EN UNA LISTA
        // O SEA QUE DEBERIAMOS PONER query.list() Y ASIGNARLE ESE RESULTADO A UNA LISTA, TAMBIEN LA DEVOLUCION DEL METODO
        // DESPUES DE PUBLIC DEBERIA SER LIST
        session.close();

        return employee;
    }



    @Override
    public Employee create(Employee employee) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();

        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();//ESTE METODO ROLLBACK RETROCEDE EN LOS CAMBIOS PARA QUE NO GENEREN PROBLEMAS
        }


        session.close();


        return employee;
    }

    @Override
    public Employee update(Employee employee) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();
            session.update(employee);
            session.getTransaction().commit();

        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();//ESTE METODO ROLLBACK RETROCEDE EN LOS CAMBIOS PARA QUE NO GENEREN PROBLEMAS

        }


        session.close();


        return employee;


    }

    @Override
    public boolean deleteById(Long id) {


        Session session = HibernateUtil.getSessionFactory().openSession();
        Employee employee = session.find(Employee.class, id);
        try {


            session.beginTransaction();
            session.delete(employee);
            session.getTransaction().commit();

        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();//ESTE METODO ROLLBACK RETROCEDE EN LOS CAMBIOS PARA QUE NO GENEREN PROBLEMAS
            return false;
        }finally {
            session.close();
        }





        return true;
    }

    @Override
    public Long count() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Long employeesNumber = (Long) session.createQuery("SELECT count(e) FROM Employee e").getSingleResult();

       session.close();

        return employeesNumber;

    }







}

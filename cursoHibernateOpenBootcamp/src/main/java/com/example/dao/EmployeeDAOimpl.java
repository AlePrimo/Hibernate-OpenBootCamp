package com.example.dao;


import com.example.dto.EmployeeDTO;
import com.example.entities.Employee;
import com.example.entities.EmployeeCategory;
import com.example.util.HibernateUtil;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.*;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.math.BigInteger;
import java.util.ArrayList;
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

    @Override
    public List<Employee> findEmployeesSenior() {


        Session session = HibernateUtil.getSessionFactory().openSession();

         List<Employee> seniorEmployees = session.createNamedQuery("Employee.seniors", Employee.class).list();

         session.close();

        return seniorEmployees;
    }

    @Override
    public List<Employee> findEmployeeMarried() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Employee> marriedEmployees = session.createNamedQuery("Employee.married", Employee.class).list();

        session.close();

        return marriedEmployees;



    }


    //METODOS CON CRITERIA






    @Override
    public List<Employee> findAllCriteria() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        //PRIMERO SE CREA LA CONSULTA CRITERIA
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);

        criteria.select(criteria.from(Employee.class));

        List<Employee> employees = session.createQuery(criteria).list();


        session.close();

        return employees;
    }

    @Override
    public Employee findByIdCriteria(Long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();


        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
        //ROOT ES LA TABLA EN DONDE DEBEMOS HACER LA BUSQUEDA
        Root<Employee> root = criteria.from(Employee.class);
        //PREDICATE ES EL FILTRO QUE LE VAMOS A PASAR A CRITERIA
        Predicate filter = builder.equal(root.get("id"), id);
        //OBJETO CRITERIA CON EL FILTRO DE BUSQUEDA
        criteria.select(root).where(filter);
        //LE PASAMOS A SESSION EL OBJETO CRITERIA Y HACEMOS LA BUSQUEDA
         Employee employee = session.createQuery(criteria).getSingleResult();
         session.close();
        return employee;
    }

    @Override
    public List<Employee> findByLastNameLikeCriteria(String lastName) {
        Session session = HibernateUtil.getSessionFactory().openSession();


        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);

        Root<Employee> root = criteria.from(Employee.class);

        Predicate filter = builder.equal(root.get("lastName"), lastName);
        //SI QUISIERAMOS HACER UNA CONSULTA DONDE QUEREMOS RECUPERAR LOS APELLIDOS QUE CONTENGAN UN STRING DETERMINADO SERIA ASI :
        //Predicate filter1 = builder.like(root.get("lastName"), "%" + lastName+ "%");

        criteria.select(root).where(filter);

        List<Employee> employees = session.createQuery(criteria).list();
        session.close();
        return employees;

    }

    @Override
    public List<Employee> findByOlderAgeCriteria(Integer age) {

        Session session = HibernateUtil.getSessionFactory().openSession();


        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);

        Root<Employee> root = criteria.from(Employee.class);

        Predicate filter = builder.greaterThanOrEqualTo(root.get("age"), age);

        criteria.select(root).where(filter);

        List<Employee> employees = session.createQuery(criteria).list();
        session.close();
        return employees;






    }

    @Override
    public List<Employee> findByBetweenAgeCriteria(Integer ageMin, Integer ageMax) {

        Session session = HibernateUtil.getSessionFactory().openSession();


        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);


        Root<Employee> root = criteria.from(Employee.class);

        Predicate filter = builder.between(root.get("age"), ageMin, ageMax);

        criteria.select(root).where(filter);

        List<Employee> employees = session.createQuery(criteria).list();
        session.close();
        return employees;






    }

    @Override
    public List<Employee> findByAgeBetweenAndCategory(Integer ageMin, Integer ageMax, EmployeeCategory category) {


        Session session = HibernateUtil.getSessionFactory().openSession();
        //EL OBJETO BUILDER ES UNA SUBCLASE DEL OBJETO SESSION QUE SE USA PARA CONSTRUIR LAS CONSULTAS CRITERIA
        CriteriaBuilder builder = session.getCriteriaBuilder();
        //EL OBJETO CRITERIA ES LA CONSULTA EN SI, EN ESTE CASO SE ESPECIFICA  DE QUE ENTIDAD O CLASE SE HARA ESA CONSULTA
        CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
        //EL OBJETO ROOT REPRESENTA LA TABLA DONDE SE HARA LA CONSULTA
        Root<Employee> root = criteria.from(Employee.class);


        //PRIMER FILTRO O PREDICADO (EDADES)
        Predicate ageFilter = builder.between(root.get("age"), ageMin, ageMax);
        //SEGUNDO FILTRO PARA LA CATEGORIA
        Predicate categoryFilter = builder.equal(root.get("category"), category);

        //USANDO EL METODO AND DE BUILDER PODEMOS ELEGIR LOS FILTROS QUE QUERAMOS
        // Y LUEGO PASARSELOS A CRITERIA QUE SELECCIONARA DE ROOT LOS ELEMENTOS QUE CUMPLAN LAS CONDICIONES DE LOS FILTROS
        Predicate finalFilter = builder.and(ageFilter, categoryFilter);
        criteria.select(root).where(finalFilter);

        List<Employee> employeeList = session.createQuery(criteria).list();

        session.close();
        return employeeList;
    }

    @Override
    public Integer calcAvgAgeCriteria() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        //SE CREA UNA CRITERIA QUE APUNTA A RECUPERAR UN NUMERO QUE VA A SER EL PROMEDIO DE LAS EDADES
        CriteriaQuery<Double> criteria = builder.createQuery(Double.class);
        //EL ROOT SIGUE APUNTANDO A LA TABLA EMPLOYEE
        Root<Employee> root = criteria.from(Employee.class);
        //EM ESTE CASO CREAMOS UNA EXPRESION Y NO UN FILTRO QUE LO QUE VA A HACER ES CALCULAR EL PROMEDIO DEL CAMPO AGE
        // EN LA TABLA EMPLOYEE
        Expression<Double> calcAvg = builder.avg(root.get("age"));
        //LUEGO ESTA EXPRESION SE LA PASAMOS COMO PARAMETRO A LA CONSULTA CRITERIA

         criteria.select(calcAvg);

         //Y TENIEMDO LA CONSULTA CRITERIA HAY QUE PASARLA COMO PARAMETRO PARA SESSION.CREATEQUERY

        Double resultado = session.createQuery(criteria).getSingleResult();

        session.close();




        return (int) Math.round(resultado);
    }







    //METODOS CON QUERIES NATIVAS
    @Override
    public Employee findByIdNative(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();


        NativeQuery<Employee> query = session.createNativeQuery("SELECT * FROM employees WHERE id = :id", Employee.class);
        query.setParameter("id", id);


        Employee employee = query.getSingleResult();


        session.close();
        return employee;
    }

    @Override
    public List<Employee> findAllNative() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Employee> employees = session.createNativeQuery("SELECT * FROM employees ", Employee.class).list();
        session.close();
        return employees;

    }

    @Override
    public List<EmployeeDTO> findAllProjections() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> employees = session.createNativeQuery("SELECT id, email FROM employees").list();

        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Object[] employee: employees){

            Long id = employee[0] instanceof BigInteger ? ((BigInteger) employee[0]).longValue() : (Long) employee[0];
            String email = (String) employee[1];
            employeeDTOS.add(new EmployeeDTO(id, email));

        }



        session.close();
        return employeeDTOS;
    }


}

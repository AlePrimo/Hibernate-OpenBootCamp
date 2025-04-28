package com.example.dao;



import com.example.entities.Employee;

import java.util.List;



public interface EmployeeDAO {

    //METODOS DE BUSQUEDA EN LA BASE DE DATOS

    List<Employee> findAll();
    Employee findById(Long id);
    Employee findByIdEager(Long id);
    Employee findByEmail(String emalil);

    //METODO DE INSERCION DE UN EMPLEADO EN LA BASE DE DATOS

    Employee create(Employee employee);

    //METODO DE ACTUALIZACION DE UN EMPLEADO EN LA BASE DE DATOS

    Employee update(Employee employee);

    //METODO PARA BORRAR UN EMPLEADO DE LA BASE DE DATOS POR ID

    boolean deleteById(Long id);//devuelve true si lo borra exitosamente


    //  METODO COUNT PARA OBTENER LA CANTIDAD DE EMPLEADOS O REGISTROS DE UNA ENTIDAD QUE EXISTE EN UNA TABLA DE BASE DE DATOS
    //ESTE METODO ES IMPORTANTE PARA OBTENER LA CANTIDAD SIN TENER QUE HACER UNA CONSULTA SOBRE TODOS LOS DATOS Y GENERAR UN GASTO DE RECURSOS

    Long count();











}

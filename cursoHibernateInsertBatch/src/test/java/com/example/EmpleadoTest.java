package com.example;

import com.example.dao.EmployeeDAO;
import com.example.dao.EmployeeDAOimpl;
import com.example.entities.Employee;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoTest {






//    @Test
//    void saveEmployees() {
//
//        EmployeeDAO dao = new EmployeeDAOimpl();
//        List <Employee> employeeList = new ArrayList<>();
//
//        for (int i = 1; i <= 50000; i++ ){
//            employeeList.add(new Employee(null,"Juan", "Empleado"+i,"empleado"+i+"@mail.com"));
//        }
//
//
//        dao.createEmployees(employeeList);
//
//    }//CON TARDO 21 SEGUNDOS EN INSERTAR LOS EMPLEADOS




    @Test
    void saveEmployeesWithBatch() {

        EmployeeDAO dao = new EmployeeDAOimpl();
        List<Employee> employeeList = new ArrayList<>();

        for (int i = 1; i <= 50000; i++) {
            employeeList.add(new Employee(null, "Juan", "Empleado" + i, "empleado" + i + "@mail.com"));
        }


        dao.createEmployees(employeeList);


    } //CON LA CONFIGURACION DE BATCH Y EL NUEVO GENERADOR DE ID TARDO 4 SEGUNDOS















}


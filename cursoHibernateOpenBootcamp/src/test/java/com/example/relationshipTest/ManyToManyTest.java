package com.example.relationshipTest;

import com.example.dao.*;
import com.example.entities.Customer;
import com.example.entities.Employee;
import com.example.entities.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManyToManyTest {

    EmployeeDAO daoEmplo;
    CustomerDAO daoCusto;
    ProjectDAO daoProject;

    @BeforeEach
    void setUp(){
        daoEmplo = new EmployeeDAOimpl();
        daoCusto = new CustomerDAOimpl();
        daoProject = new ProjectDAOimpl();

    }


    @Test

    void ManyToManyTest1(){


        Project projectUno = new Project(null,"Proyecto Ambassador",350000D, LocalDate.of(2024,12,1));

        //daoProject.createProject(projectUno);

       //daoProject.findById(1L).setCustomer(daoCusto.findById(1L));
//
//        List<Project> custoProjects = new ArrayList<>();
//
//        custoProjects.add(daoProject.findById(1L));
//
//        Customer customer1 = daoCusto.findById(1L);
//        customer1.setProjects(custoProjects);
//        daoCusto.update(customer1);
//


        Employee employee1 = daoEmplo.findById(5L);
       Employee employee2 = daoEmplo.findById(6L);
       Employee employee3 = daoEmplo.findById(7L);
//
//
//        List<Project> projects1 = new ArrayList<>();
//        projects1.add(daoProject.findById(1L));
//
//        employee1.setProjects(projects1);
//
//        List<Project> projects2 = new ArrayList<>();
//        projects2.add(daoProject.findById(1L));
//        employee2.setProjects(projects2);
//        List<Project> projects3 = new ArrayList<>();
//        projects3.add(daoProject.findById(1L));
//        employee3.setProjects(projects3);
//
//
//
//
//        daoEmplo.update(employee1);
//        daoEmplo.update(employee2);
//        daoEmplo.update(employee3);

//       List<Employee> employeeList = new ArrayList<>();
//
//        employeeList.add(employee1);
//       employeeList.add(employee2);
//       employeeList.add(employee3);
//       projectUno.setEmployees(employeeList);
//
//       daoProject.updateProject(projectUno);

        daoProject.deleteById(2L);
        daoProject.deleteById(3L);


        







    }


}

package com.example.springboot_hibernate_openbootcamp.dao;

import com.example.springboot_hibernate_openbootcamp.dto.EmployeeDTO;
import com.example.springboot_hibernate_openbootcamp.entities.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();
    Employee findById(Long id);
    Employee findByEmail(String email);
    boolean createEmployee(Employee employee);
    Employee upDate(Employee employee);
    boolean deleteEmployeeById(Long id);
    List<EmployeeDTO> findAllDTO();
    Employee findByFullName(String fullName);

    /*PAGINACION!!*/

    List<Employee> findAllLastPage();



}

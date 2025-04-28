package com.example.springboot_hibernate_openbootcamp.dao;

import com.example.springboot_hibernate_openbootcamp.dto.EmployeeDTO;
import com.example.springboot_hibernate_openbootcamp.entities.Customer;
import com.example.springboot_hibernate_openbootcamp.entities.Employee;

import java.util.List;

public interface CustomerDAO {
    List<Customer> findAll();
    Customer findById(Long id);
    //Customer findByEmail(String email);
    boolean createCustomer(Customer customer);
    Customer upDate(Customer customer);
    boolean deleteCustomerById(Long id);
    //List<CustomerDTO> findAllDTO();
}

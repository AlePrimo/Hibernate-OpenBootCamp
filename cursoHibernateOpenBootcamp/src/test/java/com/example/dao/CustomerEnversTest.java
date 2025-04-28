package com.example.dao;

import com.example.entities.Customer;
import com.example.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CustomerEnversTest {

    CustomerDAO customerDAO;

    @BeforeEach
    void setCustomerDAO(){
        customerDAO = new CustomerDAOimpl();
    }



    @Test

    void createCustomerEnversAudited(){

        Customer customerEnvers = new Customer(null,"Jose","EnversAudited","josecin@hotmail.com", LocalDate.of(2002,2,21),"Maven 124");

        customerDAO.create(customerEnvers);




    }


    @Test

    void updateCustomerEnversAudited(){

      Customer customer = customerDAO.findById(7L);
      customer.setAdress("Peribebuy 7425");
      customerDAO.update(customer);


    }












}

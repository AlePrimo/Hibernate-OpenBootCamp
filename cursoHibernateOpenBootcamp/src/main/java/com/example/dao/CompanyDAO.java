package com.example.dao;

import com.example.entities.Company;
import com.example.entities.Employee;

import java.util.List;

public interface CompanyDAO {

      Company findById(Long id);
      List<Employee> finfByIdEager(Long id);
      Company createCompany(Company company);
      Company updateCompany(Company company);
      boolean deleteCompany(Long id);




}

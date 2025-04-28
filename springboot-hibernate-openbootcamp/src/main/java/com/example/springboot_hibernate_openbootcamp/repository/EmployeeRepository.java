package com.example.springboot_hibernate_openbootcamp.repository;

import com.example.springboot_hibernate_openbootcamp.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository                                 //INTERFAZ QUE CONTIENE METODOS CRUD  (RECIBE COMO PARAMETROS LA CLASE DE LA ENTIDAD Y EL TIPO DE CLAVE PRIMARIA
public interface EmployeeRepository extends JpaRepository<Employee, Long> {









}

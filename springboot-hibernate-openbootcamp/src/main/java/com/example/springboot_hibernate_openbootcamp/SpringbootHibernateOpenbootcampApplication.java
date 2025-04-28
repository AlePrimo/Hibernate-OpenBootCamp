package com.example.springboot_hibernate_openbootcamp;

import com.example.springboot_hibernate_openbootcamp.dao.CustomerDAO;
import com.example.springboot_hibernate_openbootcamp.dao.CustomerDAOimpl;
import com.example.springboot_hibernate_openbootcamp.dao.EmployeeDAO;
import com.example.springboot_hibernate_openbootcamp.dao.EmployeeDAOimpl;
import com.example.springboot_hibernate_openbootcamp.entities.Customer;
import com.example.springboot_hibernate_openbootcamp.entities.Employee;
import com.example.springboot_hibernate_openbootcamp.repository.EmployeeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringbootHibernateOpenbootcampApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringbootHibernateOpenbootcampApplication.class, args);

		//EN VEZ DE GENERAR LAS CLASES DAO, SE CREA UNA INTERFAZ REPOSITORY Y AL ACCEDER AL METODO CONTEXT.GETBEAN
		//SPRING YA GENERA UNA CLASE REPOSITORY PARA IMPLEMENTAR LOS METODOS CRUD



		EmployeeRepository employeeRepository= context.getBean(EmployeeRepository.class);

		List<Employee> employees = new ArrayList<>();


//		for (int i = 1; i <= 5000 ; i++) {
//			Employee employeeToInsert = new Employee(null,"employee " + i, 40, "emplo"+i+"@mail.com", LocalDate.of(2019,8,22),true,30000D);
//			employees.add(employeeToInsert);
//		}

//		for (int i = 5001; i <= 10000 ; i++) {
//			Employee employeeToInsert = new Employee(null,"employee " + i, 30, "emplo"+i+"@mail.com", LocalDate.of(2020,4,25),false,12500D);
//			employees.add(employeeToInsert);
//		}
//
//		for (int i = 10001; i <= 15000 ; i++) {
//			Employee employeeToInsert = new Employee(null,"employee " + i, 25, "emplo"+i+"@mail.com", LocalDate.of(2023,7,18),true,2500D);
//			employees.add(employeeToInsert);
//		}
//
//
//			employeeRepository.saveAll(employees);
//



		//ENFOQUE DESDE SPRING JPA

		//System.out.println(employeeRepository.findAll());


		//ENFOQUE DESDE SPRING PERO CON HIBERNATE

		EmployeeDAO dao = context.getBean(EmployeeDAO.class);

		//System.out.println(dao.findByFullName("employee 4"));


       /*---------------------------METODO DE PAGINACION-------------------------------------------------------*/

//		System.out.println(dao.findAllLastPage());
//		List<Employee> employeeList = dao.findAllLastPage();
//
//for ( Employee employee : employeeList){
//	System.out.println();
//	System.out.println("********************************************** EMPLEADO : "+ employee.getId() +" ***********************************************************************");
//	System.out.println(employee);
//	System.out.println("*************************************************************************************************************************************************");
//	System.out.println();
//}



		//METODOS CRUD

		//CREATE
		//boolean creado = dao.createEmployee(new Employee(null,"Juan Carlos Parna",54,"parna@mail.com"));
		//System.out.println(creado);

		//RETRIEVE
//		System.out.println(dao.findById(1L));
//		System.out.println(dao.findByEmail("pedro@hotmail.com"));

		//UPDATE
//		Employee employee2 = dao.findById(3L);
//		System.out.println(employee2.getEmail());
//		employee2.setEmail("parnaroli@gmail.com");
//         dao.upDate(employee2);
//		System.out.println(employee2.getEmail());

		//DELETE

//		Employee employee2 = dao.findById(3L);
//		boolean borrado = dao.deleteEmployeeById(3L);
//		System.out.println(borrado);


//		dao.createEmployee(new Employee(null,"Alberto Troncoso",43,"tronco@gmail.com"));
//		dao.createEmployee(new Employee(null,"Jorge Pendorchio",60,"georgependorcho@gmail.com"));
//		dao.createEmployee(new Employee(null,"Juan Carlos Badia",89,"badiaycia@gmail.com"));
//		dao.createEmployee(new Employee(null,"Pablo Marmol",25, "marmol@gmail.com"));
//		dao.createEmployee(new Employee(null,"Olivia Pufarezzi",22,"pufa@gmail.com"));
//		dao.createEmployee(new Employee(null,"Roberto Giordano",48,"giordirip@gmail.com"));


//		System.out.println();
//		System.out.println("****************************************************************************");
//		System.out.println("****************************************************************************");
//		System.out.println(dao.findAll());
//		System.out.println();
//		System.out.println("****************************************************************************");
//		System.out.println("****************************************************************************");
//
//		System.out.println();
//		System.out.println(dao.findAllDTO());
//
//		System.out.println();
//		System.out.println("****************************************************************************");
//		System.out.println("****************************************************************************");

//		CustomerDAO customerDAO = context.getBean(CustomerDAO.class);
//
//
//
//		Customer customer1 = new Customer(null,"Orlando Gatti", LocalDate.of(1985,11,12),"ES");
//		Customer customer2 = new Customer(null,"Octavio Petela", LocalDate.of(1978,11,12),"AR");
//		Customer customer3 = new Customer(null,"Juan Carlos Calabro", LocalDate.of(1952,11,12),"ES");
//		Customer customer4 = new Customer(null,"Pedro Parna", LocalDate.of(2002,11,12),"US");
//		Customer customer5 = new Customer(null,"Victor Bo ", LocalDate.of(1953,11,12),"AR");

//		customerDAO.createCustomer(customer1);
//		customerDAO.createCustomer(customer2);
//		customerDAO.createCustomer(customer3);
//		customerDAO.createCustomer(customer4);
//		customerDAO.createCustomer(customer5);




	}

}

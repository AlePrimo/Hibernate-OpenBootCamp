package com.example.Proyecto_Final_Hibernate;

import com.example.Proyecto_Final_Hibernate.entities.*;
import com.example.Proyecto_Final_Hibernate.repository.BillingInfoRepository;
import com.example.Proyecto_Final_Hibernate.repository.TagRepository;
import com.example.Proyecto_Final_Hibernate.repository.TaskRepository;
import com.example.Proyecto_Final_Hibernate.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class ProyectoFinalHibernateApplication {

	public static void main(String[] args) {




		ApplicationContext context = SpringApplication.run(ProyectoFinalHibernateApplication.class, args);


        //CREAR LOS OBJETOS REPOSITORIOS PARA CADA ENTIDAD :


		BillingInfoRepository billingInfoRepository = context.getBean(BillingInfoRepository.class);

		UserRepository userRepository = context.getBean(UserRepository.class);

		TaskRepository taskRepository = context.getBean(TaskRepository.class);

		TagRepository tagRepository = context.getBean(TagRepository.class);


      //CREAR DOS OBJETOS DE LA CLASE BILLINGINFO Y PERSISTIRLOS :


		BillingInfo factura1 = new BillingInfo(null,"Calle Parna 1025","7150P","Rosario","Santa Fe","Argentina","1025-4521-78934",null);
		BillingInfo factura2 = new BillingInfo(null,"Calle Salta 1025","7101P","Lima","Buenos Aires","Argentina","784-421-78852",null);

//		billingInfoRepository.save(factura1);
//		billingInfoRepository.save(factura2);


		//CREAR DOS OBJETOS DE LA CLASE USUARIO Y AGREGARLES LA BILLINGINFO:


		User usuario1 = new User(null,"Alberto","Pandorra","45.213.154",true, LocalDate.of(1998,03,25));

		usuario1.setBillingInfo(factura1);
		userRepository.save(usuario1);


		User usuario2 = new User(null,"Jairo","Cocochio","42.213.154",true, LocalDate.of(1952,04,25));


		usuario2.setBillingInfo(factura2);
		userRepository.save(usuario2);


		//CREAR TASKS Y  TAGS Y AGREAGARSELAS A LOS OBJETOS USER :

		Tag tag1 = new Tag(null,"Tarea basica", TagColor.GREEN);
		Tag tag2 = new Tag(null,"Tarea compleja", TagColor.RED);
		Tag tag3 = new Tag(null,"Tarea intermedia", TagColor.GREEN);
		Tag tag4 = new Tag(null,"Tarea no remunerada", TagColor.YELLOW);
		Tag tag5 = new Tag(null,"Tarea remunerada", TagColor.BLUE);
		Tag tag6 = new Tag(null,"Tarea individual", TagColor.BLUE);
		Tag tag7 = new Tag(null,"Tarea grupal", TagColor.BLUE);

		tagRepository.saveAll(List.of(tag1,tag2,tag3,tag4,tag5,tag6,tag7));


		Task task = new Task(null,"Tarea nro 1","La tarea consiste en lavar todos los pisos con ECHO en el balde",true,LocalDate.of(2024,12,24),usuario1);
		task.setTagList(List.of(tag1,tag5,tag6));


		Task task2 = new Task(null,"Tarea nro 2","La tarea consiste en lijar una puerta",false,LocalDate.of(2024,11,24),usuario2);
		task2.setTagList(List.of(tag3,tag5,tag7));



		Task task3= new Task(null,"Tarea nro 3","La tarea consiste en pasear al perro",true,LocalDate.of(2024,10,24),usuario1);
		task3.setTagList(List.of(tag2,tag4,tag6));



		Task task4= new Task(null,"Tarea nro 4","La tarea consiste en pintar la puerta",false,LocalDate.of(2024,10,25),usuario2);
		task4.setTagList(List.of(tag1,tag5,tag7));



		taskRepository.saveAll(List.of(task,task2,task3,task4));












	}

}

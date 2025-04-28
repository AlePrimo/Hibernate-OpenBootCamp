package com.example.interceptors;

import com.example.entities.Customer;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;

public class Interceptor extends EmptyInterceptor {



//    En Hibernate, un Interceptor es una interfaz que permite interceptar y modificar las operaciones realizadas
//    por Hibernate en diferentes puntos del ciclo de vida de las entidades.
//    Es una herramienta poderosa para personalizar el comportamiento
//    de Hibernate sin modificar directamente el código de las entidades o las consultas.


    //TODO EL CODIGO QUE PONGAMOS DENTRO DE ESTE METODO SE APLICARA PARA TODAS LAS ENTIDADES
    //SALVO QUE LE INDIQUEMOS A QUE ENTIDAD DEBE APUNTAR
    //PARA IMPLEMENTAR ESTOS METODOS DE LA CLASE INTERCEPTOR DEBEMOS AGREGARLOS A LA CREACION DE LA SESSION EN
    // LA CLASE HIBERNATE UTIL
    //sessionFactory = metadata.getSessionFactoryBuilder().applyInterceptor(new Interceptor()).build();

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {

        if (entity instanceof Customer){ //AQUI LE INDICAMOS QUE PARA QUE SE CUMPLA EL CODIGO DEBE SER UNA ENTIDAD DE TIPO CUSTOMER


            System.out.println("Se esta creando un cliente en base de datos");
            //CON ESTE METODO, CADA VEZ QUE SE REALICE UNA OPERACION SAVE PARA UNA ENTIDAD CUSTOMER SE EJECUTARA ESTE CODIGO
            //POR LO TANTO EN ESTE CASO SE IMPRIMIRA POR PANTALLA LA LEYENDA QUE LE PASAMOS POR SOUT

        }

        return super.onSave(entity, id, state, propertyNames, types);
    }
}

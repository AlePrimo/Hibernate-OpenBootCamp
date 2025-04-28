### ESTOS SON  ALGUNOS DE LOS COMANDOS PARA ADMINISTRAR BASES DE DATOS DESDE CONSOLA CMD :

1. Login :

  * psql -U postgres(postgres equivale al nombre de usuario)
  * Luego nos pedira la contraseña


2. Ayuda :

  * \ ? (alt + 92) + signo de pregunta(sin espacio en el medio)

3. Salir :


   * \q


4. Ver Bases de Datos :

   
  * \l (barra invertida + letra L minuscula)


5. Conectarse a una base de datos ya estando logueado :

  * \c database_name


6. Ver Tablas :

  
   * Una vez conectado a la base de datos de la cual queremos ver las tablas ejecutar :
   * \dt

7. Login directo a una tabla :

  * psql -U user_name(postgres) -d table_name


8. Ver roles o usuarios de una base de datos :


   * Estando ya conectados a esa base de datos ejecutamos el comando :
   * \du


9. Ver columnas de una tabla : 


  * \d table_name

10. Crear una base de datos :


   * psql -c "CREATE DATABASE \"database_name\";"  





## BACKUPS :


       ## RESTAURACION :

 * Comandos para restaurar un archivo .sql de backup en una base de datos : 


 *  psql -U postgres -d database_name < backup_name.sql
          (En este caso el triangulo apunta a la base de datos
            lo que indica que el archivo .sql se guardara en esa base de datos)

 
 
 * psql -h 127.0.0.1 -p 5432 -U postgres -d database_name < backup.sql
  
 * Son basicamente lo mismo pero el segundo comando especifica mejor la conexion

    

     ## CREACION  DE UN BACKUP DE UNA BASE DE DATOS:



 * pg_dump -U postgres -d database_name > backup_name.sql  
      (Notese que el triangulo apunta al archivo .sql , lo que indica que 
               la base de datos se guardara en el archivo)


   



    ## BACKUP DE TODAS LAS BASES DE DATOS :

 *  pg_dumpall -U postgres > total_backup.sql 

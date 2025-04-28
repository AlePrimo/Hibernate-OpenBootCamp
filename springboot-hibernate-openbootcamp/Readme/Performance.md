### RENDIMIENTO :

Mejoras a nivel de base de datos (postgres) :


1.PgTune : permite optimizar la base de datos (mediante el archivo postgresql.conf) segun el uso , sistema operativo, hardware, etc...
    https://pgtune.leopard.in.ua/


2.EXPLAIN ANALIZE: ejecutando este comando antes de una consulta nos dara un informe completo de la misma(duracion , etc).


3.Work_mem : parametro que permite optimizar las consultas con ORDER BY . Se puede consultar en la consola de la base de datos
mediante el comando "SHOW work_mem" , luego se puede cambiar el valor en la misma consola con el comando "SET work_mem = 'nuevo_valor'"
o lo ideal es configurarlo en el archivo .conf de postgres para que persista. 


4.SHOW ALL : a diferencia del show work_mem este comando nos permite visualizar toda la configuracion de postgres.


5.Indices : estructura de datos separada de las tablas que permite optimizar la recuperacion de datos. 
 
   *Indices normales :

     *una columna
     *varias columnas
     *unique
     *parcial(WHERE)
     *implicito( se crea automaticamente sobre la primary key)
   

   *Tipos de indices :

     *B-tree (balanced tree)- POR DEFECTO

     *Hash

     *Gist

     *SP-Gist

     *GIN

     *BRIN
   
El comando que se utiliza para crear un indice es "CREATE INDEX index_name ON table_name(col1, col2, etc)".
La creacion de indices optimiza pero hay que tener en cuenta que al agregar datos se debera actualizar los indices.
Para saber como se estan ejecutando estos indices se deber invocar el comanto "EXPLAIN ANALYZE"


6.Particionamiento de tablas : En base de datos muy grandes se pueden crear particiones de la misma para que al realizar una 
consulta que entre dentro de esa particion , la consulta se haga directamente sobre esa particion y no sobre la base de datos 
completa.

    *Por rango (por fechas, por precios , etc) 
     Ej: Primero creamos la tabla y seteamos por que campo se haran las particiones :
        CREATE TABLE table_name..etc etc....PARTITION BY (age, prize, etc);
         Despues creamos la particion : 
        CREATE TABLE partition_name PARTITION OF original_table_name FOR VALUES FROM ('first_value') to ('second_value')

    *Por lista

    *Por hash


7.Vacuum :

A medida que se realizan operaciones en una base de datos (escritura ,etc) se van generando huecos que hay que volver a utilizar
Postgres realiza un proceso automatico cuando en una base de datos se realizan muchos cambios que se llama AUTOVACUUM, pero lo ideal es
configurar limpiezas de manera personalizada (VACUUM ANALIZE, VACUUM FULL). Hay que tener en cuenta que las bases de datos
tienen una funcion de bloqueao o Lock cuando se realizan estas operaciones de limpieza que pueden llevar a que una aplicacion no funcione, por es
estos procesos se realizan en horarios donde se sepa que no haya actividad de los usuarios.
Ej: VACUUM ANALIZE table_name


### OTROS CONCEPTOS  A NIVEL DE BASE DE DATOS :

*CTE

*FUNCIONES VENTANA

*VISTAS Y VISTAS MATERIALIZADAS

 


### OPTIMIZACION A NIVEL DE HIBERNATE : 

1.UTILIZAR DTOs (DATA TRANSFER OBJECTS)para las consultas : son una especie de clases resumidas de las entidades que tenemos en la 
aplicacion. Los DTO generalmente tienen solo algunos atributos (los  mas importantes)de las clases entidades de las que derivan.





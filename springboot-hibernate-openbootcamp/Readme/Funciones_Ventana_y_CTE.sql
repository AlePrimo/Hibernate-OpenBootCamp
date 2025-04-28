/*CTE's (COMMON TABLE EXPRESIONS)

*Es un mecanismo que se usa para simplificar las consultas SELECT
*Es un resultado temporal tomado de una consulta
*Crea tablas temporales para consultar datos en lugar de usar subqueries o subselects en una clausula FROM
*A diferencia de las subqueries las CTE pueden ser referenciadas multiples veces desde multiples partes de una misma sentencia SQL
*Mejora la legibilidad de las sentencias
*El ciclo de vida de una CTE es el mismo de una sentencia SQL
*Se utilizan en conjunto con las funciones ventana.
* Hay dos tipos de CTE : recursiva y no recursiva
SINTAXIS :
WITH cte_name (column_list) AS (cte_query_definition)  statement;
*/

/*Ejemplo de CTE de la columna salary de la tabla employees : 
Creamos primero la CTE y luego ejecutamos la consulta SELECT pasandole como parametro la misma.
ACLARACION : tanto la creacion de la CTE como el select forman parte de la misma consulta , ya que el punto y coma
finaliza la misma.*/
WITH cte_salaries AS (
SELECT salary FROM employees
) 

SELECT * FROM cte_salaries;


/*Ejemplo de creacion de una tabla con el metodo generate_series y creacion de una CTE :*/

WITH cte_numbers AS (
SELECT * FROM generate_series(1,10) AS id
) 
SELECT * FROM cte_numbers WHERE id > 5; /*AL CREAR EL CTE SE GENERA UNA TABLA CON NUMEROS DEL 1 AL 10 para una columna id
Y LUEGO AL HACER LA CONSULTA SE MUESTRA UNA TABLA CON NUMEROS DEL 6 AL 10, ESTA TABLA NO PERSISTE EN LA BASE DE DATOS*/


/*Ejemplo con una CTE mas compleja :*/

WITH cte_dates AS (
SELECT * FROM generate_series(
'2024-01-01 00:00'::timestamp,
'2024-12-30 00:00',
'6 hours'
) AS creation_date
) /*En este caso creamos un cte y con el metodo generate agregamos fechas c/6hs a una columna creation_date*/

SELECT * FROM cte_dates

/*Ejemplo de CTE que realiza una comparacion*/

SELECT * FROM employees;

WITH cte_categories AS (

SELECT salary , active , id , (/*seleccionamos los campos que vamos a necesitar de la tabla*/
  CASE    /*creamos una sentencia que asigna una leyenda segun el salary*/
    WHEN salary < 5000 THEN 'Basic'
    WHEN salary > 5000 AND salary < 30000 THEN 'Medium'
    WHEN salary = 30000 THEN 'High'
  END
) AS category FROM employees /*la leyenda se cargara en una columna que se llamara category*/

)
SELECT * FROM cte_categories WHERE active = 'false' AND id % 2 = 0; /*Al realizar la consulta podemos usar el CTE y por lo tanto tendremos un select mas 
acotado y legible */

/*Ejemplo de CTE donde borramos datos de una tabla original hacia una tabla historico*/

CREATE TABLE employees_archive AS SELECT * FROM employees LIMIT 0; /*Esta sentencia crea o duplica una tabla ya existente
pero al agregarle el comanto LIMIT con el numero  0, se creara con los mismos campos pero sin datos */

WITH cte_employees_archive_inactive AS (
DELETE FROM employees  /*Lo que haremos con esta consulta ser borrar de la tabla original todos los datos que cumplan la condicion*/
WHERE active = 'false' /*si retiramos la condicion se borraran todos los registros de la tabla original y se cargaran en la nueva*/
RETURNING * /*Luego retornara esos datos*/
)
INSERT INTO employees_archive /*Los datos retornados en la CTE se insertaran en la nueva tabla*/
SELECT * FROM cte_employees_archive_inactive; /*Al ejecutar el CTE se realizara el borrado de una tabla y las inserciones en la otra*/
/*Ahora verificamos en ambas tablas*/
SELECT * FROM employees_archive;
SELECT * FROM employees;



/*FUNCIONES AGREGADAS Y FUNCIONES VENTANA*/

/*
Las funciones AGREGADAS (COUNT, AVG, SUM ....) realizan un calculo sobre varias filas y 
devuelven el resultado en una sola fila(un unico resultado).

Las funciones VENTANA permiten realizar calculos sobre un conjunto de filas relacionadas con la fila actual.
No agrupan los datos en un unico resultado, por eso permiten realizar calculos sin perder detalle o reducir el numero de resultados
como con las funciones agregadas, o sea se crean agregaciones sobre las propias filas

SINTAXIS :

OVER ()
PARTITION BY () ****NO CONFUNDIR CON LAS PARTICIONES DE TABLAS****
ORDER BY ()

ROW NUMBER ()
RANK ()
DENSE RANK ()
FIRST_VALUE ()
LAST_VALUE ()
LAG ()
LEAD ()


*/

/*EJEMPLOS:*/

SELECT * , array_agg(x) OVER () AS frame FROM generate_series(1,10) AS x;
/*En esta consulta tenemos una funcion array_agg que lo que hace es crear un array del parametro que le pasemos
en este caso tambien tenemos la funcion generate que le asigna a x valores del 1 al 10
a su vez tenemos la funcion over() que crea una columna de ese array que se llamara frame */

SELECT * , array_agg(x) OVER (ORDER BY x) AS frame FROM generate_series(1,10) AS x;
/*Lo mismo que antes pero ahora se le agrego a la funcion OVER el parametro ORDER BY, que en este caso
nos ira ordenando los datos de la columna frame a medida que se van ingresando*/


SELECT * , array_agg(x) OVER (
  ORDER BY x ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW /*valores del order by*/
) AS frame FROM generate_series(1,10) AS x;


SELECT * , array_agg(x) OVER (
  ORDER BY x ROWS BETWEEN CURRENT ROW AND UNBOUNDED FOLLOWING/*lo mismo que la anterior pero al reves*/
) AS frame FROM generate_series(1,10) AS x;



SELECT * , array_agg(x) OVER (
  ORDER BY x ROWS BETWEEN UNBOUNDED PRECEDING AND 1 FOLLOWING /*agrega 1 numero siguiente (puede ser 2, 3 etc)*/
) AS frame FROM generate_series(1,10) AS x;

--Ejemplo de uso de funcion agregada AVG() con un OVER() donde le agregamos un PARTITION:
/*En este ejemplo tenemos una tabla de productos que tiene una columna con categorias con un id y una columna
de precios unitarios , lo que haremos sera calcular el promedio de precios segun la categoria*/

SELECT product_id, product_name, category_id, category_name, unit_price, /*columnas a seleccionar*/
AVG(unit_price) /*calculo del promedio del precio unitario segun la funcion ventana a continuacion*/
OVER (PARTITION BY category_id) /*funcion ventana que nos crea una nueva columna con el promedio de precios segun categoria*/
ROW_NUMBER ()  (PARTITION BY category_id) /*row_number nos calcula la cantidad de filas segun la categoria*/
FROM products INNER JOIN categories USING category_id;/*Aqui indicamos de que tablas sacaremos los datos
en este caso es de la tabla products haciendo un cruce con la tabla categories usando el category_id*/
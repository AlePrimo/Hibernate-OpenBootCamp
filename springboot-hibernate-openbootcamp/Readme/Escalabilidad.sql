
/*ESCALABILIDAD*/


CREATE TABLE customers (

id BIGSERIAL,
birth_date DATE NOT NULL,
full_name varchar(20) NOT NULL,
PRIMARY KEY (id, birth_date)

) PARTITION BY RANGE (birth_date);

/* PARTICIONAMIENTO POR RANGO */

CREATE TABLE customers_70 PARTITION OF customers
FOR VALUES FROM ('1970-01-01') TO ('1980-01-01');


CREATE TABLE customers_80 PARTITION OF customers
FOR VALUES FROM ('1980-01-01') TO ('1990-01-01');


CREATE TABLE customers_90 PARTITION OF customers
FOR VALUES FROM ('1990-01-01') TO ('2000-01-01');

INSERT INTO customers (birth_date, full_name) VALUES ('1974-02-25','Juan Jose Camero');
INSERT INTO customers (birth_date, full_name) VALUES ('1985-07-25','Marty Mcfly');
INSERT INTO customers (birth_date, full_name) VALUES ('1999-01-25','Kurt Cobain');

INSERT INTO customers (birth_date, full_name) VALUES ('1989-01-4','Juan Jose Parna');
INSERT INTO customers (birth_date, full_name) VALUES ('1978-06-14','Mario Kassar');
INSERT INTO customers (birth_date, full_name) VALUES ('1992-04-2','Kyle Ordoñez');

 select * from customers WHERE birth_date > '1974-02-25';


 /*PARTICIONAMIENTO POR LISTA*/

drop table if exists customers;

CREATE TABLE customers (

id BIGSERIAL,
birth_date DATE NOT NULL,
full_name varchar(20) NOT NULL,
country_code varchar(2) NOT NULL,
PRIMARY KEY (id, country_code)

) PARTITION BY LIST(country_code);


CREATE TABLE customers_ar PARTITION OF customers
FOR VALUES IN ('AR');
CREATE TABLE customers_us PARTITION OF customers
FOR VALUES IN ('US');
CREATE TABLE customers_es PARTITION OF customers
FOR VALUES IN ('ES');


SELECT * FROM customers WHERE country_code = 'US';


/*PARTICIONAMIENTO POR HASH*/

drop table if exists department;

CREATE TABLE department (
id int PRIMARY KEY
) PARTITION BY HASH(id);

/*Una particion por hash es cuando dividimos la tabla en partes iguales
la clave primaria es el parametro que le pasamos para hacer la particion y 
segun el indice del modulus , es en cuantas partes se 
va a dividir(en este caso la tabla department va a particionarse 
en 3 tablas) , el remainder siempre sera desde 0 hasta el modulus menos 1*/

CREATE TABLE department_1 PARTITION OF department 
FOR VALUES WITH (MODULUS 3, remainder 0);
CREATE TABLE department_2 PARTITION OF department 
FOR VALUES WITH (MODULUS 3, remainder 1);
CREATE TABLE department_3 PARTITION OF department 
FOR VALUES WITH (MODULUS 3, remainder 2);


INSERT INTO department (SELECT generate_series (0,60));/*Generamos valores de 0 a 60*/





/*PARTICION POR HERENCIA*/


--CREANDO LAS TABLAS

CREATE TABLE measurement (
city_id int not null,
log_date date not null,
peak_temperature int,
unit_sales int
);

CREATE TABLE measurement_2006 (
CHECK (log_date >= DATE '2006-01-01' AND log_date < DATE '2007-01-01')
)INHERITS (measurement);

CREATE TABLE measurement_2007 (
CHECK (log_date >= DATE '2007-01-01' AND log_date < DATE '2008-01-01')
)INHERITS (measurement);

--CREAR FUNCION PARA TRIGGER

--Almacenar todos los nuevos registros en la tabla measurement_2006

CREATE OR REPLACE FUNCTION measurement_insert_trigger ()
RETURNS TRIGGER AS $$
BEGIN
     INSERT INTO measurement_2006  VALUES (NEW.*);
	 RETURN null;
END;
$$
LANGUAGE plpgsql;

--CREAR TRIGGER

CREATE TRIGGER insert_measurement_trigger 
  BEFORE INSERT ON measurement
  FOR EACH ROW EXECUTE PROCEDURE measurement_insert_trigger ();

--INSERTAR DATOS EN LA TABLA

INSERT INTO measurement VALUES 
(1,'2006-04-07',3,7),
(2,'2006-08-03',8,7),
(3,'2006-04-07',5,15),
(4,'2006-04-05',4,12);

SELECT * FROM measurement;
SELECT * FROM measurement_2006;

/*ESTA FUNCION CREADA PARA INSERTAR DATOS NO SIRVE YA QUE NO PERMITIRA CARGAR DATOS QUE NO SEAN DEL AÑO 2006*/

--FUNCION QUE PERMITE CARGAR DATOS EN AMBAS PARTICIONES
CREATE OR REPLACE FUNCTION measurement_insert_trigger()
RETURNS TRIGGER AS $$
BEGIN
    -- Verificar el rango de log_date y redirigir el registro a la tabla correspondiente
    IF (NEW.log_date >= DATE '2006-01-01' AND NEW.log_date < DATE '2007-01-01') THEN
        INSERT INTO measurement_2006 VALUES (NEW.*);
    ELSIF (NEW.log_date >= DATE '2007-01-01' AND NEW.log_date < DATE '2008-01-01') THEN
        INSERT INTO measurement_2007 VALUES (NEW.*);
    ELSE
        RAISE EXCEPTION 'La fecha % no está dentro de los rangos permitidos', NEW.log_date;
    END IF;
    RETURN NULL;
END;
$$
LANGUAGE plpgsql;

--PRIMERO  BORRAMOS EL TRIGGER VIEJO
DROP TRIGGER insert_measurement_trigger ON measurement;

--ACTUALIZAMOS EL TRIGGER
CREATE TRIGGER insert_measurement_trigger
BEFORE INSERT ON measurement
FOR EACH ROW EXECUTE PROCEDURE measurement_insert_trigger();

--INSERTAMOS DATOS DE AMBOS AÑOS

INSERT INTO measurement VALUES 
(5,'2006-04-07',3,7),
(6,'2007-08-03',8,7),
(7,'2006-04-07',5,15),
(8,'2007-04-05',4,12);

SELECT * FROM measurement;
SELECT * FROM measurement_2006;
SELECT * FROM measurement_2007;


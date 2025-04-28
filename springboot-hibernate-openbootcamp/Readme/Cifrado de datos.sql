/*--------------------CIFRADO DE DATOS---------------------*/


/*Con la extension pgcrypto se puede hacer un cifrado de los datos de una tabla*/

CREATE EXTENSION pgcrypto; /*Habilitamos la extension*/

SELECT * FROM users;


CREATE TABLE users (id serial PRIMARY KEY, 
email varchar(30) not null unique,
pass varchar not null);/*Creamos una tabla*/




INSERT INTO users (email , pass) VALUES
('user3@gmail.com', pgp_sym_encrypt('admin1', 'secret')),
('user4@mail.com',  pgp_sym_encrypt('123456', 'secret'));/*Ingresamos datos usando el metodo de cifrado
o codificado para que el campo pass no sea accesible sin la clave que es el segundo parametro 'secret'*/
/*NOTA : NO ACOTAR LA CANTIDAD DE CARACTERES PARA EL CAMPO A CODIFICAR*/

/*Recuperar datos con columnas cifradas*/

SELECT id, email ,pgp_sym_decrypt(pass::bytea, 'secret') AS pass FROM users;
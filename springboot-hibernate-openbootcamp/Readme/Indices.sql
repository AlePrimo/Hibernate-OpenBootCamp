/*INDICES*/

/*Indices por columna full_name*/
CREATE INDEX idx_employees_full_name ON employees(full_name);

EXPLAIN ANALYZE SELECT * FROM employees WHERE full_name =  'empleado 4';


/*Indices por columna register_date*/

EXPLAIN ANALYZE SELECT * FROM employees WHERE EXTRACT(YEAR FROM register_date) = 2019;
CREATE INDEX idx_employees_register_date_2019 ON employees(register_date)
WHERE EXTRACT(YEAR FROM register_date) = 2019;

/*Se pueden crear indices para cualquiera de las columnas o campos pero segun la cantidad de registros que tenga 
la tabla postgres elegira si usar el indice o no*/
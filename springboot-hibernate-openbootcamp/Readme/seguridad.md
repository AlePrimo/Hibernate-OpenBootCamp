## Seguridad en PostgreSql :

La seguridad esta gestionada por roles.

Usuarios , grupos y roles son lo mismo , depende de como se utilizan que se mencionan de una u otra manera.

Niveles de seguridad :

1. Instancia : crear usuarios, roles, bases de datos, realizar login, replicacion.
2. Bases de datos : conexion, crear esquemas, etc.
3. Esquemas : usar el esquema, crear objetos dentro.
4. Tabla : operaciones Data Manipulation Language (DML)
5. Columnas : permitir o restringir el acceso a una o varias columnas.
6. Filas : restringir o permitir el acceso a una fila/s en particular.

### 1.Instancia :

Estos son los atributos de seguridad de instancia que podemos usar en postgres para la instancia.
Estos se asignaran a partir de una consulta sql.
Ej: CREATE USER "usuario" NOSUPERUSER NOCREATEDB LOGIN - Crea un usuario que no es superuser ni puede crear una
base de datos pero puede loguearse

*SUPERUSER
*CREATEDB
*CREATEROLE
*LOGIN
*REPLICATION

### 2.Base de datos :

Antes de agregar seguridad a la base de datos primero debemos revocar todos los permisos preexistentes
REVOKE ALL ON DATABASE db_name FROM public.
Luego podemos empezar a dar permisos de seguridad , por ejemplo el permiso de conexion a un usuario en particular :
GRANT CONNECT ON DATABASE db_name to user_name
O tambien autorizar a un usuario a crear en una base de datos :
GRANT CREATE ON DATABASE db_name to user_name
Para revocar esos permisos dados a un usuario , se usan los siguientes comandos :

REVOKE ALL ON DATABASE db_name to user_name

*CREATE
*CONNECT
*TEMP/TEMPORARY

### 3.Esquemas :

Los dos permisos mas importantes que se pueden otorgar a nivel de esquemas es el de creacion(create)
y uso (usage).

*CREATE
*USAGE
Ej: GRANT CREATE SCHEMA public to user_name / GRANT USAGE SCHEMA public to user_name

Revocar permisos a nivel de esquemas para un usuario en particular :
REVOKE ALL ON SCHEMA public to user_name

### 4.Tablas :

A nivel de tablas son varios los permisos que se pueden otorgar :

*SELECT
*INSERT
*UPDATE
*DELETE
*TRUNCATE
*TRIGGER
*REFERENCE

Se pueden otorgar permisos a un usuario para todas las tablas :

GRANT nombre_permiso ON ALL TABLES IN SCHEMA schema_name TO user_name

O tambien se pueden dar permisos pero para una tabla en particular :

GRANT nombre_permiso ON TABLE table_name TO user_name

### 5.Columnas :

Parecido a la seguridad en tablas pero difiere en la sintaxis de las consultas que otorgan los permisos
ademas otorga permiso sobre las columnas que le indiquemos por parametro pero no sobre toda la base de datos.

EJ :  GRANT permission_name (col1, col2, col3 ....) ON TABLE table_name TO user_name
GRANT SELECT (id, name) ON employees TO db_user1
*SELECT
*INSERT
*UPDATE
*REFERENCE

### 6.Filas :

Para poder implementar la seguridad en filas se debe habilitar primero , ya que no esta habilitada por defecto,
una vez habilitada su valor inicial por defecto es DENY ALL.

HABILITACION :

ALTER TABLE table_name ENABLE ROW LEVEL SECURITY 

ESTABLECER LA SEGURIDAD A TRAVEZ DE UNA POLICY :

CREATE POLICY policy_name ON table_name FOR permission_name TO user_name USING (condition)
EJ: CREATE POLICY only_adults ON employees FOR SELECT TO db_user 1 USING (age > 18)
Esta policy que llamamos only_adults  especifica que el usuario 1 solo podra acceder a los valores de la tabla
employees donde el valor de age sea superior a 18.

BORRADO DE UNA POLICY :

DROP POLICY policy_name





### Comandos para verificar los privilegios de seguridad de un usuario :

El mismo usuario puede verificar sus privilegios ejecutando un comando desde su sesion :

SELECT 
has_database_privilege ('table_name','permission_name'), 
has_schema_privilege('schema_name','permission_name'),
has_table_privilege ('table_name', 'permission_name'),
has_any_column_privilege('table_name','permission_name'); 
SEPARANDO CON COMA SE PUEDEN LISTAR LOS PRIVILEGIOS 
 A CONSULTAR. 

Tambien podemos consultar si un usario en particular tiene un privilegio de seguridad en una tabla dada:

SELECT has_column_privilege('user_name', 'table_name','column_name', 'permission_name');


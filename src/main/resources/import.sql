/* INSERT de tabla de regiones*/

INSERT INTO regiones (nombre_region) VALUES ('Región del Maule');
INSERT INTO regiones (nombre_region) VALUES ('Región del Ñuble');
INSERT INTO regiones (nombre_region) VALUES ('Región del Biobío');
INSERT INTO regiones (nombre_region) VALUES ('Región de La Araucanía');
INSERT INTO regiones (nombre_region) VALUES ('Región de Tarapacá');
INSERT INTO regiones (nombre_region) VALUES ('Región de Antofagasta');
INSERT INTO regiones (nombre_region) VALUES ('Región de Atacama');
INSERT INTO regiones (nombre_region) VALUES ('Región de Coquimbo');
INSERT INTO regiones (nombre_region) VALUES ('Región de Valparaíso');
INSERT INTO regiones (nombre_region) VALUES ('Región Metropolitana');
INSERT INTO regiones (nombre_region) VALUES ('Región de O’Higgins');
INSERT INTO regiones (nombre_region) VALUES ('Región de Arica y Parinacota');
INSERT INTO regiones (nombre_region) VALUES ('Región de Los Ríos');
INSERT INTO regiones (nombre_region) VALUES ('Región de Los Lagos');
INSERT INTO regiones (nombre_region) VALUES ('Región de Aysén');
INSERT INTO regiones (nombre_region) VALUES ('Región de Magallanes');

/* INSERT de tabla de ciudades*/
INSERT INTO ciudades (nombre_ciudad,region_id) VALUES ('Laja',3);
INSERT INTO ciudades (nombre_ciudad,region_id) VALUES ('Santiago',10);
INSERT INTO ciudades (nombre_ciudad,region_id) VALUES ('Arica',12);
INSERT INTO ciudades (nombre_ciudad,region_id) VALUES ('Temuco',4);
INSERT INTO ciudades (nombre_ciudad,region_id) VALUES ('Los Angeles',3);
INSERT INTO ciudades (nombre_ciudad,region_id) VALUES ('Antofagasta',6);
INSERT INTO ciudades (nombre_ciudad,region_id) VALUES ('Concepción',3);

/* INSERT de tabla de clientes*/

INSERT INTO clientes (nombre,apellido,email,telefono,fecha_nacimiento,ciudad_id) VALUES ('Patricio','Contreras','patorma@yahoo.com',432469265,'1981-05-06',1);
INSERT INTO clientes (nombre,apellido,email,telefono,fecha_nacimiento,ciudad_id) VALUES ('Daniel','Perez','dragon2020@gmail.com',93521787,'1985-12-14',2);
INSERT INTO clientes (nombre,apellido,email,telefono,fecha_nacimiento,ciudad_id) VALUES ('Leslie','Suarez','leslie2024@gmail.com',432364589,'2000-06-06',2);
INSERT INTO clientes (nombre,apellido,email,telefono,fecha_nacimiento,ciudad_id) VALUES ('Tamara','Torres','tamara@gmail.com',432255689,'2002-02-09',5);
INSERT INTO clientes (nombre,apellido,email,telefono,fecha_nacimiento,ciudad_id) VALUES ('Luis ','Vergara','hunterxhunter@gmail.com',412788921,'2003-05-06',7);

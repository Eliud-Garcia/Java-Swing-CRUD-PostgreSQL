
create table DEUDOR(
	id SERIAL not null primary key,
	nombres varchar(50),
	apellidos varchar(50),
	cantidad_deuda int,
	descripcion varchar(50)
);

-- mostrar
select * from DEUDOR;

-- insertar

insert into DEUDOR (nombres, apellidos, cantidad_deuda, descripcion) values ('Camila', 'Gonzales', 100000, 'audifonos');

-- eliminar
delete from DEUDOR where id = 25;

-- editar
update DEUDOR set nombres = 'Eliud', apellidos = 'Garcia' where id = 1; 

-- buscar
select * from DEUDOR where id = 25;



create table DEUDOR(
	id SERIAL not null primary key,
	nombres varchar(50),
	apellidos varchar(50),
	cantidad_deuda int,
	descripcion varchar(50)
);

/*para saber el usuario actual*/
select current_user;

/*INSERT*/
insert into Deudores (nombres, apellidos, cantidad_deuda) values ('Eliud', 'Garcia', 20000);

/*mostrar*/
select * from DEUDOR;

-- para validar si ya existe un registro de deudor
select exists (select 1 from Deudores where nombres='juan' and apellidos='gonzales' and cantidad_deuda=1)
CREATE table ubicacion (
id serial primary key, 
hospital varchar(80),
nombre varchar (80)
);

create table habitacion (
id serial primary key, 
numero integer
);


create table cama (
id serial primary key,
estado varchar (60)
);


create table internacion (
	id serial primary key,
	paciente varchar(80) not null,
	diagnostico varchar(100) not null
);
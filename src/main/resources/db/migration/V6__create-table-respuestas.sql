create table respuestas(
id bigInt not null auto_increment,
mensaje varchar(250) not null,
id_topico int  not null,
fecha_respuesta datetime not null,
autor varchar(30) not null,
solucion varchar(20) not null,
primary key(id)
);
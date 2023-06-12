create table topicos(
id bigInt not null auto_increment,
titulo varchar(50) not null unique,
mensaje varchar(150) not null unique,
fecha_creacion datetime not null,
Statuss varchar(30) not null,
nombreU varchar(50) not null,
email varchar(50) not null,
nombreC varchar(50) not null,
categoria varchar(50) not null,
primary key(id)
);
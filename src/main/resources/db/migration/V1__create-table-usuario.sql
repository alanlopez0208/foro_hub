CREATE TABLE usuario(
    id bigint not null unique auto_increment,
    nombre varchar(200) not null,
    correo_electronico varchar(300) not null unique,
    password varchar(300) not null
);
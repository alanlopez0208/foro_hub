CREATE TABLE topico(
    id bigint not null unique auto_increment,
    titulo varchar(200) not null,
    mensaje varchar(200) not null,
    fecha_creacion datetime not null,
    status varchar(200) not null,
    id_usuario bigint not null,

    primary key(id)
);
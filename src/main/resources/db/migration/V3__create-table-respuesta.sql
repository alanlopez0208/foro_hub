CREATE TABLE respuesta (
    id bigint not null unique auto_increment,
    mensaje varchar(200) not null,
    id_topico bigint not null ,
    fecha_creacion datetime not null,
    id_usuario bigint not null,
    solucion varchar(300),
    primary key(id),
    constraint fk_respuesta_topico_id foreign key(id_usuario) references usuario(id),
    constraint fk_respuesta_usuario_id foreign key(id_topico) references topico(id)
);
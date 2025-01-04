package com.example.foro_alura.infra.exception;

public class NotFoundException extends RuntimeException{
    private String mensaje;

    public NotFoundException(String mensaje){
        super(mensaje);
        this.mensaje = mensaje;
    }
}

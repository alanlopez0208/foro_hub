package com.example.foro_alura.infra.exception;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class RestResponseExceptionHandler {

    @ExceptionHandler(exception = EntityNotFoundException.class)
    public ResponseEntity<ErrorDetalles> notFound(
            RuntimeException ex, WebRequest request
    ){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(exception = HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDetalles> badRequest(
            RuntimeException ex, WebRequest request
    ){
        return ResponseEntity.badRequest().body(new ErrorDetalles(ex.getMessage()));
    }



    @ExceptionHandler(exception = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgument(MethodArgumentNotValidException exception){
        var errorStream = exception.getAllErrors()
                .stream().map(e-> new ErrorDetalles(e.getDefaultMessage())).toList();
        return ResponseEntity.badRequest().body(errorStream);
    }


    @ExceptionHandler(exception = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorDetalles> manejarSqlExceptions(SQLIntegrityConstraintViolationException ex){
        var error = ex.getMessage();


        return ResponseEntity.badRequest().body(new ErrorDetalles(error));
    }

    public record ErrorDetalles(String mensaje){
    }

}

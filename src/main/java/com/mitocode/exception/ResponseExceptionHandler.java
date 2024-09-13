package com.mitocode.exception;

import org.hibernate.dialect.function.array.H2ArrayContainsFunction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/*8.3.1 Manejo de excepciones::Con esta anotacion estoy definiendo q esta clase va a hacer la q va a gestionar y retornar al cliente, en caso ocurra, las excepciones
ocurridas en el proyecto y asi, se centraliza el manejo de excepciones.*/
@RestControllerAdvice
public class ResponseExceptionHandler
{
    /*8.3.5 Manejo de excepciones::Para manejar todas las demas excepciones q no sean del tipo ModelNotFoundException
    ni MethodArgumentNotValidException.*/
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleException(Exception ex, WebRequest webRequest)
    {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*8.3.2 Manejo de excepciones::Con esta anotacion estoy definiendo que este metodo va a manejar la excepcion personalizada de tipo ModelNotFoundException.*/
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleModelNotFoundException(ModelNotFoundException ex, WebRequest webRequest)
    {
        /*8.3.4 Manejo de excepciones:: El webRequest.getDescription(false) me retorna una url abreviada de la que ocasionó el error,*/
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), webRequest.getDescription(false));
        /*8.3.3 Manejo de excepciones::retorna al cliente una respuesta HTTP estandarizada (404 -  Not Found) y dentro de ella la respuesta de error CustomErrorResponse en formato json con todo sus atributos.*/
        return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest webRequest)
    {
        /*10.3.1 Validacion de inputs:: Se esta personalizando el mensaje de error*/
        String errorMsg = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField().concat(": ").concat(e.getDefaultMessage()))
                .collect(Collectors.joining(","));

        /*10.3.2 Validacion de inputs:: Este bloque de codigo funcional de aqui arriba es lo mismo que hacer este bloque de codigo imperativo de aqui de abajo:
        for (FieldError e : ex.getBindingResult().getFieldErrors())
            errorMsg += e.getField().concat(":").concat(e.getDefaultMessage());*/

        CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now(), errorMsg, webRequest.getDescription(false));
        return new ResponseEntity<>(customErrorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}

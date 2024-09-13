package com.mitocode.exception;

/*8.2.1 Manejo de excepciones:: Para manejar una clase de excepcion personalizada, esta debe de heredar de RuntimeException y en su constructor debe de enviar el mensaje
,que recibió como parametro, a su constructor padre: RuntimeException.*/
public class ModelNotFoundException extends RuntimeException
{
    public ModelNotFoundException(String message)
    {
        super(message);
    }
}

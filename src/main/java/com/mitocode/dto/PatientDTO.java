package com.mitocode.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PatientDTO {
    private Integer idPatient;
    /*10.1 Validacion de inputs:: Para poder realizar las validaciones de inputs de manera semiautomatica (la que me permite usar las anotaciones @NotNull, @Size, @Pattern,...)
    es necesario agregar la dependencia (libreria) spring boot validation, cuya etiqueta xml se puede encontrar en mvnrepository.com para agregarla al pom.xml .
    En caso no se cumpla al menos 1 validacion, entonces se genera una excepcion.
    * La validacion size es aplicable solo a String y su parametro message es para q su mensaje salga dentro del
    * mensaje de la excepcion que se generase en caso no se cumpla la validacion. EL tipo de excepcion que se genera en este caso es MethodArgumentNotValidException.
    * La clase q gestiona las excepciones de manera centralizada en este proyecto es ResponseExceptionHandler.*/
    @NotNull
    /*11.1 Manejo de idiomas en backend:: El valor del parametro message, el cual está entre {} significa que es una variable, cuyo valor está definido en otro archivo,
     * ruta de archivo que está definido,para este caso, en la clase MessageConfig.*/
    @Size(min = 3, max = 70, message = "{firstname.size}")
    private String firstName;

    @NotNull
    @Size(min = 3, max = 70, message = "{lastname.size}")
    private String lastName;

    @NotNull
    private String dni;

    @NotNull
    private String address;

    @NotNull
    @Pattern(regexp = "[0-9]+")
    private String phone;

    @Email
    private String email;
}

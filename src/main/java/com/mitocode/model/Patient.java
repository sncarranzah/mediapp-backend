package com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
/*2.2.4 Introduccion a spring data jpa :: @Table(name=Paciente) <-- agregar esto si se quiere personalizar el nombre de la tabla ya sea al momento de generar la migracion de bd
o ya sea al momento de iniciar el programa e iniciar la sincronizacion entre esta entidad y su tabla de bd respectiva.*/
//Para ver detalle de la anotacion @EqualsAndHashCode ver CursosLibres\Java\Spring boot\Lombok.docx .
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Patient {
    @Id
    /*2.2.1 Introduccion a spring data jpa :: La anotacion @GeneratedValue es para indicar que esta columna es autoincrementada por el motor de la bd y esta anotacion es necesaria
   siempre ya sea cuando se realize la migracion de bd (generar el modelo de tablas de la bd a partir de este modelo de entidades que está en la capa de model)
   o ya sea para el funcionamiento rutinario de la aplicacion. Los mismo pasa para las demas anotaciones de esta capa de modelo
   tales como @ManyToOne,@JoinColumn, ... */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idPatient;
    /*2.2.2 Introduccion a spring data jpa :: La opcion nullable es para indicar si el atributo de la tabla aceptará null o no para cuando se
     genere la migracion. Es por defecto true (acepta null).*/
    @Column(nullable = false, length = 70)
    private String firstName;
    /*2.2.3 Introduccion a spring data jpa :: En java se utiliza la notacion lowerCamelCase para nombrar a los atributos, clases y metodos, en cambio
     * en base de datos se utiliza la notacion snake case. Cuando Spring genera la migracion, cambia el nombre de las tablas, su campos, etc...
      migradas segun la notacion snake case. Es decir, por ejm, lastName pasaria a la bd como last_name. Si se quiere personalizar el nombre de
      * los campos del modelo de bd antes de migrar, utilizar el parametro name dentro de column y darle el valor que debe tener
      el campo en la tabla. Lo mismo ocurre con el parametro name de la anotacion @Table.*/
    @Column(nullable = false, length = 70/*,name ="lastName"*/)
    private String lastName;
    @Column(nullable = false, length = 8)
    private String dni;
    @Column(nullable = false, length = 150)
    private String address;
    @Column(length = 9)
    private String phone;
    @Column(nullable = false, length = 55)
    private String email;
}

package com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
//cambiamos el nombre de como se va a llamar en la bd la entidad User, ya que User es una palabra reservada en postgres.
@Table(name = "user_data")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idUser;

    @Column(nullable = false, length = 60, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String enabled;

    /*5.3 Mapeo de muchos a muchos :: (2da forma) Esta forma es más limitada y es para mapear una llave primaria compuesta en otra tabla de bd.
    En esta ultima forma, no se va a crear la clase-entidad UserRole, pero si va a permitir generar la tabla user_role
    (ver la figura del modelo de bd en el directorio raiz del proyecto: "Modelo de BD de mediaapp.png") al momento de ejecutar la migracion. Una tabla que tiene una llave
    primaria compuesta nace de la relacion de muchos a muchos de varias tablas . Como atributo va la lista de entidades con la quien tiene la relacion de muchos a muchos.
    El valor FetchType.EAGER quiere decir que siempre Spring boot al momento de traer el valor de un objeto User de la bd, va a traer su atributo-lista roles,
    para este caso. Si fuese el valor LAZY, querria decir que no va a traer su atributo-lista roles. El valor del parametro name del parametro joinColumns es el nombre del
    campo de esta tabla user_role y que se vincula con el atributo @Id de esta clase y el valor del parametro referencedColumnName del parametro joinColumns es el nombre
    del etributo @Id de esta clase. El valor del parametro name del parametro inverseJoinColumns es el nombre del
    campo de esta tabla user_role y que se vincula con el atributo @Id de la clase generica Role que va en List y el valor del parametro referencedColumnName del parametro inverseJoinColumns es el nombre
    del etributo @Id de la clase generica Role que va en List.*/
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
        joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "idUser"),
        inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "idRole")
    )
    private List<Role> roles;
}

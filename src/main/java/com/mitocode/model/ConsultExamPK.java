package com.mitocode.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

/*5.2 Mapeo de muchos a muchos :: Esta es la entidad que representa la llave primaria compuesta. Esta contiene las entidades padre junto a sus respectivas anotaciones.
* Ademas contiene la anotacion @Embeddable y ademas la clase implementa la interfaz Serializable.*/
@Embeddable
@EqualsAndHashCode
public class ConsultExamPK implements Serializable
{
    @ManyToOne
    @JoinColumn(name = "id_consult")
    private Consult consult;

    @ManyToOne
    @JoinColumn(name = "id_exam")
    private Exam exam;
}

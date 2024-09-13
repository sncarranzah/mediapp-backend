package com.mitocode.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
/*5.1 Mapeo de muchos a muchos :: (1era forma y es la mejor)Si se tiene una llave primaria  compuesta de multiples llaves foráneas como es en este caso (llave primaria
compuesta por id_consult e id_exam), entonces el compilador de JDK no t va a permitir tener multiples anotaciones @Id en esta entidad hija. Lo que hay q hacer es crear
un clase que va a representar la llave primaria compuesta, en este caso, ConsultExamPK. Hay que agregar la siguiente anotacion con su parametro personalizado:*/
@IdClass(ConsultExamPK.class)
public class ConsultExam {
    @Id
    private Consult consult;

    @Id
    private Exam exam;

}

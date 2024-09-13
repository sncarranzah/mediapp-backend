package com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Consult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idConsult;

    /*3.1 Mapeo ManyToOne :: Si es una FK lo que se va a mapear, lo mas probable es q convenga traer toda la entidad. Ademas para este tipo de caso,siempre se debe de poner la
    anotacion @ManyToOne y el valor del parametro name de la anotacion @JoinColumn debe ser igual al nombre del fk de la tabla foranea o hija.*/
    @ManyToOne
    @JoinColumn(name = "id_patient", nullable = false, foreignKey = @ForeignKey(name="FK_CONSULT_PATIENT"))
    private Patient patient;

    @ManyToOne //FK
    @JoinColumn(name = "id_medic", nullable = false, foreignKey = @ForeignKey(name="FK_CONSULT_MEDIC"))
    private Medic medic;

    @ManyToOne //FK
    @JoinColumn(name = "id_specialty", nullable = false, foreignKey = @ForeignKey(name="FK_CONSULT_SPECIALTY"))
    private Specialty specialty;

    @Column(length = 3, nullable = false)
    private String numConsult;

    @Column(nullable = false)
    private LocalDateTime consultDate;

    /*4.1.1 Mapeo OneToMany :: Esta lista tiene todos los detalles de esta entidad consulta. Este mapeo no tiene ningun valor a nivel de esta tabla Consult
    en la bd. El valor del parametro mappedBy es el nombre del atributo de tipo de esta clase, en este caso Consult, que pertenece a la clase de tipo
    * generica que tiene este atributo List<>, en este caso ConsultDetail.*/

    /*4.1.2 Mapeo OneToMany ::El CascadeType.ALL sirve para cuando, por ejemplo, se inserte ,a nivel de clases, un Curso y  se inserte un correspodiente  CursoUsuario,
    entonces primero se inserte el Curso y luego el CursoUsuario (en cascada); o cuando se elimine un Curso y se elimine un correspondiente
    CursoUsuario, se elimine primero el CursoUsuario y luego el Curso.*/

    /*4.1.3 Mapeo OneToMany ::El parametro orphanRemoval = true hace que Spring boot elimine registros de cursos_usuarios en la bd. Esto va a pasar
    cuando a nivel de objetos de java se hace un remove a ciertos elementos (registros) de la lista cursoUsuarioList de curso y luego se hace su respectivo
     this.cursoRepository.save(curso) en la capa de servicio.*/
    @OneToMany(mappedBy = "consult", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConsultDetail> details;
}

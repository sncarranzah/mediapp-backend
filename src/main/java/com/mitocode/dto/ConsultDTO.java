package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ConsultDTO {
    @EqualsAndHashCode.Include
    private Integer idConsult;
    /*13.1.1 Insertando en maestro detalle desde backend :: Cuando se tiene un DTO anidado, como en este caso que PatientDTO
    * está dentro de ConsultDTO y en el equivalente a nivel de entidad (objeto que representa la tabla en bd) del DTO contenedor, el DTO anidado
    * es un FK de la entidad contenedora,
    * entonces cuando se mande desde el cliente a la aplicacion el contenido del DTO anidado en json, solamente es
    * necesario mandar el id de este DTO anidado, mas no sus demas atributos que pueda tener.
    * Pero cuando se tenga una lista de DTO anidados, como en este caso, List<ConsultDetailDTO> esta dentro de ConsultDTO y en el equivalente a la
    entidad del DTO contenedor, el DTO anidado tiene la anotacion @OneToMany,  entonces cuando se mande desde el cliente a la aplicacion el contenido
    del DTO anidado en json, es necesario mandar todo el contenido de ese DTO anidado.*/
    @NotNull
    private PatientDTO patient;

    @NotNull
    private MedicDTO medic;

    @NotNull
    private SpecialtyDTO specialty;

    @NotNull
    private String numConsult;

    @NotNull
    /*13.1.2 Insertando en maestro detalle desde backend :: Es mejor usar la clase LocalDateTime para fechas que usar la clase Date
    * del paquete java.util.*/
    private LocalDateTime consultDate;

    /*13.5 Insertando en maestro detalle desde backend :: Esta anotacion tambien es necesaria para que se pueda hacer la insercion en bd maestro y detalle en 1 sola tanda.*/
    @JsonManagedReference
    @NotNull
    private List<ConsultDetailDTO> details;
}

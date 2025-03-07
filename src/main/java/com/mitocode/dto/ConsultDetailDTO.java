package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ConsultDetailDTO {
    @EqualsAndHashCode.Include
    private Integer idDetail;

    /* 13.4 Insertando en maestro detalle desde backend :: Cada vez que se inserte al padre en bd, en este caso, ConsultDTO, Spring le pasara el id de este padre al
    campo ConsultDTO consult de este DTO ConsultDetailDTO. Este punto y el punto siguiente es necesario hacer para insertar en bd unas tablas maestro y detalle en una
    sola tanda desde la tabla maestro.*/
    @JsonBackReference
    private ConsultDTO consult;

    @NotNull
    private String diagnosis;

    @NotNull
    private String treatment;
}

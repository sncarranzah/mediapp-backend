package com.mitocode.controller;

import com.mitocode.dto.MedicDTO;
import com.mitocode.model.Medic;
import com.mitocode.service.IMedicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/medics")
@RequiredArgsConstructor
public class MedicController {
    private final IMedicService medicService;
    /*12.3 Parametrizando controller y GenericResponse:: Se determina que a este atributo se le va a inyectar el bean ModelMapper con alias
    * medicMapper. Ademas hay que crear el archivo reservado de configuracion de lombok lombok.config en la raiz del proyecto y agregarle
    *un contenido determinado para que lombok reconozca la anotacion @Qualifier al momento de hacer la inyeccion de dependencias
    *debido a su anotacion @RequiredArgsConstructor. Luego, es recomendable  ejecutar el comando mvn clean  en la consola del proyecto
    * o (para intellij) seleccionar el boton lateral Maven -> expandir el arbol de proyecto actual -> expandir el arbol de Lifecycle ->
    * doble click sobre la opcion clean.*/
    @Qualifier("medicMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<MedicDTO>> findAll() throws Exception
    {
        List<MedicDTO> medicDTOList = this.medicService.findAll().stream().map(p -> this.convertToDto(p)).toList();
        return ResponseEntity.ok(medicDTOList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MedicDTO> findById(@PathVariable("id") Integer id) throws Exception
    {
        Medic medic = this.medicService.findById(id);
        return ResponseEntity.ok(this.convertToDto(medic));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody MedicDTO medicDTO) throws Exception
    {
        Medic medicResponse = this.medicService.save(this.convertToEntity(medicDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(medicResponse.getIdMedic()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicDTO> update(@PathVariable("id") Integer id,@Valid @RequestBody MedicDTO medicDTO) throws Exception
    {
        medicDTO.setIdMedic(id);
        Medic medicBD = this.medicService.update(id, this.convertToEntity(medicDTO));
        return ResponseEntity.ok(this.convertToDto(medicBD));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception
    {
        this.medicService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private MedicDTO convertToDto(Medic medic)
    {
        return this.modelMapper.map(medic, MedicDTO.class);
    }

    private Medic convertToEntity(MedicDTO medicDTO)
    {
        return this.modelMapper.map(medicDTO, Medic.class);
    }

}

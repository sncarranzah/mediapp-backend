package com.mitocode.controller;

import com.mitocode.dto.SpecialtyDTO;
import com.mitocode.model.Specialty;
import com.mitocode.service.ISpecialtyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/specialties")
@RequiredArgsConstructor
public class SpecialtyController {
    private final ISpecialtyService specialtyService;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<SpecialtyDTO>> findAll() throws Exception
    {
        List<SpecialtyDTO> specialtyDTOList = this.specialtyService.findAll().stream().map(p -> this.convertToDto(p)).toList();
        return ResponseEntity.ok(specialtyDTOList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> findById(@PathVariable("id") Integer id) throws Exception
    {
        Specialty specialty = this.specialtyService.findById(id);
        return ResponseEntity.ok(this.convertToDto(specialty));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody SpecialtyDTO specialtyDTO) throws Exception
    {
        Specialty specialtyResponse = this.specialtyService.save(this.convertToEntity(specialtyDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(specialtyResponse.getIdSpecialty()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> update(@PathVariable("id") Integer id,@Valid @RequestBody SpecialtyDTO specialtyDTO) throws Exception
    {
        specialtyDTO.setIdSpecialty(id);
        Specialty specialtyBD = this.specialtyService.update(id, this.convertToEntity(specialtyDTO));
        return ResponseEntity.ok(this.convertToDto(specialtyBD));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception
    {
        this.specialtyService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private SpecialtyDTO convertToDto(Specialty specialty)
    {
        return this.modelMapper.map(specialty, SpecialtyDTO.class);
    }

    private Specialty convertToEntity(SpecialtyDTO specialtyDTO)
    {
        return this.modelMapper.map(specialtyDTO, Specialty.class);
    }

}

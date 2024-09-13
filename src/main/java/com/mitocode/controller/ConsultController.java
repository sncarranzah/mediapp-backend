package com.mitocode.controller;

import com.mitocode.dto.ConsultDTO;
import com.mitocode.dto.ConsultListExamDTO;
import com.mitocode.model.Consult;
import com.mitocode.model.Exam;
import com.mitocode.service.IConsultService;
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
@RequestMapping("/consults")
@RequiredArgsConstructor
public class ConsultController {
    private final IConsultService consultService;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ConsultDTO>> findAll() throws Exception
    {
        List<ConsultDTO> consultDTOList = this.consultService.findAll().stream().map(p -> this.convertToDto(p)).toList();
        return ResponseEntity.ok(consultDTOList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ConsultDTO> findById(@PathVariable("id") Integer id) throws Exception
    {
        Consult consult = this.consultService.findById(id);
        return ResponseEntity.ok(this.convertToDto(consult));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ConsultListExamDTO consultListExamDTO) throws Exception
    {
        Consult consult = this.convertToEntity(consultListExamDTO.getConsult());
        List<Exam> examLst = consultListExamDTO.getLstExam().stream().map(e -> this.modelMapper.map(e, Exam.class)).toList();
        Consult consultResponse = this.consultService.saveTransactional(consult, examLst);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(consultResponse.getIdConsult()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultDTO> update(@PathVariable("id") Integer id,@Valid @RequestBody ConsultDTO consultDTO) throws Exception
    {
        consultDTO.setIdConsult(id);
        Consult consultBD = this.consultService.update(id, this.convertToEntity(consultDTO));
        return ResponseEntity.ok(this.convertToDto(consultBD));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception
    {
        this.consultService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ConsultDTO convertToDto(Consult consult)
    {
        return this.modelMapper.map(consult, ConsultDTO.class);
    }

    private Consult convertToEntity(ConsultDTO consultDTO)
    {
        return this.modelMapper.map(consultDTO, Consult.class);
    }

}

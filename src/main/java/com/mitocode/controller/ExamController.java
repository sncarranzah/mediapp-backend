package com.mitocode.controller;

import com.mitocode.dto.ExamDTO;
import com.mitocode.model.Exam;
import com.mitocode.service.IExamService;
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
@RequestMapping("/exams")
@RequiredArgsConstructor
public class ExamController {
    private final IExamService examService;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ExamDTO>> findAll() throws Exception
    {
        List<ExamDTO> examDTOList = this.examService.findAll().stream().map(p -> this.convertToDto(p)).toList();
        return ResponseEntity.ok(examDTOList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> findById(@PathVariable("id") Integer id) throws Exception
    {
        Exam exam = this.examService.findById(id);
        return ResponseEntity.ok(this.convertToDto(exam));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ExamDTO examDTO) throws Exception
    {
        Exam examResponse = this.examService.save(this.convertToEntity(examDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(examResponse.getIdExam()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamDTO> update(@PathVariable("id") Integer id,@Valid @RequestBody ExamDTO examDTO) throws Exception
    {
        examDTO.setIdExam(id);
        Exam examBD = this.examService.update(id, this.convertToEntity(examDTO));
        return ResponseEntity.ok(this.convertToDto(examBD));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception
    {
        this.examService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ExamDTO convertToDto(Exam exam)
    {
        return this.modelMapper.map(exam, ExamDTO.class);
    }

    private Exam convertToEntity(ExamDTO examDTO)
    {
        return this.modelMapper.map(examDTO, Exam.class);
    }

}

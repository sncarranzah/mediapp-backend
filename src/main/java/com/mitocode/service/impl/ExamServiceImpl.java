package com.mitocode.service.impl;

import com.mitocode.model.Exam;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.repository.IExamRepo;
import com.mitocode.service.IExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl extends CRUDServiceImpl<Exam, Integer> implements IExamService {

    private final IExamRepo patientRepository;

    @Override
    protected IGenericRepo<Exam, Integer> getRepository() {
        return this.patientRepository;
    }
}

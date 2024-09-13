package com.mitocode.service.impl;

import com.mitocode.model.Specialty;
import com.mitocode.repository.ISpecialtyRepo;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.service.ISpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecialtyServiceImpl extends CRUDServiceImpl<Specialty, Integer> implements ISpecialtyService {

    private final ISpecialtyRepo patientRepository;

    @Override
    protected IGenericRepo<Specialty, Integer> getRepository() {
        return this.patientRepository;
    }
}

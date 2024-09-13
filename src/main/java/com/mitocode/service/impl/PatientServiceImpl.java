package com.mitocode.service.impl;

import com.mitocode.model.Patient;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.repository.IPatientRepo;
import com.mitocode.service.IPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
//Esta anotacion de la libreria de lombok es util para implementar la inyeccion de dependencias.
@RequiredArgsConstructor
public class PatientServiceImpl extends CRUDServiceImpl<Patient, Integer> implements IPatientService {

    private final IPatientRepo patientRepository;

    @Override
    protected IGenericRepo<Patient, Integer> getRepository() {
        return this.patientRepository;
    }
}

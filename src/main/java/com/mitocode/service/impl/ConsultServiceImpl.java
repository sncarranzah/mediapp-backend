package com.mitocode.service.impl;

import com.mitocode.model.Consult;
import com.mitocode.model.Exam;
import com.mitocode.repository.IConsultExamRepo;
import com.mitocode.repository.IConsultRepo;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.service.IConsultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultServiceImpl extends CRUDServiceImpl<Consult, Integer> implements IConsultService {

    private final IConsultRepo consultRepository;
    private final IConsultExamRepo consultExamRepository;

    @Override
    protected IGenericRepo<Consult, Integer> getRepository() {
        return this.consultRepository;
    }

    /* 13.6 Insertando en maestro detalle desde backend :: Esta anotacion es para que todo el metodo sea atomico a nivel de bd y en caso suceda un error, se haga un rollback
    *en la bd.*/
    @Transactional
    @Override
    public Consult saveTransactional(Consult consult, List<Exam> examLst) {
        this.consultRepository.save(consult);
/*13.4 Insertando en maestro detalle desde backend :: El metodo saveConsultExam no trabaja con su entidad ConsultExam, sino con variables simples. Tampoco se esta
haciendo uso del metodo save del CRUD del JPA repository. */
        examLst.forEach(exam -> this.consultExamRepository.saveConsultExam(consult.getIdConsult(), exam.getIdExam()));
        return consult;
    }
}

package com.mitocode.service;

import com.mitocode.model.Consult;
import com.mitocode.model.Exam;

import java.util.List;

public interface IConsultService extends ICRUDService<Consult,Integer> {
    Consult saveTransactional(Consult consult, List<Exam> examLst);
}

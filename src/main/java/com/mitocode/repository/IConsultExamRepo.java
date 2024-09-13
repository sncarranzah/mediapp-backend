package com.mitocode.repository;

import com.mitocode.model.ConsultExam;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IConsultExamRepo extends IGenericRepo<ConsultExam, Integer>{
    /* 13.2.2 Insertando en maestro detalle desde backend :: Ademas, el query por defecto espera el uso de un select, asi que si voy a usar un insert, update o delete,
    entonces tengo que colocar la anotacion @Modifying . */
    @Modifying
    /*13.2.1 Insertando en maestro detalle desde backend :: La anotacion @Query es para implementar un query manual de una bd. Por defecto este trabaja con JPQL
    (Java Persistence Query Language), el cual es un query sql orientado a objetos, el cual es compatible con todos los motores de bd.
    Pero para este caso vamos a trabajar con un query nativo (tal y como se escribiria en un motor de bd específico, en este caso: postgres), eso lo logramos asignandole true
    * al parametro nativeQuery. Para este caso, este query nativo utiliza 2 variables: idConsult e idExam, las cuales estan enlazadas respectivamente a los parametros
    * idConsult_ e idExam_ del metodo abstracto saveExam.*/
    @Query(value ="INSERT INTO consult_exam(id_consult, id_exam) VALUES(:idConsult, :idExam)", nativeQuery = true)
    Integer saveConsultExam(@Param("idConsult") Integer idConsult_, @Param("idExam") Integer idExam_);
}

package com.mitocode.service.impl;

import com.mitocode.model.Medic;
import com.mitocode.model.Patient;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.repository.IMedicRepo;
import com.mitocode.repository.IPatientRepo;
import com.mitocode.service.IMedicService;
import com.mitocode.service.IPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

//@RequiredArgsConstructor
public class MedicServiceImpl extends CRUDServiceImpl<Medic, Integer> implements IMedicService {

    /*1.1.1 Inyeccion de dependencias :: Inyeccion de dependencias en Spring es que Spring reconozca una clase y que genere una instancia de esta y  la registre dentro
    de un contenedor, llamado Spring IoC Container,
    Una manera en que spring reconozca una clase es que tenga 1 de las 4 anotaciones de los estereotipos de spring:
    @Repository, @Service, @Controller, @Component. A todas las instancias registradas dentro del contenedor se les conoce como beans.
    Los beans tienen un tipo de ciclo de vida singleton, es decir, siempre se trabaja con la misma instancia del objeto inyectado durante todo el tiempo de vida de la aplicación.  */
    private IMedicRepo medicRepository;
    /*1.1.2 Inyeccion de dependencias :: La primera forma de inyectar dependencias es por medio del atributo con ayuda de la anotacion @Autowired. La segunda forma de inyectar
    dependencias es por medio del constructor y en esta 2da forma hay 2 subformas: La primera declarando la dependencia como atributo y luego asignandole un valor
    (valor que Spring le dará en tiempo de ejecucion) en el constructor siempre y cuando, y esto ultimo es casi para cualquier forma de inyeccion, encuentre a la clase que implementa
    la interfaz con la que esta declarada la dependencia como bean. Pero por ejm no es necesario que el IMedicRepo este esteriotipado, ya que la anotacion JpaRepository ya
    lo incluye.*/
    public MedicServiceImpl(IMedicRepo medicRepository)
    {
        this.medicRepository = medicRepository;
    }

    @Override
    protected IGenericRepo<Medic, Integer> getRepository() {
        return this.medicRepository;
    }
}

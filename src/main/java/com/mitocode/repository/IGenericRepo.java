package com.mitocode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/*7.1 Refactoring de capas:: El JpaRepository está esperando que la clase genérica T sea del tipo que le otorga la anotacion @Entity, pero como en esta interface no existe
* tal anotacion y T es solo de tipo Object, entonces se usa esta anotacion para que Spring Boot no lo trate, debido a la interfaz JpaRepository,
* como un bean.*/
@NoRepositoryBean
public interface IGenericRepo<T, ID> extends JpaRepository<T, ID> {
}

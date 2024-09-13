package com.mitocode.service.impl;

import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.service.ICRUDService;
import java.util.List;

public abstract class CRUDServiceImpl <T, ID> implements ICRUDService<T, ID>
{
    protected abstract IGenericRepo<T, ID> getRepository();


    /*2.3 Introduccion a spring data jpa :: Tanto un update como un save en bd se hace con el mismo save del JpaRepository.
     * JpaRepository hace la diferencia entre update y save, ya que con el primero se realiza cuando id de la entidad ya existe en la bd, y
     * con el segundo, save, se realiza cuando el id de la entidad no existe en la bd.*/
    @Override
    public T save(T t) throws Exception {
        return this.getRepository().save(t);
    }

    @Override
    public T update(ID id, T t) throws Exception {
        this.getRepository().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        return this.getRepository().save(t);
    }

    @Override
    public List<T> findAll() throws Exception {
        return this.getRepository().findAll();
    }

    @Override
    public T findById(ID id) throws Exception {
/*8.1 Manejo de excepciones:: El metodo orElseThrow() se activa solamente cuando su metodo que está a su izquierda inmediato devuelva null. Lo que devuelve cuando
* se activa es una funcion lambda que devuelva una excepcion y el orElseThrow lanza esa excepcion. Para ver la teoria de excepciones ver
* el documento: CursosLibres\Java\Java SE\Java SE.docx*/
        return this.getRepository().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
    }

    @Override
    public void delete(ID id) throws Exception {
        this.getRepository().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        this.getRepository().deleteById(id);
    }

}

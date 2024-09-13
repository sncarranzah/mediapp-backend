package com.mitocode.controller;

import com.mitocode.dto.PatientDTO;
import com.mitocode.model.Patient;
import com.mitocode.service.IPatientService;
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

//@RequestMapping("/patients")
/*12.1 Parametrizando controller y GenericResponse:: Si el parametro del RequestMapping es una variable, su valor lo buscará en el archivo application.properties.*/
@RequestMapping("${patient.controller.path}")
@RequiredArgsConstructor
public class PatientController {
    /*1.2 Inyeccion de dependencias :: La segunda subforma y la mejor forma de todas es de declarar la dependencia como atributo final y hacer uso de la anotacion
    @RequiredArgsConstructor de la libreria lombok y de esta manera se evita implementar el constructor, ya que lombok lo implementa por debajo.*/
    private final IPatientService patientService;
    /*9.1.2 Uso de ModelMapper::Se inyecta el ModelMapper*/
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<PatientDTO>> findAll() throws Exception
    {
        List<PatientDTO> patientDTOList = this.patientService.findAll().stream().map(p -> this.convertToDto(p)).toList();
        return ResponseEntity.ok(patientDTOList);
        //return new ResponseEntity<>(patientList, HttpStatus.OK);
    }
    /*6.1.1 API REST con ResponseEntity:: Un navegador web solo hace peticiones del tipo GET. No hace peticiones ni del tipo PUT, ni DELETE, ni POST, etc...
    * La clase ResponseEntity te permite controlar y estandarizar tu respuesta HTTP, tanto la cabezera como  el cuerpo.*/
    @GetMapping("/{id}")
    /*6.1.2 API REST con ResponseEntity:: La anotacion @PathVariable va a permitir recuperar una variable desde la url, llamada, en este caso, id.*/
    public ResponseEntity<PatientDTO> findById(@PathVariable("id") Integer id) throws Exception
    {
        Patient patient = this.patientService.findById(id);
        return ResponseEntity.ok(this.convertToDto(patient));
    }

    @PostMapping
    /*6.1.3 API REST con ResponseEntity:: La anotacion @RequestBody convierte un objeto json que viene de la capa cliente en un objeto java.*/
    /*10.2 Validacion de inputs:: La anotacion @Valid es util para que spring boot realize las validaciones definidas en la clase PatientDTO.*/
    public ResponseEntity<Void> save(@Valid @RequestBody PatientDTO patientDTO) throws Exception
    {
        Patient patientBD = this.patientService.save(this.convertToEntity(patientDTO));
        /*6.1.4 API REST con ResponseEntity:: Aqui va la clase que representa a la url que ira en la cabecera de la respuesta de este endpoint y que busca en la bd y muestra
        (si se ejecuta en el postman o en el navegador por ejemplo), por el id, el nuevo paciente creado. El url  interno
        que construye esta clase es: localhost:8080/patients/{id} , siendo {id} una variable, parametro del metodo path, que toma el valor
        en tiempo de ejecucion del parametro del metodo buildAndExpand(...). Esto se hace para cumplir con un punto del modelo de madurez de Richardson*/
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(patientBD.getIdPatient()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
/*9.1.3 Uso de ModelMapper:: Para este caso tanto el DtoResponse como el DtoRequest son lo mismo, por eso tanto en la respuesta del metodo como en el parametro
* de entrada se usa la misma clase de dto. Es más, el PatientDTO como la entidad Patient tambien son lo mismo.*/
    public ResponseEntity<PatientDTO> update(@PathVariable("id") Integer id,@Valid @RequestBody PatientDTO patientDTO) throws Exception
    {
        patientDTO.setIdPatient(id);
        Patient patientBD = this.patientService.update(id, this.convertToEntity(patientDTO));
        return ResponseEntity.ok(this.convertToDto(patientBD));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception
    {
        this.patientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private PatientDTO convertToDto(Patient patient)
    {
/*9.1.1 Uso de ModelMapper:: Los DTO sirven para representar aquello especial que el  cliente quiere ver y que no se encuentra en esa forma en la bd  o
viceversa, si la bd cambia, pero el cliente quiere seguir viendo sus datos de la misma forma. Tambien los DTO es util por un tema
de seguridad. La libreria ModelMapper, al igual que la libreria MapStruct son utiles para hacer la transformacion automatizada entre una clase
entidad y una clase DTO. La libreria ModelMapper se pueda buscar su etiqueta xml de dependencia pom.xml en la pagina web del repositorio
de maven : mvnrepository.com y luego agregarlo al pom.xml.*/
        return this.modelMapper.map(patient, PatientDTO.class);
    }

    private Patient convertToEntity(PatientDTO patientDTO)
    {
        return this.modelMapper.map(patientDTO, Patient.class);
    }

}

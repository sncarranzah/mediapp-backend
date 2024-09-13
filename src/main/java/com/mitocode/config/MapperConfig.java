package com.mitocode.config;

import com.mitocode.dto.MedicDTO;
import com.mitocode.model.Medic;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*9.2.1 Uso de ModelMapper::Esta anotacion es para declarar q en esta clase van a ir definidos beans, los cuales, en tiempo de ejecucion, serán  inyectados
por dependencia por Spring Boot y siempre y cuando el bean sea declarado como un atributo readonly de una clase.*/
@Configuration
public class MapperConfig {
/*9.2.2 Uso de ModelMapper:: Esta anotacion es para indicar que este método devuelve una determinada clase de inyeccion de dependencia (no importa el nombre del método)
, importa lo q retorna el método.*/
/*12.2.1 Parametrizando controller y GenericResponse:: Este bean al igual al que le sigue son de la misma clase, asi que para que Spring boot
* sepa qué bean inyectar en un momento determinado, es necesario ponerle un alias. Para este caso, le hemos puesto defaultMapper y
* medicMapper.*/
    @Bean("defaultMapper")
    public ModelMapper getDefaultMapper()
    {
        return new ModelMapper();
    }
    @Bean("medicMapper")
    public ModelMapper getMedicMapper()
    {
        ModelMapper mapper = new ModelMapper();

/*12.2.2 Parametrizando controller y GenericResponse:: Aqui se hace el mapeo manual para aquellos atributos que ModelMapper no puede
* mapear por sí mismo, tanto para pasar de un DTO a un Entity (escritura), como para pasar de un Entity a un DTO (lectura) .*/
        //escritura
        TypeMap<MedicDTO, Medic> typeMap1 = mapper.createTypeMap(MedicDTO.class, Medic.class);
        /*Esta linea y la siguientes son equivalentes.*/
        //typeMap1.addMapping(e -> e.getPrimaryName(), (dest,v) -> dest.setFirstName((String) v));
        typeMap1.addMapping(MedicDTO::getPrimaryName, (dest,v) -> dest.setFirstName((String) v));
        typeMap1.addMapping(MedicDTO::getSurname, (dest,v) -> dest.setLastName((String) v));
        typeMap1.addMapping(MedicDTO::getPhoto, (dest,v) -> dest.setPhotoUrl((String) v));

        //Lectura
        TypeMap<Medic, MedicDTO> typeMap2 = mapper.createTypeMap(Medic.class, MedicDTO.class);
        typeMap2.addMapping(Medic::getFirstName, (dest,v) -> dest.setPrimaryName((String) v));
        typeMap2.addMapping(Medic::getLastName, (dest,v) -> dest.setSurname((String) v));

        return mapper;


    }

}

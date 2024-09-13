package com.mitocode.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class MessageConfig
{
    /*11.2.1 Manejo de idiomas en backend:: Estos 2 primeros metodos son para cargar en memoria principal los archivos de idiomas.*/
    @Bean
    public MessageSource getMessageSource()
    {
        ReloadableResourceBundleMessageSource reldResBundMsgeSrc = new ReloadableResourceBundleMessageSource();
/*11.2.2 Manejo de idiomas en backend:: El 'classpath:' siempre va, pero lo q sigue debe ser el prefijo de los nombres de los archivos de idiomas,
* en este caso: 'messages'.*/
        reldResBundMsgeSrc.setBasename("classpath:messages");
        return reldResBundMsgeSrc;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator()
    {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(this.getMessageSource());
        return bean;
    }
 /*11.2.3 Manejo de idiomas en backend:: Este metodo es para definir el idioma por defecto con la que trabajara nuestra aplicaciom.*/
    @Bean
    /*11.2.4 Manejo de idiomas en backend:: El parametro "prototype" de esta anotacion es para indicar que el ciclo de vida de este bean a inyectar es por request.
    * Esta configuracion traera como consecuencia que cada usuario maneje su propio idioma.*/
    @Scope("prototype")
    public LocaleResolver localeResolver()
    {
        SessionLocaleResolver slr = new SessionLocaleResolver();
/*11.2.4 Manejo de idiomas en backend:: Al definir el idioma a Locale.ROOT, estamos declarando que el archivo de idioma q se cargará es el que no tenga en su nombre
una extension de idioma (fr, en), para este caso se cargará el archivo messages.properties .*/
        slr.setDefaultLocale(Locale.ROOT);
        return slr;
    }
}

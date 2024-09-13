package com.mitocode.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@RestController
@RequestMapping("/languages")
@RequiredArgsConstructor
public class LanguageController {
    private final LocaleResolver localeResolver;
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

/*11.3 Manejo de idiomas en backend:: Este metodo permite el cambio del idioma de esta aplicacion por el usuario.
La biblioteca de archivos de idiomas se encuentra en src/main/resources . Es @GetMapping porque no hace cambio en la bd, solo hace cambio de la memoria principal (RAM).*/
    @GetMapping("/locale/{loc}")
    public ResponseEntity<Void> changeLocaleLanguage(@PathVariable("loc") String loc)
    {
        Locale userLocale;
        if ( loc.equals("en") || loc.equals("us") ) userLocale = Locale.ENGLISH;
        else if ( loc.equals("fr") )  userLocale = Locale.FRENCH;
        else userLocale = Locale.ROOT;
        this.localeResolver.setLocale(this.httpServletRequest, this.httpServletResponse, userLocale);
        return ResponseEntity.ok().build();
    }
}

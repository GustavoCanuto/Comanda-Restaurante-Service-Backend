package com.projetoweb4.comandaRestaurante.infra.handler;

import java.util.Set;
import java.util.stream.Collectors;

import org.reflections.Reflections;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import com.projetoweb4.comandaRestaurante.enumeration.EnumBase;
import com.projetoweb4.comandaRestaurante.infra.config.EnumBasePropertyEditor;

@ControllerAdvice
public class GlobalControllerAdvice {

    private static final Reflections reflections = new Reflections("com.projetoweb4.comandaRestaurante.enumeration"); // Ajuste o pacote base conforme necessário

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        Set<Class<? extends Enum<?>>> enumClasses = findEnumsImplementing(EnumBase.class);
        for (Class<? extends Enum<?>> enumClass : enumClasses) {
            EnumBasePropertyEditor<?> propertyEditor = new EnumBasePropertyEditor<>(enumClass);
			binder.registerCustomEditor(enumClass, propertyEditor);
        }
    }

    private Set<Class<? extends Enum<?>>> findEnumsImplementing(Class<?> markerInterface) {
        // Obter todos os subtipos de Enum
        @SuppressWarnings("unchecked")
		Set<Class<? extends Enum<?>>> enums = reflections.getSubTypesOf(Enum.class).stream()
            .filter(clazz -> clazz.isEnum() && markerInterface.isAssignableFrom(clazz))
            .map(clazz -> (Class<? extends Enum<?>>) clazz) 
            .collect(Collectors.toSet());

        return enums;
    }
}

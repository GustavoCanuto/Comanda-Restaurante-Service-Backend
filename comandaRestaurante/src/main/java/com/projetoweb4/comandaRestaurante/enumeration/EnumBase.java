package com.projetoweb4.comandaRestaurante.enumeration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.projetoweb4.comandaRestaurante.validacoes.EnumInvalidoException;

public interface EnumBase {
    String getEnumName();
    
    static <E extends Enum<E> & EnumBase> E fromString(Class<E> enumClass, String valor) {
        for (E enumConstant : enumClass.getEnumConstants()) {
            if (enumConstant.getEnumName().equalsIgnoreCase(valor)) {
                return enumConstant;
            }
        }
        List<String> options = listOptions(enumClass);
        throw new EnumInvalidoException(valor, options);
    }
    
    static <E extends Enum<E> & EnumBase> List<String> listOptions(Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                     .map(EnumBase::getEnumName)
                     .collect(Collectors.toList());
    }
}

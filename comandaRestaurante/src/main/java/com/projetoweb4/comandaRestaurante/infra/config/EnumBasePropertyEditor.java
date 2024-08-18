package com.projetoweb4.comandaRestaurante.infra.config;
import java.beans.PropertyEditorSupport;

import com.projetoweb4.comandaRestaurante.enumeration.EnumBase;

public class EnumBasePropertyEditor<E extends Enum<E> & EnumBase> extends PropertyEditorSupport {
    private final Class<E> enumType;

    @SuppressWarnings("unchecked")
	public EnumBasePropertyEditor(Class<? extends Enum<?>> enumClass) {
        this.enumType = (Class<E>) enumClass;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null || text.isEmpty()) setValue(null);
        else setValue(EnumBase.fromString(enumType, text));
        
    }
}
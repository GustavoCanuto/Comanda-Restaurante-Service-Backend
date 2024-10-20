package com.projetoweb4.comandaRestaurante.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StatusGeralEnum implements EnumBase{

 	ATIVO((short) 1, 'A'),
 	DESATIVADO((short) 2, 'D');
	
	private final Short id;
    private final char status;
    
    StatusGeralEnum(Short id, char status) {
        this.id = id;
        this.status = status;
    }
    
    public Short getId() {
        return id;
    }
    
    public char getStatus() {
		return status;
	}

	@Override
    public String getEnumName() {
        return name();
    }
    
	@JsonCreator
	public static StatusGeralEnum fromString(String valor) {
		return EnumBase.fromString(StatusGeralEnum.class, valor);
	}
}


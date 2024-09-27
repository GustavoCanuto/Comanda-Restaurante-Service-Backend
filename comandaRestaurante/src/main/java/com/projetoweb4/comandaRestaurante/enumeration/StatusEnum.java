package com.projetoweb4.comandaRestaurante.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StatusEnum implements EnumBase{

 	A_FAZER((short) 1, 'A'),
 	FAZENDO((short) 2, 'F'), 
    PRONTO((short) 3, 'P');
	
	private final Short id;
    private final char status;
    
    StatusEnum(Short id, char status) {
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
	public static StatusEnum fromString(String valor) {
		return EnumBase.fromString(StatusEnum.class, valor);
	}
}


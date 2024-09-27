package com.projetoweb4.comandaRestaurante.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CargoFuncionarioEnum implements EnumBase{

 	COZINHEIRO((short) 1, "Cozinheiro"),
 	GARCON((short) 2, "Garçon"), 
    GERENTE((short) 3, "Gerente");
	
	private final Short id;
    private final String nome;
    
    CargoFuncionarioEnum(Short id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public Short getId() {
        return id;
    }
    
    public String getNome() {
		return nome;
	}

	@Override
    public String getEnumName() {
        return name();
    }
    
	@JsonCreator
	public static CargoFuncionarioEnum fromString(String valor) {
		return EnumBase.fromString(CargoFuncionarioEnum.class, valor);
	}
}


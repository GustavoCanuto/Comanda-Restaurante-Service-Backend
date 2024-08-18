package com.projetoweb4.comandaRestaurante.enumeration;

public enum TipoEnumTeste implements EnumBase{

 	P((short) 1, "Prato"),
 	B((short) 2, "Bebida"), 
    S((short) 3, "Sobremesa");
	
	private final Short id;
    private final String nome;
    
    TipoEnumTeste(Short id, String nome) {
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
    
}


package com.projetoweb4.comandaRestaurante.enumeration;

public enum TipoProdutoEnum implements EnumBase{

 	PRATO((short) 1, "Prato"),
 	BEBIDA((short) 2, "Bebida"), 
    SOBREMESA((short) 3, "Sobremesa");
	
	private final Short id;
    private final String nome;
    
    TipoProdutoEnum(Short id, String nome) {
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

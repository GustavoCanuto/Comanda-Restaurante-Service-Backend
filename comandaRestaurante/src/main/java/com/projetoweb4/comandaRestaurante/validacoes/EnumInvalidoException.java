package com.projetoweb4.comandaRestaurante.validacoes;

import java.util.List;

public class EnumInvalidoException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String valorInvalido;
    private final List<String> opcoes;

    public EnumInvalidoException(String valorInvalido, List<String> opcoes) {
        super("Valor " + valorInvalido + " inválido. Utilize uma das opções: " + String.join(", ", opcoes));
        this.valorInvalido = valorInvalido;
        this.opcoes = opcoes;
    }

    public String getValorInvalido() {
        return valorInvalido;
    }

    public List<String> getOpcoes() {
        return opcoes;
    }
}
package com.projetoweb4.comandaRestaurante.dto.funcionario;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projetoweb4.comandaRestaurante.entity.Funcionario;
import com.projetoweb4.comandaRestaurante.entity.domain.CargoFuncionario;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record FuncionarioDtoDetalhar(
		Long id,
		String nome,
		String cpf,
		CargoFuncionario cargoFuncionario
		) {

	public FuncionarioDtoDetalhar(Funcionario entidade) {
		this(entidade.getId(), entidade.getNome(), entidade.getCpf(), entidade.getCargoFuncionario());
	}

}

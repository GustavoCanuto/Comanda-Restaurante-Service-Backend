package com.projetoweb4.comandaRestaurante.dto.funcionario;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projetoweb4.comandaRestaurante.dto.domain.CargoFuncionarioDtoDetalhar;
import com.projetoweb4.comandaRestaurante.entity.Funcionario;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record FuncionarioDtoDetalhar(
		Long id,
		String nome,
		String cpf,
		CargoFuncionarioDtoDetalhar cargoFuncionario
		) {

	public FuncionarioDtoDetalhar(Funcionario entidade) {
		this(entidade.getId(), entidade.getNome(), entidade.getCpf(),  new CargoFuncionarioDtoDetalhar(entidade.getCargoFuncionario()));
	}

}

package com.projetoweb4.comandaRestaurante.dto.domain;

import com.projetoweb4.comandaRestaurante.entity.domain.CargoFuncionario;

public record CargoFuncionarioDtoDetalhar(
		Short id,
		String cargo,
		String descricao
		) {

	public CargoFuncionarioDtoDetalhar(CargoFuncionario entidade) {
		this(entidade.getId(), entidade.getCargo(), entidade.getDescricao());
	}

}

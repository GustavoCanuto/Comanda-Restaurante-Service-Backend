package com.projetoweb4.comandaRestaurante.dto.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projetoweb4.comandaRestaurante.dto.domain.StatusDtoDetalhar;
import com.projetoweb4.comandaRestaurante.dto.funcionario.FuncionarioDtoDetalhar;
import com.projetoweb4.comandaRestaurante.entity.Login;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record LoginDtoDetalhar(
		Long id,
		String email,
		FuncionarioDtoDetalhar funcionario,
		StatusDtoDetalhar statusGeral
		) {

	public LoginDtoDetalhar(Login entidade) {
		this(entidade.getId(), entidade.getEmail(), new FuncionarioDtoDetalhar(entidade.getFuncionario()) ,
				new StatusDtoDetalhar(entidade.getStatusGeral())
				);
	}

}

package com.projetoweb4.comandaRestaurante.dto.domain;

import com.projetoweb4.comandaRestaurante.entity.domain.StatusGeral;
import com.projetoweb4.comandaRestaurante.entity.domain.StatusProcesso;

public record StatusDtoDetalhar(
		Short id,
		char status,
		String descricao
		) {

	public StatusDtoDetalhar(StatusProcesso entidade) {
		this(entidade.getId(), entidade.getStatus(), entidade.getDescricao());
	}
	
	public StatusDtoDetalhar(StatusGeral entidade) {
		this(entidade.getId(), entidade.getStatus(), entidade.getDescricao());
	}

}

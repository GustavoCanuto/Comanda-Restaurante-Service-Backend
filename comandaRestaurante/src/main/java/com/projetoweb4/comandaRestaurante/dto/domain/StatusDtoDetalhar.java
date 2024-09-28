package com.projetoweb4.comandaRestaurante.dto.domain;

import com.projetoweb4.comandaRestaurante.entity.domain.Status;

public record StatusDtoDetalhar(
		Short id,
		char status,
		String descricao
		) {

	public StatusDtoDetalhar(Status entidade) {
		this(entidade.getId(), entidade.getStatus(), entidade.getDescricao());
	}

}

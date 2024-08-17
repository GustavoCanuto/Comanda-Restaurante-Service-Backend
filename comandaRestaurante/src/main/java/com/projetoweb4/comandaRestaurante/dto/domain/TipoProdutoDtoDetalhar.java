package com.projetoweb4.comandaRestaurante.dto.domain;

import com.projetoweb4.comandaRestaurante.entity.domain.TipoProduto;

public record TipoProdutoDtoDetalhar(
		Long id,
		String nome
		) {

	public TipoProdutoDtoDetalhar(TipoProduto entidade) {
		this(entidade.getId(), entidade.getNome());
	}

}

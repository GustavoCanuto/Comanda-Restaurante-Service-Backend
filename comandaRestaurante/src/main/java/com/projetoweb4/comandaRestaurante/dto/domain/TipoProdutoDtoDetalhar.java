package com.projetoweb4.comandaRestaurante.dto.domain;

import com.projetoweb4.comandaRestaurante.entity.domain.TipoProduto;

public record TipoProdutoDtoDetalhar(
		Short id,
		String nome
		) {

	public TipoProdutoDtoDetalhar(TipoProduto entidade) {
		this(entidade.getId(), entidade.getNome());
	}

}

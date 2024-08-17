package com.projetoweb4.comandaRestaurante.dto.produto;

import com.projetoweb4.comandaRestaurante.entity.Produto;
import com.projetoweb4.comandaRestaurante.entity.domain.TipoProduto;

public record ProdutoDtoDetalhar(
		Long id,
		String nome,
		String descricao,
		Double preco,
		String linkImagem,
		TipoProduto tipoProduto
		) {

	public ProdutoDtoDetalhar(Produto entidade) {
		this(entidade.getId(), entidade.getNome(), entidade.getDescricao(),
				entidade.getPreco(), entidade.getLinkImagem(), entidade.getTipoProduto());
	}

}

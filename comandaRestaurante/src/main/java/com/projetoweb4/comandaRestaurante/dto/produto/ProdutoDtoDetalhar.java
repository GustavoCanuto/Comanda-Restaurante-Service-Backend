package com.projetoweb4.comandaRestaurante.dto.produto;

import com.projetoweb4.comandaRestaurante.dto.domain.StatusDtoDetalhar;
import com.projetoweb4.comandaRestaurante.dto.domain.TipoProdutoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.entity.Produto;

public record ProdutoDtoDetalhar(
		Long id,
		String nome,
		String descricao,
		Double preco,
		String linkImagem,
		TipoProdutoDtoDetalhar tipoProduto,
		StatusDtoDetalhar statusGeral
		) {

	public ProdutoDtoDetalhar(Produto entidade) {
		this(entidade.getId(), entidade.getNome(), entidade.getDescricao(),
				entidade.getPreco(), entidade.getLinkImagem(), new TipoProdutoDtoDetalhar(entidade.getTipoProduto()),
				new StatusDtoDetalhar(entidade.getStatusGeral()));
	}

}

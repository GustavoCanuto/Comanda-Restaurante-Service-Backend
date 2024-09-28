package com.projetoweb4.comandaRestaurante.dto.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projetoweb4.comandaRestaurante.dto.pedido.PedidoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.dto.produto.ProdutoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.entity.ItemPedido;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record LoginDtoDetalhar(
		Long id,
		Integer quantidade,
		String observacoes,
		PedidoDtoDetalhar pedido,
		ProdutoDtoDetalhar produto
		) {

	public LoginDtoDetalhar(ItemPedido entidade, boolean incluirPedido) {
		this(entidade.getId(), entidade.getQuantidade(), entidade.getObservacoes(),
				incluirPedido ? new PedidoDtoDetalhar(entidade.getPedido())  : null,
				new ProdutoDtoDetalhar(entidade.getProduto()));
	}

}

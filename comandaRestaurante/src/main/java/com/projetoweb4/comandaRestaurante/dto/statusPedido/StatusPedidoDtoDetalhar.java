package com.projetoweb4.comandaRestaurante.dto.statusPedido;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projetoweb4.comandaRestaurante.dto.pedido.PedidoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.dto.produto.ProdutoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.entity.ItemPedido;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StatusPedidoDtoDetalhar(
		Long id,
		Integer quantidade,
		String observacoes,
		PedidoDtoDetalhar pedido,
		ProdutoDtoDetalhar produto
		) {

	public StatusPedidoDtoDetalhar(ItemPedido entidade, boolean incluirPedido) {
		this(entidade.getId(), entidade.getQuantidade(), entidade.getObservacoes(),
				incluirPedido ? new PedidoDtoDetalhar(entidade.getPedido())  : null,
				new ProdutoDtoDetalhar(entidade.getProduto()));
	}

}

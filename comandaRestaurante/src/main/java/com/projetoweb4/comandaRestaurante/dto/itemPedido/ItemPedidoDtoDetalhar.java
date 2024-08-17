package com.projetoweb4.comandaRestaurante.dto.itemPedido;

import com.projetoweb4.comandaRestaurante.entity.ItemPedido;
import com.projetoweb4.comandaRestaurante.entity.Pedido;
import com.projetoweb4.comandaRestaurante.entity.Produto;

public record ItemPedidoDtoDetalhar(
		Long id,
		String quantidade,
		String observacoes,
		Pedido pedido,
		Produto produto
		) {

	public ItemPedidoDtoDetalhar(ItemPedido entidade) {
		this(entidade.getId(), entidade.getQuantidade(), entidade.getObservacoes(),
				entidade.getPedido(), entidade.getProduto());
	}

}

package com.projetoweb4.comandaRestaurante.dto.pedido;

import java.util.List;

import com.projetoweb4.comandaRestaurante.dto.itemPedido.ItemPedidoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.entity.Pedido;

public record PedidoDtoDetalhar(
		Long id,
		Integer mesa,
		String comanda,
		List<ItemPedidoDtoDetalhar> itensPedido
		) {

	public PedidoDtoDetalhar(Pedido entidade) {
		this(entidade.getId(), entidade.getMesa(), entidade.getComanda(),
				entidade.getItensPedido().stream()
				  .map(item -> new ItemPedidoDtoDetalhar(item, false)) 
			    .toList());
	}

}

package com.projetoweb4.comandaRestaurante.dto.pedido;

import java.util.List;

import com.projetoweb4.comandaRestaurante.entity.ItemPedido;
import com.projetoweb4.comandaRestaurante.entity.Pedido;

public record PedidoDtoDetalhar(
		Long id,
		String mesa,
		String comanda,
		List<ItemPedido> itensPedido
		) {

	public PedidoDtoDetalhar(Pedido entidade) {
		this(entidade.getId(), entidade.getMesa(), entidade.getComanda(),
				 entidade.getItensPedido());
	}

}

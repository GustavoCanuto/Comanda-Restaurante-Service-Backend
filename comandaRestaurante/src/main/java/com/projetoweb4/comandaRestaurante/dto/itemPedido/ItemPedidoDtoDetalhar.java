package com.projetoweb4.comandaRestaurante.dto.itemPedido;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projetoweb4.comandaRestaurante.dto.pedido.PedidoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.dto.produto.ProdutoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.dto.statusItemPedido.ControleStatusItemPedidoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.entity.ItemPedido;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ItemPedidoDtoDetalhar(
		Long id,
		String observacao,
		ProdutoDtoDetalhar produto,
		ControleStatusItemPedidoDtoDetalhar controleStatusItemPedidoDtoDetalhar,
		PedidoDtoDetalhar pedido
		) {

	public ItemPedidoDtoDetalhar(ItemPedido entidade, boolean incluirPedido) {
		this(entidade.getId(), entidade.getObservacao(),
				new ProdutoDtoDetalhar(entidade.getProduto()),
				new ControleStatusItemPedidoDtoDetalhar(entidade.getControleStatusItemPedido()),
				incluirPedido ? new PedidoDtoDetalhar(entidade.getPedido())  : null);
	}

}

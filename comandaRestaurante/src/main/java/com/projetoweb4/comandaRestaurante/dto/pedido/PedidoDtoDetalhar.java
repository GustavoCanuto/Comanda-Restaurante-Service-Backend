package com.projetoweb4.comandaRestaurante.dto.pedido;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.projetoweb4.comandaRestaurante.dto.funcionario.FuncionarioDtoDetalhar;
import com.projetoweb4.comandaRestaurante.dto.itemPedido.ItemPedidoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.entity.Pedido;

public record PedidoDtoDetalhar(
		Long id,
		Integer mesa,
		String comanda,
		LocalDateTime dataHoraPedido,
		List<ItemPedidoDtoDetalhar> itensPedido,
		FuncionarioDtoDetalhar funcionario
		) {

	public PedidoDtoDetalhar(Pedido entidade) {
		this(entidade.getId(), entidade.getMesa(), entidade.getComanda(),
				entidade.getDataHoraPedido(),
				entidade.getItensPedido().stream()
				  .map(item -> new ItemPedidoDtoDetalhar(item, false)) 
			    .toList(),
			    Objects.nonNull(entidade.getFuncionario()) ? 
						new FuncionarioDtoDetalhar(entidade.getFuncionario()) : null
				);
	}

}

package com.projetoweb4.comandaRestaurante.dto.pedido;

import java.util.List;

import com.projetoweb4.comandaRestaurante.dto.itemPedido.ItemPedidoDtoCadastrar;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PedidoDtoCadastrar(
		@NotNull
		Integer mesa,
		@NotNull
		@Size(max = 255)
		String comanda,
		@NotNull
		@Valid
		List<ItemPedidoDtoCadastrar> itensPedido
		) {

}

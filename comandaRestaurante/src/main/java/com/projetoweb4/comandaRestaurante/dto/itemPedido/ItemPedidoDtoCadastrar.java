package com.projetoweb4.comandaRestaurante.dto.itemPedido;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ItemPedidoDtoCadastrar(
		@NotNull
		@Size(max = 255)
		String quantidade,
		@NotNull
		@Size(max = 255)
		String observacoes,
		@NotNull
		Long idProduto,
		Long idPedido// se usar endpoint adiconar item fica obrigatorio informar, é ignorado no endpoint Pedido
		) {

}

package com.projetoweb4.comandaRestaurante.dto.itemPedido;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ItemPedidoDtoCadastrar(
		@NotBlank
		@Size(max = 255)
		String observacoes,
		@NotNull
		Long idProduto,
		Long idPedido
		) {

}

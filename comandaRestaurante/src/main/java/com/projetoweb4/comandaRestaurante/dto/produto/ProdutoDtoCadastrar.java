package com.projetoweb4.comandaRestaurante.dto.produto;

import com.projetoweb4.comandaRestaurante.enumeration.TipoProdutoEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProdutoDtoCadastrar(
		@NotNull
		@Size(max = 255)
		String nome,
		@NotNull
		@Size(max = 255)
		String descricao,
		@NotNull
		@Positive
		Double preco,
		@Size(max = 255)
		String linkImagem,
		@NotNull
		@Enumerated(EnumType.STRING)
		TipoProdutoEnum tipoProduto
		) {

}

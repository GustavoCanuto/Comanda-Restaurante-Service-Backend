package com.projetoweb4.comandaRestaurante.dto.produto;

import org.springframework.web.multipart.MultipartFile;

import com.projetoweb4.comandaRestaurante.enumeration.TipoProdutoEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProdutoDtoCadastrar(
		@NotBlank
		@Size(max = 255)
		String nome,
		@NotBlank
		@Size(max = 255)
		String descricao,
		@NotNull
		@Positive
		Double preco,
		@NotNull
		MultipartFile imagem,
		@NotNull
		@Enumerated(EnumType.STRING)
        TipoProdutoEnum tipoProduto
		) {

}

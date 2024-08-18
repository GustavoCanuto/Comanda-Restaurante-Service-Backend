package com.projetoweb4.comandaRestaurante.dto.produto;

import org.springframework.web.multipart.MultipartFile;

import com.projetoweb4.comandaRestaurante.enumeration.TipoEnumTeste;
import com.projetoweb4.comandaRestaurante.enumeration.TipoProdutoEnum;

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
		@NotNull
		MultipartFile imagem,
        TipoProdutoEnum tipoProduto,
        TipoEnumTeste tipoTeste
		) {

}

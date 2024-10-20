package com.projetoweb4.comandaRestaurante.dto.statusItemPedido;

import com.projetoweb4.comandaRestaurante.enumeration.StatusProcessoEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ControleStatusItemPedidoDtoUpdate(
		@NotBlank
		@Size(max = 255)
		String descricao,
		Long idFuncionario,//buscar automatico pelo login depois
		@NotNull
		@Enumerated(EnumType.STRING)
        StatusProcessoEnum status
		) {

}


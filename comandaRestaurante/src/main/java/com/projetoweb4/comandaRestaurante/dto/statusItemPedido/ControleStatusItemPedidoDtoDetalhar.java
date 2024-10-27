package com.projetoweb4.comandaRestaurante.dto.statusItemPedido;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projetoweb4.comandaRestaurante.dto.domain.StatusDtoDetalhar;
import com.projetoweb4.comandaRestaurante.dto.funcionario.FuncionarioDtoDetalhar;
import com.projetoweb4.comandaRestaurante.entity.ControleStatusItemPedido;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ControleStatusItemPedidoDtoDetalhar(
		Long id,
		StatusDtoDetalhar status,
		String descricao,
		LocalDateTime dataHoraIniciado,
		LocalDateTime dataHoraPronto,
		LocalDateTime dataHoraEntregue,
		FuncionarioDtoDetalhar funcionario
		) {

	public ControleStatusItemPedidoDtoDetalhar(ControleStatusItemPedido entidade) {
		this(entidade.getId(), new StatusDtoDetalhar(entidade.getStatus()),
			 entidade.getDescricaoStatus(),  
			entidade.getDataHoraIniciado(), entidade.getDataHoraPronto(), 
			entidade.getDataHoraEntregue(),
			new FuncionarioDtoDetalhar(entidade.getFuncionario())
		);
	}

}

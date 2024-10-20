package com.projetoweb4.comandaRestaurante.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projetoweb4.comandaRestaurante.dto.itemPedido.ItemPedidoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.dto.statusItemPedido.ControleStatusItemPedidoDtoUpdate;
import com.projetoweb4.comandaRestaurante.entity.ControleStatusItemPedido;
import com.projetoweb4.comandaRestaurante.entity.ItemPedido;
import com.projetoweb4.comandaRestaurante.enumeration.StatusProcessoEnum;
import com.projetoweb4.comandaRestaurante.repository.StatusPedidoRepository;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarFuncionario;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarItemPedido;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarStatusProcesso;

@Service
public class ControleStatusItemPedidoService {

	@Autowired
	private StatusPedidoRepository repository;
	
	@Autowired
	private BuscarStatusProcesso getStatus;
	
	@Autowired
	private BuscarItemPedido getItemPedido;
	
	@Autowired
	private BuscarFuncionario getFuncionario;

	public Page<ItemPedidoDtoDetalhar> atualizar(List<Long> idsItemPedido ,ControleStatusItemPedidoDtoUpdate dados, Pageable paginacao) {
		
		//funcionario pegar do jwt
		
		 // Coleta os itens pedidos a partir dos IDs
	    List<ItemPedido> itensPedido = idsItemPedido.stream()
	        .map(item -> getItemPedido.buscar(item))
	        .collect(Collectors.toList());  // Coleta em uma lista

	    // Atualiza o controle de status para cada item pedido
	    for (ItemPedido item : itensPedido) {
	        ControleStatusItemPedido controleStatus = item.getControleStatusItemPedido();
	        
	        controleStatus.setDescricaoStatus(dados.descricao());
	        controleStatus.setFuncionario(getFuncionario.buscar(dados.idFuncionario()));
	        controleStatus.setStatus(getStatus.buscar(dados.status().getId()));
	        
	        if(dados.status() == StatusProcessoEnum.FAZENDO) {
	            controleStatus.setDataHoraIniciado(LocalDateTime.now());
	        } else if(dados.status() == StatusProcessoEnum.PRONTO) {
	            controleStatus.setDataHoraPronto(LocalDateTime.now());
	        } else if(dados.status() == StatusProcessoEnum.ENTREGUE) {
	            controleStatus.setDataHoraEntregue(LocalDateTime.now());
	        }
	        
	        // Salva o controle de status no repositório
	        repository.save(controleStatus);
	    }

	    // Mapeia os itens atualizados para DTOs
	    List<ItemPedidoDtoDetalhar> detalhes = itensPedido.stream()
	            .map(item -> new ItemPedidoDtoDetalhar(item, false))  // Correção na chamada do construtor
	            .collect(Collectors.toList());

	    // Retorna a lista de DTOs como uma página
	    return new PageImpl<>(detalhes, paginacao, detalhes.size());
	}

}

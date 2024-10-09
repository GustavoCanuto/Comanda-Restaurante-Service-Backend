package com.projetoweb4.comandaRestaurante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoweb4.comandaRestaurante.dto.itemPedido.ItemPedidoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.dto.statusItemPedido.ControleStatusItemPedidoDtoUpdate;
import com.projetoweb4.comandaRestaurante.service.ControleStatusItemPedidoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("controle-status-item-pedido")
public class ControleStatusItemPedidoController {

	@Autowired
	private ControleStatusItemPedidoService service;

	@PutMapping("/{idsItemPedido}")
	@Transactional
	public ResponseEntity<Page<ItemPedidoDtoDetalhar>> cadastrar(
			@RequestBody @Valid ControleStatusItemPedidoDtoUpdate dados, 																						
			@PageableDefault(size = 10) Pageable paginacao,
			@PathVariable List<Long> idsItemPedido) {
		
		   // Chama o serviço para atualizar os itens e obter os detalhes
	    Page<ItemPedidoDtoDetalhar> entidade = service.atualizar(idsItemPedido, dados, paginacao);

	    // Retorna a resposta com o status 200 e os dados da entidade
	    return ResponseEntity.ok(entidade);
	}

//	@GetMapping
//	public ResponseEntity<Page<ControleStatusItemPedidoDtoDetalhar>> listar(@PageableDefault(size = 10) Pageable paginacao) {
//
//		return ResponseEntity.ok(service.listarTodos(paginacao));
//	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<ControleStatusItemPedidoDtoDetalhar> detalhar(@PathVariable Long id) {
//
//		return ResponseEntity.ok(service.buscarPorId(id));
//	}

}

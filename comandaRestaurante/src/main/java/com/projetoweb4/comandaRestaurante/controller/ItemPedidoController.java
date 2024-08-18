package com.projetoweb4.comandaRestaurante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.projetoweb4.comandaRestaurante.dto.itemPedido.ItemPedidoDtoCadastrar;
import com.projetoweb4.comandaRestaurante.dto.itemPedido.ItemPedidoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.service.ItemPedidoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("item-pedido")
public class ItemPedidoController {

	@Autowired
	private ItemPedidoService service;


	@PostMapping
	@Transactional
	public ResponseEntity<ItemPedidoDtoDetalhar> cadastrar(@RequestBody @Valid ItemPedidoDtoCadastrar dados,
			UriComponentsBuilder uriBuilder) {
		
		var entidade = service.cadastrar(dados);

		var uri = uriBuilder.path("/item-pedido/{id}").buildAndExpand(entidade.id()).toUri();

		return ResponseEntity.created(uri).body(entidade);
	}
	

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Long id) {

		service.deletar(id);

		return ResponseEntity.noContent().build();
	}
	
	
	@GetMapping
	public ResponseEntity<Page<ItemPedidoDtoDetalhar>> listar(@PageableDefault(size = 10) Pageable paginacao) {
		return ResponseEntity.ok(service.listarTodos(paginacao));

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ItemPedidoDtoDetalhar> detalhar(@PathVariable Long id) {
		return ResponseEntity.ok(service.buscarPorId(id));
	}

}
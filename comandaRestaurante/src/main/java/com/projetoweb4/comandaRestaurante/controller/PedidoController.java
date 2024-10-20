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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.projetoweb4.comandaRestaurante.dto.pedido.PedidoDtoCadastrar;
import com.projetoweb4.comandaRestaurante.dto.pedido.PedidoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.enumeration.StatusProcessoEnum;
import com.projetoweb4.comandaRestaurante.service.PedidoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("pedido")
public class PedidoController {

	@Autowired
	private PedidoService service;

	@PostMapping
	@Transactional
	public ResponseEntity<PedidoDtoDetalhar> cadastrar(@RequestBody @Valid PedidoDtoCadastrar dados,
			UriComponentsBuilder uriBuilder) {
		
		var entidade = service.cadastrar(dados);

		var uri = uriBuilder.path("/pedido/{id}").buildAndExpand(entidade.id()).toUri();

		return ResponseEntity.created(uri).body(entidade);
	}
	

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Long id) {

		service.deletar(id);

		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<Page<PedidoDtoDetalhar>> listar(
			@PageableDefault(size = 10) Pageable paginacao) {
		
		return ResponseEntity.ok(service.listarTodos(paginacao));
	}

	@GetMapping("/status")
	public ResponseEntity<Page<PedidoDtoDetalhar>> listarPorStatus(
			@RequestParam(required = false) StatusProcessoEnum statusProcesso,
			@PageableDefault(size = 10) Pageable paginacao) {
		
		return ResponseEntity.ok(service.listarTodosPorStatus(paginacao, statusProcesso));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoDtoDetalhar> detalhar(@PathVariable Long id) {
		
		return ResponseEntity.ok(service.buscarPorId(id));
	}
}

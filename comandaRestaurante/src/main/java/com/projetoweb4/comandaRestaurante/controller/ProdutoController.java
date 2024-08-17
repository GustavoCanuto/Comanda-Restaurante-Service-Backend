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

import com.projetoweb4.comandaRestaurante.dto.produto.ProdutoDtoCadastrar;
import com.projetoweb4.comandaRestaurante.dto.produto.ProdutoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.service.ProdutoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("produto")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@PostMapping
	@Transactional
	public ResponseEntity<ProdutoDtoDetalhar> cadastrar(@RequestBody @Valid ProdutoDtoCadastrar dados,
			UriComponentsBuilder uriBuilder) {

		var entidade = service.cadastrar(dados);

		var uri = uriBuilder.path("/produto/{id}").buildAndExpand(entidade.id()).toUri();

		return ResponseEntity.created(uri).body(entidade);

	}
	
	@GetMapping
	public ResponseEntity<Page<ProdutoDtoDetalhar>> listar(@PageableDefault(size = 10) Pageable paginacao) {
		return null;
		//return ResponseEntity.ok(pedidoService.listarPedidos(paginacao));

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDtoDetalhar> detalhar(@PathVariable Long id) {
		return null;
		//return ResponseEntity.ok(cidadeService.detalharCidade(id));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Long id) {

		service.deletar(id);

		return ResponseEntity.noContent().build();
	}
}

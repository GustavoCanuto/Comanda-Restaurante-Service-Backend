package com.projetoweb4.comandaRestaurante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoweb4.comandaRestaurante.dto.funcionario.FuncionarioDtoCadastrar;
import com.projetoweb4.comandaRestaurante.dto.funcionario.FuncionarioDtoDetalhar;
import com.projetoweb4.comandaRestaurante.service.FuncionarioService;

import jakarta.transaction.Transactional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;

//	@PostMapping
//	@Transactional
//	public ResponseEntity<FuncionarioDtoDetalhar> cadastrar(@RequestBody @Valid FuncionarioDtoCadastrar dados, 																						
//			UriComponentsBuilder uriBuilder) throws IOException {
//		
//		var entidade = service.cadastrar(dados);
//
//		var uri = uriBuilder.path("/funcionario/{id}").buildAndExpand(entidade.id()).toUri();
//
//		return ResponseEntity.created(uri).body(entidade);
//	}

	@PreAuthorize("hasRole('GERENTE')")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<FuncionarioDtoDetalhar> atualizar(@PathVariable Long id,
			@RequestBody FuncionarioDtoCadastrar dados) {

		return ResponseEntity.ok(service.atualizar(dados, id));
	}
	
//	@GetMapping
//	public ResponseEntity<Page<FuncionarioDtoDetalhar>> listar(@PageableDefault(size = 10) Pageable paginacao) {
//
//		return ResponseEntity.ok(service.listarTodos(paginacao));
//	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<FuncionarioDtoDetalhar> detalhar(@PathVariable Long id) {
//
//		return ResponseEntity.ok(service.buscarPorId(id));
//	}

}

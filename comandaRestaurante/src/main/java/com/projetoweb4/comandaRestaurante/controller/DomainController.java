package com.projetoweb4.comandaRestaurante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoweb4.comandaRestaurante.dto.domain.TipoProdutoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.repository.domain.TipoProdutoRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("dominio")
public class DomainController {

	@Autowired
	private TipoProdutoRepository tipoProdutoRepository;

	@GetMapping("tipo-produto")
	public ResponseEntity<List<TipoProdutoDtoDetalhar>> listar() {

		return ResponseEntity.ok(tipoProdutoRepository.findAll().stream().map(TipoProdutoDtoDetalhar::new).toList());

	}
	
}

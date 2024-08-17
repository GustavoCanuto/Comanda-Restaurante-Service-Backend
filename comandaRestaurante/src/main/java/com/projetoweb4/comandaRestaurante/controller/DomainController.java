package com.projetoweb4.comandaRestaurante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetoweb4.comandaRestaurante.dto.domain.TipoProdutoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.repository.domain.TipoProdutoRepository;
import com.projetoweb4.comandaRestaurante.service.PedidoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("dominio")
public class DomainController {

	@Autowired
	private TipoProdutoRepository tipoProdutoRepository;

	@GetMapping
	public ResponseEntity<Page<TipoProdutoDtoDetalhar>> listar(@PageableDefault(size = 10) Pageable paginacao) {

		return null;
		//return ResponseEntity.ok(pedidoService.listarPedidos(paginacao));

	}
	
}

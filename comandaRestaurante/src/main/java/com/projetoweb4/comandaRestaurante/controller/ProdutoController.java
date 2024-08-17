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

import com.projetoweb4.comandaRestaurante.dto.produto.ProdutoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.service.PedidoService;
import com.projetoweb4.comandaRestaurante.service.ProdutoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

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
}

package com.projetoweb4.comandaRestaurante.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetoweb4.comandaRestaurante.dto.produto.ProdutoDtoCadastrar;
import com.projetoweb4.comandaRestaurante.dto.produto.ProdutoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.enumeration.TipoProdutoEnum;
import com.projetoweb4.comandaRestaurante.service.ProdutoService;
import org.springframework.http.MediaType;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("produto")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	 @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Transactional
	public ResponseEntity<ProdutoDtoDetalhar> cadastrar(
			@ModelAttribute @Valid ProdutoDtoCadastrar dados,          // Recebe o arquivo
			UriComponentsBuilder uriBuilder) throws IOException {

	 
		var entidade = service.cadastrar(dados, dados.imagem());

		var uri = uriBuilder.path("/produto/{id}").buildAndExpand(entidade.id()).toUri();

		return ResponseEntity.created(uri).body(entidade);

	}
	

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Long id) {

		service.deletar(id);

		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<Page<ProdutoDtoDetalhar>> listar(@PageableDefault(size = 10) Pageable paginacao) {
		
		return ResponseEntity.ok(service.listarTodos(paginacao));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDtoDetalhar> detalhar(@PathVariable Long id) {
		
		return ResponseEntity.ok(service.buscarPorId(id));
	}
	
}

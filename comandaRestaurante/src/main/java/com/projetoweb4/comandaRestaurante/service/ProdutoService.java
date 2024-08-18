package com.projetoweb4.comandaRestaurante.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projetoweb4.comandaRestaurante.dto.produto.ProdutoDtoCadastrar;
import com.projetoweb4.comandaRestaurante.dto.produto.ProdutoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.entity.Produto;
import com.projetoweb4.comandaRestaurante.entity.domain.TipoProduto;
import com.projetoweb4.comandaRestaurante.repository.ProdutoRepository;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarTipoProduto;

@Service
public class ProdutoService{

	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private BuscarTipoProduto getTipoProduto;
	
	@Autowired
	private UploadService uploadService;
	
	public ProdutoDtoDetalhar cadastrar(ProdutoDtoCadastrar dados) throws IOException {
		TipoProduto tipoProduto = getTipoProduto.buscar(dados.tipoProduto().getId());

		String imagemUrl = uploadService.uploadImage(dados.imagem(), "produto_", "imagesProduto");
		
		Produto produto = new Produto(dados, tipoProduto, imagemUrl); 

		repository.save(produto);

		return new ProdutoDtoDetalhar(produto);
	}

	public ProdutoDtoDetalhar buscarPorId(Long id) {
		return new ProdutoDtoDetalhar(repository.getReferenceById(id));
	}

	public Page<ProdutoDtoDetalhar> listarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(ProdutoDtoDetalhar::new);
	}

	public void deletar(Long id) {
		repository.deleteById(id);
		
	}

	public ProdutoDtoDetalhar atualizar(ProdutoDtoCadastrar dados, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}

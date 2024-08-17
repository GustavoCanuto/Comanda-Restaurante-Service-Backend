package com.projetoweb4.comandaRestaurante.service;

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
public class ProdutoService implements CrudService<ProdutoDtoDetalhar, ProdutoDtoCadastrar, Long>{

	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private BuscarTipoProduto getTipoProduto;
	
	@Override
	public ProdutoDtoDetalhar cadastrar(ProdutoDtoCadastrar dados) {
		TipoProduto tipoProduto = getTipoProduto.buscar(dados.tipoProduto().getId());

		Produto produto = new Produto(dados, tipoProduto); 

		repository.save(produto);

		return new ProdutoDtoDetalhar(produto);
	}

	@Override
	public ProdutoDtoDetalhar buscarPorId(Long id) {
		return new ProdutoDtoDetalhar(repository.getReferenceById(id));
	}

	@Override
	public Page<ProdutoDtoDetalhar> listarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(ProdutoDtoDetalhar::new);
	}

	@Override
	public void deletar(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public ProdutoDtoDetalhar atualizar(ProdutoDtoCadastrar dados, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}

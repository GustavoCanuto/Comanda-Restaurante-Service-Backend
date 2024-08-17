package com.projetoweb4.comandaRestaurante.service.buscador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projetoweb4.comandaRestaurante.entity.Produto;
import com.projetoweb4.comandaRestaurante.repository.ProdutoRepository;
import com.projetoweb4.comandaRestaurante.validacoes.ValidacaoException;

@Component
public class BuscarProduto implements BuscarEntidade<Produto, Long>  {

	@Autowired
	ProdutoRepository repository;
	
	Produto entidade;
	
	@Override
	public Produto buscar(Long id) {
		if (id != null) {

			entidade = repository.findById(id)
					.orElseThrow(() -> new ValidacaoException("Id de "+ Produto.class.getSimpleName() +" informado não existe!"));
		}

		return entidade;
	}

}

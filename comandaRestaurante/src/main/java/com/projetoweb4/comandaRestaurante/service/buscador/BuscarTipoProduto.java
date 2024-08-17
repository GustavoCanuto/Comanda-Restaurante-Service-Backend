package com.projetoweb4.comandaRestaurante.service.buscador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projetoweb4.comandaRestaurante.entity.domain.TipoProduto;
import com.projetoweb4.comandaRestaurante.repository.domain.TipoProdutoRepository;
import com.projetoweb4.comandaRestaurante.validacoes.ValidacaoException;

@Component
public class BuscarTipoProduto implements BuscarEntidade<TipoProduto, Short>  {

	@Autowired
	TipoProdutoRepository repository;
	
	TipoProduto entidade;
	
	@Override
	public TipoProduto buscar(Short id) {
		if (id != null) {

			entidade = repository.findById(id)
					.orElseThrow(() -> new ValidacaoException("Id informado não existe!"));
		}

		return entidade;
	}

}

package com.projetoweb4.comandaRestaurante.service.buscador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projetoweb4.comandaRestaurante.entity.ItemPedido;
import com.projetoweb4.comandaRestaurante.repository.ItemPedidoRepository;
import com.projetoweb4.comandaRestaurante.validacoes.ValidacaoException;

@Component
public class BuscarItemPedido implements BuscarEntidade<ItemPedido, Long>  {

	@Autowired
	ItemPedidoRepository repository;
	
	ItemPedido entidade;
	
	@Override
	public ItemPedido buscar(Long id) {
		if (id != null) {

			entidade = repository.findById(id)
					.orElseThrow(() -> new ValidacaoException("Id de "+ ItemPedido.class.getSimpleName() +" informado não existe!"));
		}

		return entidade;
	}

}

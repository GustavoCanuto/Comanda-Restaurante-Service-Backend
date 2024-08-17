package com.projetoweb4.comandaRestaurante.service.buscador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projetoweb4.comandaRestaurante.entity.Pedido;
import com.projetoweb4.comandaRestaurante.repository.PedidoRepository;
import com.projetoweb4.comandaRestaurante.validacoes.ValidacaoException;

@Component
public class BuscarPedido implements BuscarEntidade<Pedido, Long>  {

	@Autowired
	PedidoRepository repository;
	
	Pedido entidade;
	
	@Override
	public Pedido buscar(Long id) {
		if (id != null) {

			entidade = repository.findById(id)
					.orElseThrow(() -> new ValidacaoException("Id de "+ Pedido.class.getSimpleName() +" informado não existe!"));
		}

		return entidade;
	}

}

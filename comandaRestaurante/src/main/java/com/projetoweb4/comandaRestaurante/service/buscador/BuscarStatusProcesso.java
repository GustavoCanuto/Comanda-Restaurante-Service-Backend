package com.projetoweb4.comandaRestaurante.service.buscador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projetoweb4.comandaRestaurante.entity.domain.StatusProcesso;
import com.projetoweb4.comandaRestaurante.repository.domain.StatusProcessoRepository;
import com.projetoweb4.comandaRestaurante.validacoes.ValidacaoException;


@Component
public class BuscarStatusProcesso implements BuscarEntidade<StatusProcesso, Short>  {

	@Autowired
	StatusProcessoRepository repository;
	
	StatusProcesso entidade;
	
	@Override
	public StatusProcesso buscar(Short id) {
		if (id != null) {

			entidade = repository.findById(id)
					.orElseThrow(() -> new ValidacaoException("Id de "+ StatusProcesso.class.getSimpleName() +" informado não existe!"));
		}

		return entidade;
	}

}

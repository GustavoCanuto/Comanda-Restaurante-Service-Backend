package com.projetoweb4.comandaRestaurante.service.buscador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projetoweb4.comandaRestaurante.entity.domain.Status;
import com.projetoweb4.comandaRestaurante.repository.domain.StatusRepository;
import com.projetoweb4.comandaRestaurante.validacoes.ValidacaoException;


@Component
public class BuscarStatus implements BuscarEntidade<Status, Short>  {

	@Autowired
	StatusRepository repository;
	
	Status entidade;
	
	@Override
	public Status buscar(Short id) {
		if (id != null) {

			entidade = repository.findById(id)
					.orElseThrow(() -> new ValidacaoException("Id de "+ Status.class.getSimpleName() +" informado não existe!"));
		}

		return entidade;
	}

}

package com.projetoweb4.comandaRestaurante.service.buscador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projetoweb4.comandaRestaurante.entity.domain.StatusGeral;
import com.projetoweb4.comandaRestaurante.repository.domain.StatusGeralRepository;
import com.projetoweb4.comandaRestaurante.validacoes.ValidacaoException;


@Component
public class BuscarStatusGeral implements BuscarEntidade<StatusGeral, Short>  {

	@Autowired
	StatusGeralRepository repository;
	
	StatusGeral entidade;
	
	@Override
	public StatusGeral buscar(Short id) {
		if (id != null) {

			entidade = repository.findById(id)
					.orElseThrow(() -> new ValidacaoException("Id de "+ StatusGeral.class.getSimpleName() +" informado não existe!"));
		}

		return entidade;
	}

}

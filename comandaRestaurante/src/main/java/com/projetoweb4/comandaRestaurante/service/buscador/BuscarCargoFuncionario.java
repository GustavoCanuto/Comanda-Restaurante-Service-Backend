package com.projetoweb4.comandaRestaurante.service.buscador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projetoweb4.comandaRestaurante.entity.domain.CargoFuncionario;
import com.projetoweb4.comandaRestaurante.repository.domain.CargoFuncionarioRepository;
import com.projetoweb4.comandaRestaurante.validacoes.ValidacaoException;

@Component
public class BuscarCargoFuncionario implements BuscarEntidade<CargoFuncionario, Short>  {

	@Autowired
	CargoFuncionarioRepository repository;
	
	CargoFuncionario entidade;
	
	@Override
	public CargoFuncionario buscar(Short id) {
		if (id != null) {

			entidade = repository.findById(id)
					.orElseThrow(() -> new ValidacaoException("Id de "+ CargoFuncionario.class.getSimpleName() +" informado não existe!"));
		}

		return entidade;
	}

}

package com.projetoweb4.comandaRestaurante.service.buscador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projetoweb4.comandaRestaurante.entity.Funcionario;
import com.projetoweb4.comandaRestaurante.repository.FuncionarioRepository;
import com.projetoweb4.comandaRestaurante.validacoes.ValidacaoException;

@Component
public class BuscarFuncionario implements BuscarEntidade<Funcionario, Long>  {

	@Autowired
	FuncionarioRepository repository;
	
	Funcionario entidade;
	
	@Override
	public Funcionario buscar(Long id) {
		if (id != null) {

			entidade = repository.findById(id)
					.orElseThrow(() -> new ValidacaoException("Id de "+ Funcionario.class.getSimpleName() +" informado não existe!"));
		}

		return entidade;
	}

}

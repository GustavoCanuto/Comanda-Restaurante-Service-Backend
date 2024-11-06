package com.projetoweb4.comandaRestaurante.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoweb4.comandaRestaurante.dto.funcionario.FuncionarioDtoCadastrar;
import com.projetoweb4.comandaRestaurante.dto.funcionario.FuncionarioDtoDetalhar;
import com.projetoweb4.comandaRestaurante.entity.Funcionario;
import com.projetoweb4.comandaRestaurante.entity.domain.CargoFuncionario;
import com.projetoweb4.comandaRestaurante.repository.FuncionarioRepository;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarCargoFuncionario;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarFuncionario;
import com.projetoweb4.comandaRestaurante.validacoes.ValidacaoException;

@Service
public class FuncionarioService{

	@Autowired
	private FuncionarioRepository repository;
	
	@Autowired
	private BuscarCargoFuncionario getCargoFuncionario;
	
	@Autowired
	private BuscarFuncionario getFuncionario;

	public FuncionarioDtoDetalhar atualizar(FuncionarioDtoCadastrar dados, Long id) {
		
		if (repository.existsByCpf(dados.cpf())) {
			throw new ValidacaoException("Cpf já registrado!");
		}

		CargoFuncionario cargoFuncionario = getCargoFuncionario.buscar(dados.cargoFuncionario().getId());
		
		Funcionario funcionario = getFuncionario.buscar(id);
		funcionario.atualizarInformacoes(dados, cargoFuncionario);
		 
		repository.save(funcionario);
        
		return new FuncionarioDtoDetalhar(funcionario);
	}

}

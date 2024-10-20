package com.projetoweb4.comandaRestaurante.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class FuncionarioService implements CrudService<FuncionarioDtoDetalhar, FuncionarioDtoCadastrar, Long>{

	@Autowired
	private FuncionarioRepository repository;
	
	@Autowired
	private BuscarCargoFuncionario getCargoFuncionario;
	
	@Autowired
	private BuscarFuncionario getFuncionario;

	@Override
	public FuncionarioDtoDetalhar cadastrar(FuncionarioDtoCadastrar dados) {

		if (repository.existsByCpf(dados.cpf())) {
			throw new ValidacaoException("Cpf já registrado!");
		}

		CargoFuncionario cargoFuncionario = getCargoFuncionario.buscar(dados.cargoFuncionario().getId());
		
		Funcionario funcionario = new Funcionario(dados, cargoFuncionario); 

		repository.save(funcionario);
        
		return new FuncionarioDtoDetalhar(funcionario);
	}

	@Override
	public FuncionarioDtoDetalhar buscarPorId(Long id) {
		return new FuncionarioDtoDetalhar(repository.getReferenceById(id));
	}

	@Override
	public Page<FuncionarioDtoDetalhar> listarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(FuncionarioDtoDetalhar::new);
	}

	@Override
	public void deletar(Long id) {
		repository.deleteById(id);
	}

	@Override
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

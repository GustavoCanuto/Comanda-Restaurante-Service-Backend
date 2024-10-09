package com.projetoweb4.comandaRestaurante.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projetoweb4.comandaRestaurante.dto.login.LoginDtoCadastrar;
import com.projetoweb4.comandaRestaurante.dto.login.LoginDtoDetalhar;
import com.projetoweb4.comandaRestaurante.entity.Login;
import com.projetoweb4.comandaRestaurante.repository.LoginRepository;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarFuncionario;

@Service
public class LoginService implements CrudService<LoginDtoDetalhar, LoginDtoCadastrar, Long>{

	@Autowired
	private LoginRepository repository;
	
	@Autowired
	private BuscarFuncionario getFuncionario;

	@Override
	public LoginDtoDetalhar cadastrar(LoginDtoCadastrar dados) {

		Login login = new Login(dados, getFuncionario.buscar(dados.idFuncionario()));

		repository.save(login);

		return new LoginDtoDetalhar(login);
	}

	@Override
	public LoginDtoDetalhar buscarPorId(Long id) {
		return new LoginDtoDetalhar(repository.getReferenceById(id));
	}

	@Override
	public Page<LoginDtoDetalhar> listarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(LoginDtoDetalhar::new);
	}

	@Override
	public void deletar(Long id) {
		repository.deleteById(id);
	}

	@Override
	public LoginDtoDetalhar atualizar(LoginDtoCadastrar dados, Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	


}

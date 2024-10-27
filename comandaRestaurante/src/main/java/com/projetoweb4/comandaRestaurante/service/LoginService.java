package com.projetoweb4.comandaRestaurante.service;

import static java.util.Optional.ofNullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projetoweb4.comandaRestaurante.dto.login.LoginDtoCadastrar;
import com.projetoweb4.comandaRestaurante.dto.login.LoginDtoDetalhar;
import com.projetoweb4.comandaRestaurante.entity.Funcionario;
import com.projetoweb4.comandaRestaurante.entity.Login;
import com.projetoweb4.comandaRestaurante.entity.domain.CargoFuncionario;
import com.projetoweb4.comandaRestaurante.entity.domain.StatusGeral;
import com.projetoweb4.comandaRestaurante.enumeration.StatusGeralEnum;
import com.projetoweb4.comandaRestaurante.repository.FuncionarioRepository;
import com.projetoweb4.comandaRestaurante.repository.LoginRepository;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarCargoFuncionario;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarStatusGeral;
import com.projetoweb4.comandaRestaurante.validacoes.ValidacaoException;

@Service
public class LoginService implements CrudService<LoginDtoDetalhar, LoginDtoCadastrar, Long>{

	@Autowired
	private LoginRepository repository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private BuscarStatusGeral getStatusGeral;
	
	@Autowired
	private BuscarCargoFuncionario getCargoFuncionario;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public LoginDtoDetalhar cadastrar(LoginDtoCadastrar dados) {
		
		if (repository.existsByEmail(dados.email())) {
			throw new ValidacaoException("Email já registrado!");
		}
		
		if (funcionarioRepository.existsByCpf(dados.funcionario().cpf())) {
			throw new ValidacaoException("Cpf já registrado!");
		}

		CargoFuncionario cargoFuncionario = getCargoFuncionario.buscar(dados.funcionario().cargoFuncionario().getId());
		
		Funcionario funcionario = new Funcionario(dados.funcionario(), cargoFuncionario); 

		funcionarioRepository.save(funcionario);
		
		StatusGeral statusGeral = getStatusGeral.buscar(StatusGeralEnum.ATIVO.getId());

		String senhaCriptografada = passwordEncoder.encode(dados.senha());
		  
		Login login = new Login(dados.email(),senhaCriptografada, funcionario, statusGeral);

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
		Login login = ofNullable(repository.findById(id).get())
							.orElseThrow(() -> new ValidacaoException("Id de Login Inválido"));
		
		login.setStatusGeral(getStatusGeral.buscar(StatusGeralEnum.DESATIVADO.getId()));
		
		repository.save(login);
	}

	@Override
	public LoginDtoDetalhar atualizar(LoginDtoCadastrar dados, Long id) {
		
		//if usuario gerente pode atualizar somente email
		if (repository.existsByEmail(dados.email())) {
			throw new ValidacaoException("Email já registrado!");
		}
		
		
		return null;
	}
	
	public LoginDtoDetalhar atualizarSenha(LoginDtoCadastrar dados, Long id) {
		
		//usuario pode atualizar somente a senha dele automatico
		
		
		return null;
	}
	
	
	


}

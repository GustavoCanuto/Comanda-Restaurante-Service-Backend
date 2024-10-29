package com.projetoweb4.comandaRestaurante.service;

import static java.util.Optional.ofNullable;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projetoweb4.comandaRestaurante.dto.login.LoginDtoCadastrar;
import com.projetoweb4.comandaRestaurante.dto.login.LoginDtoDetalhar;
import com.projetoweb4.comandaRestaurante.dto.login.LoginTrocarSenhaDtoAtualizar;
import com.projetoweb4.comandaRestaurante.entity.Funcionario;
import com.projetoweb4.comandaRestaurante.entity.Login;
import com.projetoweb4.comandaRestaurante.entity.domain.CargoFuncionario;
import com.projetoweb4.comandaRestaurante.entity.domain.StatusGeral;
import com.projetoweb4.comandaRestaurante.enumeration.CargoFuncionarioEnum;
import com.projetoweb4.comandaRestaurante.enumeration.StatusGeralEnum;
import com.projetoweb4.comandaRestaurante.repository.FuncionarioRepository;
import com.projetoweb4.comandaRestaurante.repository.LoginRepository;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarCargoFuncionario;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarStatusGeral;
import com.projetoweb4.comandaRestaurante.service.recurso.TokenService;
import com.projetoweb4.comandaRestaurante.validacoes.ValidacaoException;

@Service
public class LoginService{

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
	
	@Autowired
	private TokenService tokenService;

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
		  
		Login login = new Login(dados.email().toUpperCase(),senhaCriptografada, funcionario, statusGeral);

		repository.save(login);

		return new LoginDtoDetalhar(login);
	}


	public LoginDtoDetalhar buscarPorId(Long id) {
		return new LoginDtoDetalhar(repository.getReferenceById(id));
	}


	public Page<LoginDtoDetalhar> listarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(LoginDtoDetalhar::new);
	}

	
	public void deletar(Long id) {
		Login login = ofNullable(repository.findById(id).get())
							.orElseThrow(() -> new ValidacaoException("Id de Login Inválido"));
		
		login.setStatusGeral(getStatusGeral.buscar(StatusGeralEnum.DESATIVADO.getId()));
		
		repository.save(login);
	}

	public LoginDtoDetalhar atualizarSenha(LoginTrocarSenhaDtoAtualizar dados) {
		
	    Funcionario funcionario = tokenService.getFuncionarioAutenticado();
	    
	    Login loginDoUsuario = repository.findByFuncionario(funcionario);
	    
	    // Verifica se a senha antiga fornecida pelo usuário corresponde à senha criptografada no banco de dados
	    if (passwordEncoder.matches(dados.senhaAntiga(), loginDoUsuario.getSenha())) {
	        String senhaCriptografada = passwordEncoder.encode(dados.novaSenha());
	        loginDoUsuario.setSenha(senhaCriptografada);
	    } else {
	        throw new ValidacaoException("Senha antiga incorreta.");
	    }
	    
	    repository.save(loginDoUsuario);

	    return new LoginDtoDetalhar(loginDoUsuario);
	}

	public Page<LoginDtoDetalhar> listarTodosPorStatus(
			Pageable paginacao, StatusGeralEnum statusGeral, CargoFuncionarioEnum cargoFuncionarioEnum) {
		
		if(!Objects.isNull(statusGeral) && !Objects.isNull(cargoFuncionarioEnum) ) {
			
			StatusGeral status = getStatusGeral.buscar(statusGeral.getId());
			CargoFuncionario cargoFuncionario = getCargoFuncionario.buscar(cargoFuncionarioEnum.getId());
			
			return repository
					.findByStatusGeralAndFuncionario_CargoFuncionario(status, cargoFuncionario, paginacao)
					.map(LoginDtoDetalhar::new);
			
		}
		else if(!Objects.isNull(statusGeral)) {
			return repository
					.findByStatusGeral(getStatusGeral.buscar(statusGeral.getId()), paginacao)
					.map(LoginDtoDetalhar::new);
					
		}
		else if (!Objects.isNull(cargoFuncionarioEnum)) {
			return repository
					.findByFuncionario_CargoFuncionario(getCargoFuncionario.buscar(cargoFuncionarioEnum.getId()), paginacao)
					.map(LoginDtoDetalhar::new);
		}
		
		return repository.findByStatusGeralNot(getStatusGeral.buscar(StatusGeralEnum.DESATIVADO.getId()),paginacao)
				.map(LoginDtoDetalhar::new);
	}

}

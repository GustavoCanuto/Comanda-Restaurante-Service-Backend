package com.projetoweb4.comandaRestaurante.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.projetoweb4.comandaRestaurante.dto.login.DadosAutenticacao;
import com.projetoweb4.comandaRestaurante.dto.login.DadosTokenJWT;
import com.projetoweb4.comandaRestaurante.dto.login.LoginDtoCadastrar;
import com.projetoweb4.comandaRestaurante.dto.login.LoginDtoDetalhar;
import com.projetoweb4.comandaRestaurante.dto.login.LoginTrocarSenhaDtoAtualizar;
import com.projetoweb4.comandaRestaurante.entity.Login;
import com.projetoweb4.comandaRestaurante.enumeration.CargoFuncionarioEnum;
import com.projetoweb4.comandaRestaurante.enumeration.StatusGeralEnum;
import com.projetoweb4.comandaRestaurante.service.LoginService;
import com.projetoweb4.comandaRestaurante.service.recurso.TokenService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("login")
public class LoginController {

	@Autowired
	private LoginService service;
	
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PreAuthorize("hasRole('GERENTE')")
	@PostMapping
	@Transactional
	public ResponseEntity<LoginDtoDetalhar> cadastrar(@RequestBody @Valid LoginDtoCadastrar dados, 																						
			UriComponentsBuilder uriBuilder) throws IOException {
		
		var entidade = service.cadastrar(dados);

		var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(entidade.id()).toUri();

		return ResponseEntity.created(uri).body(entidade);
	}

    @PreAuthorize("hasRole('GERENTE')")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		
		service.deletar(id);

		return ResponseEntity.noContent().build();
	}

    @PreAuthorize("hasRole('GERENTE')")
	@GetMapping
	public ResponseEntity<Page<LoginDtoDetalhar>> listar(@PageableDefault(size = 10) Pageable paginacao) {

		return ResponseEntity.ok(service.listarTodos(paginacao));
	}
    
    @PreAuthorize("hasRole('GERENTE')")
	@GetMapping("/status")
	public ResponseEntity<Page<LoginDtoDetalhar>> listarPorStatus(
			@RequestParam(required = false) StatusGeralEnum statusGeral,
			@RequestParam(required = false) CargoFuncionarioEnum cargoFuncionario,
			@PageableDefault(size = 10) Pageable paginacao) {

		return ResponseEntity.ok(service.listarTodosPorStatus(paginacao, statusGeral, cargoFuncionario));
	}

    @PreAuthorize("hasRole('GERENTE')")
	@GetMapping("/{id}")
	public ResponseEntity<LoginDtoDetalhar> detalhar(@PathVariable Long id) {

		return ResponseEntity.ok(service.buscarPorId(id));
	}

    @PostMapping("/autenticar")
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login().toUpperCase(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Login) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
    
	@PutMapping
	@Transactional
	public ResponseEntity<LoginDtoDetalhar> atualizarSenha(@RequestBody LoginTrocarSenhaDtoAtualizar dados) {

		return ResponseEntity.ok(service.atualizarSenha(dados));
	}
	

}

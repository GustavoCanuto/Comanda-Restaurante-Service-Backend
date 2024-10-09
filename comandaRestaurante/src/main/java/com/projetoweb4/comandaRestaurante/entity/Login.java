package com.projetoweb4.comandaRestaurante.entity;

import com.projetoweb4.comandaRestaurante.dto.login.LoginDtoCadastrar;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "tb_login")
@Entity(name = "Login")
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String email;
	private String senha;
	
	@ManyToOne
    @JoinColumn(name = "fk_funcionario")
    private Funcionario funcionario;

	public Login(Long id, String email, String senha, Funcionario funcionario) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.funcionario = funcionario;
	}

	public Login() {
		super();
	}

	public Login(LoginDtoCadastrar dados, Funcionario funcionario) {
		this.email = dados.email();
		this.senha = dados.senha();
		this.funcionario = funcionario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
}

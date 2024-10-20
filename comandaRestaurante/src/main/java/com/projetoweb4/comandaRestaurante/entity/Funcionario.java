package com.projetoweb4.comandaRestaurante.entity;

import com.projetoweb4.comandaRestaurante.dto.funcionario.FuncionarioDtoCadastrar;
import com.projetoweb4.comandaRestaurante.entity.domain.CargoFuncionario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "tb_funcionario")
@Entity(name = "Funcionario")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String cpf;
	
	@ManyToOne
    @JoinColumn(name = "fk_cargo_funcionario")
    private CargoFuncionario cargoFuncionario;

	public Funcionario(Long id, String nome, String cpf, CargoFuncionario cargoFuncionario) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.cargoFuncionario = cargoFuncionario;
	}

	public Funcionario() {
		super();
	}

	public Funcionario(FuncionarioDtoCadastrar dados, CargoFuncionario cargoFuncionario) {
		this.nome = dados.nome();
		this.cpf = dados.cpf();
		this.cargoFuncionario = cargoFuncionario;
	}

	public void atualizarInformacoes(FuncionarioDtoCadastrar dados, CargoFuncionario cargoFuncionario) {

		if ( dados.nome() != null && !dados.nome().isBlank()) {
			this.nome = dados.nome();
		}
		
		if (dados.cpf() != null) {
			this.cpf = dados.cpf();
		}
		
		if (cargoFuncionario != null) {
			this.cargoFuncionario = cargoFuncionario;
		}
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public CargoFuncionario getCargoFuncionario() {
		return cargoFuncionario;
	}

	public void setCargoFuncionario(CargoFuncionario cargoFuncionario) {
		this.cargoFuncionario = cargoFuncionario;
	}

}

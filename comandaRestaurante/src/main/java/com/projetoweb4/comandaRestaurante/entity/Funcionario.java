package com.projetoweb4.comandaRestaurante.entity;

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
	
	private String descricao;
	
	@ManyToOne
    @JoinColumn(name = "fk_cargo_funcionario")
    private CargoFuncionario cargoFuncionario;

	public Funcionario(Long id, String descricao, CargoFuncionario cargoFuncionario) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.cargoFuncionario = cargoFuncionario;
	}

	public Funcionario() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public CargoFuncionario getCargoFuncionario() {
		return cargoFuncionario;
	}

	public void setCargoFuncionario(CargoFuncionario cargoFuncionario) {
		this.cargoFuncionario = cargoFuncionario;
	}
	
}

package com.projetoweb4.comandaRestaurante.entity.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "tb_dom_cargo_funcionario")
@Entity(name = "CargoFuncionario")
public class CargoFuncionario {

	@Id
	private Short id;
	
	private String cargo;
	
	private String descricao;
	
	public CargoFuncionario(Short id, String cargo, String descricao) {
		this.id = id;
		this.cargo = cargo;
		this.descricao = descricao;
	}

	public CargoFuncionario() {
	}


	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}

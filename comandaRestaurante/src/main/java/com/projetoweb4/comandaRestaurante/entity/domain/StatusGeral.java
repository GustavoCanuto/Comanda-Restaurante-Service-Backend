package com.projetoweb4.comandaRestaurante.entity.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "tb_dom_status_geral")
@Entity(name = "StatusGeral")
public class StatusGeral {

	@Id
	private Short id;
	
	private char status;
	
	private String descricao;

	public StatusGeral(Short id, char status, String descricao) {
		super();
		this.id = id;
		this.status = status;
		this.descricao = descricao;
	}

	public StatusGeral() {
		super();
	}

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

package com.projetoweb4.comandaRestaurante.entity.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "tb_dom_tipo_produto")
@Entity(name = "TipoProduto")
public class TipoProduto {

	@Id
	private Short id;
	
	private String nome;

	public TipoProduto(Short id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public TipoProduto() {
	}

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}

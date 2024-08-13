package com.projetoweb4.comandaRestaurante.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "tb_prato")
@Entity(name = "Prato")
public class Prato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String prato;
	private String quantidade;
	private Double observacoes;
	
	@ManyToOne
    @JoinColumn(name = "fk_pedido")
    private Pedido pedido;

	
	public Prato(String prato, String quantidade, Double observacoes, Prato categoria, Pedido pedido) {
		super();
		this.prato = prato;
		this.quantidade = quantidade;
		this.observacoes = observacoes;
		this.pedido = pedido;
	}

	public Prato() {

	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrato() {
		return prato;
	}

	public void setPrato(String prato) {
		this.prato = prato;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public Double getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(Double observacoes) {
		this.observacoes = observacoes;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}

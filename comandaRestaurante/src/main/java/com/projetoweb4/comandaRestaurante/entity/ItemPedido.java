package com.projetoweb4.comandaRestaurante.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "tb_item_pedido")
@Entity(name = "ItemPedido")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String quantidade;
	private String observacoes;
	
	@ManyToOne
    @JoinColumn(name = "fk_pedido")
	@JsonBackReference  // Controle de serialização para evitar recursão
    private Pedido pedido;
	
	@ManyToOne
    @JoinColumn(name = "fk_produto")
    private Produto produto;

	public ItemPedido() {
	}

	public ItemPedido(Long id, String quantidade, String observacoes, Pedido pedido, Produto produto) {
		this.id = id;
		this.quantidade = quantidade;
		this.observacoes = observacoes;
		this.pedido = pedido;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
}

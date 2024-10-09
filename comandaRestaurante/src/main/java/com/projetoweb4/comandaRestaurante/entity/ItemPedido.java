package com.projetoweb4.comandaRestaurante.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name = "tb_item_pedido")
@Entity(name = "ItemPedido")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String observacao;
	
	@ManyToOne
    @JoinColumn(name = "fk_pedido")
	@JsonBackReference  // Controle de serialização para evitar recursão
    private Pedido pedido;
	
	@ManyToOne
    @JoinColumn(name = "fk_produto")
    private Produto produto;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_controle_status_item_pedido")
    private ControleStatusItemPedido controleStatusItemPedido;

	public ItemPedido(Long id, String observacao, Pedido pedido, Produto produto,
			ControleStatusItemPedido controleStatusItemPedido) {
		super();
		this.id = id;
		this.observacao = observacao;
		this.pedido = pedido;
		this.produto = produto;
		this.controleStatusItemPedido = controleStatusItemPedido;
	}

	public ItemPedido() {
		super();
	}

	public ItemPedido( String observacao, Pedido pedido, Produto produto, ControleStatusItemPedido controleStatusItemPedido) {
		this.observacao = observacao;
		this.pedido = pedido;
		this.produto = produto;
		this.controleStatusItemPedido = controleStatusItemPedido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
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

	public ControleStatusItemPedido getControleStatusItemPedido() {
		return controleStatusItemPedido;
	}

	public void setControleStatusItemPedido(ControleStatusItemPedido controleStatusItemPedido) {
		this.controleStatusItemPedido = controleStatusItemPedido;
	}

}

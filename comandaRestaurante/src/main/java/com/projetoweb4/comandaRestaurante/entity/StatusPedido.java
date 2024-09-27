package com.projetoweb4.comandaRestaurante.entity;

import com.projetoweb4.comandaRestaurante.entity.domain.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "tb_status_pedido")
@Entity(name = "StatusPedido")
public class StatusPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	
	@ManyToOne
    @JoinColumn(name = "fk_pedido")
    private Pedido pedido;
	
	@ManyToOne
    @JoinColumn(name = "fk_status")
    private Status status;

	public StatusPedido(Long id, String descricao, Pedido pedido, Status status) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.pedido = pedido;
		this.status = status;
	}

	public StatusPedido() {
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

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}

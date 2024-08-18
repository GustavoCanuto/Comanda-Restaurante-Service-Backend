package com.projetoweb4.comandaRestaurante.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.projetoweb4.comandaRestaurante.dto.pedido.PedidoDtoCadastrar;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "tb_pedido")
@Entity(name = "Pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer mesa;
	private String comanda;
	@Column(name = "data_hora_pedido", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime dataHoraPedido;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<ItemPedido> itensPedido = new ArrayList<>();

	public Pedido(Long id, Integer mesa, String comanda, List<ItemPedido> itensPedido) {
		this.id = id;
		this.mesa = mesa;
		this.comanda = comanda;
		this.dataHoraPedido = LocalDateTime.now();
		this.itensPedido = itensPedido;
	}
	
	public Pedido(PedidoDtoCadastrar dados) {
		this.mesa = dados.mesa();
		this.comanda = dados.comanda();
		this.dataHoraPedido = LocalDateTime.now();
	}

	public Pedido() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMesa() {
		return mesa;
	}

	public void setMesa(Integer mesa) {
		this.mesa = mesa;
	}

	public String getComanda() {
		return comanda;
	}

	public void setComanda(String comanda) {
		this.comanda = comanda;
	}

	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}

	public LocalDateTime getDataHoraPedido() {
		return dataHoraPedido;
	}

	public void setDataHoraPedido(LocalDateTime dataHoraPedido) {
		this.dataHoraPedido = dataHoraPedido;
	}
	
}

package com.projetoweb4.comandaRestaurante.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	
	private String mesa;
	private String comanda;
	
	@OneToMany(mappedBy = "pedido")
	@JsonManagedReference // Controle de serialização para evitar recursão
	private List<ItemPedido> itensPedido = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMesa() {
		return mesa;
	}

	public void setMesa(String mesa) {
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

	public Pedido(Long id, String mesa, String comanda, List<ItemPedido> itensPedido) {
		this.id = id;
		this.mesa = mesa;
		this.comanda = comanda;
		this.itensPedido = itensPedido;
	}

	public Pedido() {
	}

	
}

package com.projetoweb4.comandaRestaurante.entity;

import java.time.LocalDateTime;

import com.projetoweb4.comandaRestaurante.entity.domain.StatusProcesso;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name = "tb_controle_status_item_pedido")
@Entity(name = "StatusPedido")
public class ControleStatusItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descricaoStatus;
	
	private LocalDateTime dataHoraIniciado;
	
	private LocalDateTime dataHoraPronto;
	
	private LocalDateTime dataHoraEntregue;

	@ManyToOne
    @JoinColumn(name = "fk_funcionario")
    private Funcionario funcionario;
	
	@ManyToOne
    @JoinColumn(name = "fk_status_processo")
    private StatusProcesso statusProcesso;
	
	@OneToOne(mappedBy = "controleStatusItemPedido", cascade = CascadeType.ALL)
    private ItemPedido itemPedido;

	public ControleStatusItemPedido(Long id, String descricaoStatus, LocalDateTime dataHoraIniciado,
			LocalDateTime dataHoraPronto, LocalDateTime dataHoraEntregue, Funcionario funcionario, StatusProcesso status,
			ItemPedido itemPedido) {
		super();
		this.id = id;
		this.descricaoStatus = descricaoStatus;
		this.dataHoraIniciado = dataHoraIniciado;
		this.dataHoraPronto = dataHoraPronto;
		this.dataHoraEntregue = dataHoraEntregue;
		this.funcionario = funcionario;
		this.statusProcesso = status;
		this.itemPedido = itemPedido;
	}

	public ControleStatusItemPedido(StatusProcesso status) {
		this.statusProcesso = status;
	}
	
	public ControleStatusItemPedido() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoStatus() {
		return descricaoStatus;
	}

	public void setDescricaoStatus(String descricaoStatus) {
		this.descricaoStatus = descricaoStatus;
	}

	public LocalDateTime getDataHoraIniciado() {
		return dataHoraIniciado;
	}

	public void setDataHoraIniciado(LocalDateTime dataHoraIniciado) {
		this.dataHoraIniciado = dataHoraIniciado;
	}

	public LocalDateTime getDataHoraPronto() {
		return dataHoraPronto;
	}

	public void setDataHoraPronto(LocalDateTime dataHoraPronto) {
		this.dataHoraPronto = dataHoraPronto;
	}

	public LocalDateTime getDataHoraEntregue() {
		return dataHoraEntregue;
	}

	public void setDataHoraEntregue(LocalDateTime dataHoraEntregue) {
		this.dataHoraEntregue = dataHoraEntregue;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public StatusProcesso getStatus() {
		return statusProcesso;
	}

	public void setStatus(StatusProcesso status) {
		this.statusProcesso = status;
	}

	public ItemPedido getItemPedido() {
		return itemPedido;
	}

	public void setItemPedido(ItemPedido itemPedido) {
		this.itemPedido = itemPedido;
	}

}

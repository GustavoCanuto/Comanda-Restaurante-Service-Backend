package com.projetoweb4.comandaRestaurante.entity;

import com.projetoweb4.comandaRestaurante.dto.produto.ProdutoDtoCadastrar;
import com.projetoweb4.comandaRestaurante.entity.domain.StatusGeral;
import com.projetoweb4.comandaRestaurante.entity.domain.TipoProduto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "tb_produto")
@Entity(name = "Produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String descricao;
	private Double preco;
	private String linkImagem;
	
	@ManyToOne
    @JoinColumn(name = "fk_tipo_produto")
    private TipoProduto tipoProduto;
	
	@ManyToOne
    @JoinColumn(name = "fk_status_geral")
    private StatusGeral statusGeral;

	public Produto() {	
	}
	
	public Produto(Long id, String nome, String descricao, Double preco, String linkImagem, TipoProduto tipoProduto, StatusGeral statusGeral) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.linkImagem = linkImagem;
		this.tipoProduto = tipoProduto;
		this.statusGeral = statusGeral;
	}

	public Produto(ProdutoDtoCadastrar dados, TipoProduto tipoProduto, String linkImagem, StatusGeral statusGeral) {
		this.nome = dados.nome();
		this.descricao = dados.descricao();
		this.preco = dados.preco();
		this.linkImagem = linkImagem;
		this.tipoProduto = tipoProduto;
		this.statusGeral = statusGeral;
	}

	public void atualizarInformacoes(ProdutoDtoCadastrar dados, TipoProduto tipoProduto, String linkImagem, StatusGeral statusGeral) {

		if (dados.nome() != null && !dados.nome().isBlank()) {
			this.nome = dados.nome();
		}
		
		if (dados.descricao() != null && !dados.descricao().isBlank()) {
			this.descricao = dados.descricao();
		}
		
		if (dados.preco() != null) {
			this.preco = dados.preco();
		}
		
		if (linkImagem != null && !linkImagem.isBlank()) {
			this.linkImagem = linkImagem;
		}
		
		if (tipoProduto != null) {
			this.tipoProduto = tipoProduto;
		}
		
		if (statusGeral != null) {
			this.statusGeral = statusGeral;
		}

	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getLinkImagem() {
		return linkImagem;
	}

	public void setLinkImagem(String linkImagem) {
		this.linkImagem = linkImagem;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public StatusGeral getStatusGeral() {
		return statusGeral;
	}

	public void setStatusGeral(StatusGeral statusGeral) {
		this.statusGeral = statusGeral;
	}

	
}

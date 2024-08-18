package com.projetoweb4.comandaRestaurante.entity;

import com.projetoweb4.comandaRestaurante.dto.produto.ProdutoDtoCadastrar;
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

	public Produto() {	
	}
	
	public Produto(Long id, String nome, String descricao, Double preco, String linkImagem, TipoProduto tipoProduto) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.linkImagem = linkImagem;
		this.tipoProduto = tipoProduto;
	}

	public Produto(ProdutoDtoCadastrar dados, TipoProduto tipoProduto, String linkImagem) {
		this.nome = dados.nome();
		this.descricao = dados.descricao();
		this.preco = dados.preco();
		this.linkImagem = linkImagem;
		this.tipoProduto = tipoProduto;
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

}

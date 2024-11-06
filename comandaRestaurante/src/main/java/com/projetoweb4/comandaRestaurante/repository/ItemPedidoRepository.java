package com.projetoweb4.comandaRestaurante.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoweb4.comandaRestaurante.entity.Funcionario;
import com.projetoweb4.comandaRestaurante.entity.ItemPedido;
import com.projetoweb4.comandaRestaurante.entity.domain.StatusProcesso;
import com.projetoweb4.comandaRestaurante.entity.domain.TipoProduto;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
	
	Page<ItemPedido> findByControleStatusItemPedido_StatusProcesso(StatusProcesso statusProcesso, Pageable paginacao);
	
	Page<ItemPedido> findByProduto_TipoProduto(TipoProduto tipoProduto, Pageable paginacao);
	
	Page<ItemPedido> findByControleStatusItemPedido_StatusProcessoAndProduto_TipoProduto(StatusProcesso statusProcesso, TipoProduto tipoProduto, Pageable paginacao);
	
	Page<ItemPedido> findByControleStatusItemPedido_StatusProcessoNot(StatusProcesso statusProcesso, Pageable paginacao);

	//Funcionario
	Page<ItemPedido> findByPedido_FuncionarioAndControleStatusItemPedido_StatusProcesso(Funcionario funcionario, StatusProcesso statusProcesso, Pageable paginacao);
	
	Page<ItemPedido> findByPedido_FuncionarioAndControleStatusItemPedido_StatusProcessoNot(Funcionario funcionario, StatusProcesso statusProcesso, Pageable paginacao);
	
	
}

package com.projetoweb4.comandaRestaurante.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoweb4.comandaRestaurante.entity.Pedido;
import com.projetoweb4.comandaRestaurante.entity.domain.StatusProcesso;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	Page<Pedido> findByItensPedido_ControleStatusItemPedido_StatusProcesso(StatusProcesso statusProcesso, Pageable paginacao);

}

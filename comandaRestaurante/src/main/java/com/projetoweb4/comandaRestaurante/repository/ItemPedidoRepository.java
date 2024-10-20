package com.projetoweb4.comandaRestaurante.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoweb4.comandaRestaurante.entity.ItemPedido;
import com.projetoweb4.comandaRestaurante.entity.domain.StatusProcesso;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
	
	Page<ItemPedido> findByControleStatusItemPedido_StatusProcesso(StatusProcesso statusProcesso, Pageable paginacao);

}

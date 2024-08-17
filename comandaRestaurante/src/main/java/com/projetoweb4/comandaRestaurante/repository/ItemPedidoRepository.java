package com.projetoweb4.comandaRestaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoweb4.comandaRestaurante.entity.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

}

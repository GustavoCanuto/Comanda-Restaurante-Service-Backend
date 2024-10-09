package com.projetoweb4.comandaRestaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoweb4.comandaRestaurante.entity.ControleStatusItemPedido;

public interface StatusPedidoRepository extends JpaRepository<ControleStatusItemPedido, Long> {

}

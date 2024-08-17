package com.projetoweb4.comandaRestaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoweb4.comandaRestaurante.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}

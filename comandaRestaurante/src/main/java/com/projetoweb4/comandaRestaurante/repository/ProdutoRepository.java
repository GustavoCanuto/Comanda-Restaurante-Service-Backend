package com.projetoweb4.comandaRestaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoweb4.comandaRestaurante.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
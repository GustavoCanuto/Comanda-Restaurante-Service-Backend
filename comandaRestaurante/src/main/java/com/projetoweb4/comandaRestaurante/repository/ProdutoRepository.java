package com.projetoweb4.comandaRestaurante.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoweb4.comandaRestaurante.entity.Produto;
import com.projetoweb4.comandaRestaurante.entity.domain.StatusGeral;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Page<Produto> findByStatusGeral(StatusGeral statusGeral, Pageable paginacao);
}

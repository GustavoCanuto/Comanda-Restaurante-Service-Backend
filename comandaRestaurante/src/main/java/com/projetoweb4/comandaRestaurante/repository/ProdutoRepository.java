package com.projetoweb4.comandaRestaurante.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoweb4.comandaRestaurante.entity.Produto;
import com.projetoweb4.comandaRestaurante.entity.domain.StatusGeral;
import com.projetoweb4.comandaRestaurante.entity.domain.TipoProduto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Page<Produto> findByStatusGeral(StatusGeral statusGeral, Pageable paginacao);
	
	Page<Produto> findByStatusGeralAndTipoProduto(StatusGeral statusGeral, TipoProduto tipoProduto, Pageable paginacao);
	
	Page<Produto> findByTipoProduto(TipoProduto tipoProduto, Pageable paginacao);
	
	Page<Produto> findByStatusGeralNot(StatusGeral statusGeral, Pageable paginacao);
}

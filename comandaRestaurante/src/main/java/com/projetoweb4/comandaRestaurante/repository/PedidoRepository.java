package com.projetoweb4.comandaRestaurante.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projetoweb4.comandaRestaurante.entity.Pedido;
import com.projetoweb4.comandaRestaurante.entity.domain.StatusProcesso;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	Page<Pedido> findByItensPedido_ControleStatusItemPedido_StatusProcesso(StatusProcesso statusProcesso, Pageable paginacao);
	
	Page<Pedido> findByItensPedido_ControleStatusItemPedido_StatusProcessoNot(StatusProcesso statusProcesso, Pageable paginacao);

	@Query("SELECT p FROM Pedido p " +
		       "JOIN p.itensPedido ip " +
		       "JOIN ip.controleStatusItemPedido csp " +
		       "WHERE p.id NOT IN (" +
		       "    SELECT p2.id FROM Pedido p2 " +
		       "    JOIN p2.itensPedido ip2 " +
		       "    JOIN ip2.controleStatusItemPedido csp2 " +
		       "    WHERE csp2.statusProcesso <> :statusCancelado " +
		       ") " +
		       "GROUP BY p.id")
	Page<Pedido> findAllPedidosComItensCancelados(@Param("statusCancelado") StatusProcesso statusCancelado, Pageable pageable);
}

package com.projetoweb4.comandaRestaurante.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projetoweb4.comandaRestaurante.dto.pedido.PedidoDtoCadastrar;
import com.projetoweb4.comandaRestaurante.dto.pedido.PedidoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.entity.ControleStatusItemPedido;
import com.projetoweb4.comandaRestaurante.entity.ItemPedido;
import com.projetoweb4.comandaRestaurante.entity.Pedido;
import com.projetoweb4.comandaRestaurante.entity.domain.StatusProcesso;
import com.projetoweb4.comandaRestaurante.enumeration.StatusProcessoEnum;
import com.projetoweb4.comandaRestaurante.repository.ItemPedidoRepository;
import com.projetoweb4.comandaRestaurante.repository.PedidoRepository;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarPedido;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarProduto;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarStatusProcesso;

@Service
public class PedidoService{

	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private BuscarProduto getProduto;

	@Autowired
	private BuscarStatusProcesso getStatusProcesso;
	
	@Autowired
	private BuscarPedido getPedido;
	
	public PedidoDtoDetalhar cadastrar(PedidoDtoCadastrar dados) {

		Pedido pedido = new Pedido(dados, null); 

		repository.save(pedido);

		List<ItemPedido> itensPedido = dados.itensPedido().stream()
			    .map(itemDto -> new ItemPedido(
			    		itemDto.observacoes(),
			    		pedido, 
			    		getProduto.buscar(itemDto.idProduto()),
			    		new ControleStatusItemPedido(getStatusProcesso.buscar(StatusProcessoEnum.A_FAZER.getId()))
			    		)
			    	)
			    .collect(Collectors.toList());
  
        itemPedidoRepository.saveAll(itensPedido);
           
        pedido.setItensPedido(itensPedido);
        
		return new PedidoDtoDetalhar(pedido);
	}


	public PedidoDtoDetalhar buscarPorId(Long id) {
		return new PedidoDtoDetalhar(repository.getReferenceById(id));
	}


	public Page<PedidoDtoDetalhar> listarTodos(Pageable paginacao, StatusProcessoEnum statusProcesso) {
		//ele vai fazer um findAll nos pedidos porém só vai retornar o pedido se algum itemPedido tiver o idstatusProcesso mandado
		if(!Objects.isNull(statusProcesso)) {
			
		StatusProcesso status = getStatusProcesso.buscar(statusProcesso.getId());
		
		return repository
				.findByItensPedido_ControleStatusItemPedido_StatusProcesso(status, paginacao)
				.map(PedidoDtoDetalhar::new);
				
		}

		return repository.findAll(paginacao).map(PedidoDtoDetalhar::new);
	}


	public void deletar(Long id) {
		//faz loop nos items e muda o status para cancelado
		Pedido pedido = getPedido.buscar(id);
		
		List<ItemPedido> itensPedido = pedido.getItensPedido().stream()
			    .peek(itemDto -> itemDto.setControleStatusItemPedido(
			        new ControleStatusItemPedido(getStatusProcesso.buscar(StatusProcessoEnum.CANCELADO.getId()))
			    ))
			    .collect(Collectors.toList());
  
        itemPedidoRepository.saveAll(itensPedido);
	}

	
	public PedidoDtoDetalhar atualizar(PedidoDtoCadastrar dados, Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	


}

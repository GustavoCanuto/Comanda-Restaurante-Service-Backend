package com.projetoweb4.comandaRestaurante.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projetoweb4.comandaRestaurante.dto.pedido.PedidoDtoCadastrar;
import com.projetoweb4.comandaRestaurante.dto.pedido.PedidoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.entity.ItemPedido;
import com.projetoweb4.comandaRestaurante.entity.Pedido;
import com.projetoweb4.comandaRestaurante.repository.ItemPedidoRepository;
import com.projetoweb4.comandaRestaurante.repository.PedidoRepository;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarProduto;

@Service
public class FuncionarioService implements CrudService<PedidoDtoDetalhar, PedidoDtoCadastrar, Long>{

	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private BuscarProduto getProduto;

	@Override
	public PedidoDtoDetalhar cadastrar(PedidoDtoCadastrar dados) {

		Pedido pedido = new Pedido(dados, null); 

		repository.save(pedido);

		List<ItemPedido> itensPedido = dados.itensPedido().stream()
			    .map(itemDto -> new ItemPedido(itemDto, pedido, getProduto.buscar(itemDto.idProduto())))
			    .collect(Collectors.toList());
  
        itemPedidoRepository.saveAll(itensPedido);
           
        pedido.setItensPedido(itensPedido);
        
		return new PedidoDtoDetalhar(pedido);
	}

	@Override
	public PedidoDtoDetalhar buscarPorId(Long id) {
		return new PedidoDtoDetalhar(repository.getReferenceById(id));
	}

	@Override
	public Page<PedidoDtoDetalhar> listarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(PedidoDtoDetalhar::new);
	}

	@Override
	public void deletar(Long id) {
		repository.deleteById(id);
	}

	@Override
	public PedidoDtoDetalhar atualizar(PedidoDtoCadastrar dados, Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	


}

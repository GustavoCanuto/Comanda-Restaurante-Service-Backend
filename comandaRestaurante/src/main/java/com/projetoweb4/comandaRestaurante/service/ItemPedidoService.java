package com.projetoweb4.comandaRestaurante.service;

import static java.util.Optional.ofNullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projetoweb4.comandaRestaurante.dto.itemPedido.ItemPedidoDtoCadastrar;
import com.projetoweb4.comandaRestaurante.dto.itemPedido.ItemPedidoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.entity.ItemPedido;
import com.projetoweb4.comandaRestaurante.entity.Pedido;
import com.projetoweb4.comandaRestaurante.entity.Produto;
import com.projetoweb4.comandaRestaurante.repository.ItemPedidoRepository;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarPedido;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarProduto;
import com.projetoweb4.comandaRestaurante.validacoes.ValidacaoException;

@Service
public class ItemPedidoService implements CrudService<ItemPedidoDtoDetalhar, ItemPedidoDtoCadastrar, Long>{

	@Autowired
	private ItemPedidoRepository repository;
	
	@Autowired
	private BuscarProduto getProduto;
	
	@Autowired
	private BuscarPedido getPedido;

	@Override
	public ItemPedidoDtoDetalhar cadastrar(ItemPedidoDtoCadastrar dados) {
	
		Produto produto = getProduto.buscar(dados.idProduto());
		
		Pedido pedido = ofNullable(getPedido.buscar(dados.idPedido()))
			    .orElseThrow(() -> new ValidacaoException("É obrigatório informar o id do Pedido"));
		
		ItemPedido itemPedido = new ItemPedido(dados, pedido, produto); 

		repository.save(itemPedido);

		return new ItemPedidoDtoDetalhar(itemPedido, true);
	}

	@Override
	public ItemPedidoDtoDetalhar buscarPorId(Long id) {
		return new ItemPedidoDtoDetalhar(repository.getReferenceById(id), true);
	}

	@Override
	public Page<ItemPedidoDtoDetalhar> listarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(item -> new ItemPedidoDtoDetalhar(item, true));
	}

	@Override
	public void deletar(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public ItemPedidoDtoDetalhar atualizar(ItemPedidoDtoCadastrar dados, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}

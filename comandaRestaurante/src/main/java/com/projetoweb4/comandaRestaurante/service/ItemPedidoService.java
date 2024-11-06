package com.projetoweb4.comandaRestaurante.service;

import static java.util.Optional.ofNullable;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projetoweb4.comandaRestaurante.dto.itemPedido.ItemPedidoDtoCadastrar;
import com.projetoweb4.comandaRestaurante.dto.itemPedido.ItemPedidoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.entity.ControleStatusItemPedido;
import com.projetoweb4.comandaRestaurante.entity.Funcionario;
import com.projetoweb4.comandaRestaurante.entity.ItemPedido;
import com.projetoweb4.comandaRestaurante.entity.Pedido;
import com.projetoweb4.comandaRestaurante.entity.Produto;
import com.projetoweb4.comandaRestaurante.entity.domain.StatusProcesso;
import com.projetoweb4.comandaRestaurante.entity.domain.TipoProduto;
import com.projetoweb4.comandaRestaurante.enumeration.StatusProcessoEnum;
import com.projetoweb4.comandaRestaurante.enumeration.TipoProdutoEnum;
import com.projetoweb4.comandaRestaurante.repository.ItemPedidoRepository;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarItemPedido;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarPedido;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarProduto;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarStatusProcesso;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarTipoProduto;
import com.projetoweb4.comandaRestaurante.service.recurso.TokenService;
import com.projetoweb4.comandaRestaurante.validacoes.ValidacaoException;

@Service
public class ItemPedidoService implements CrudService<ItemPedidoDtoDetalhar, ItemPedidoDtoCadastrar, Long>{

	@Autowired
	private ItemPedidoRepository repository;
	
	@Autowired
	private BuscarProduto getProduto;
	
	@Autowired
	private BuscarTipoProduto getTipoProduto;
	
	@Autowired
	private BuscarPedido getPedido;
	
	@Autowired
	private BuscarItemPedido getItemPedido;
	
	@Autowired
	private BuscarStatusProcesso getStatusProcesso;
	
	@Autowired
	private TokenService tokenService;

	public ItemPedidoDtoDetalhar cadastrar(ItemPedidoDtoCadastrar dados) {
	
		Produto produto = getProduto.buscar(dados.idProduto());
		
		Pedido pedido = ofNullable(getPedido.buscar(dados.idPedido()))
			    .orElseThrow(() -> new ValidacaoException("É obrigatório informar o id do Pedido"));
		
		ItemPedido itemPedido = new ItemPedido(dados.observacoes(), pedido, produto,new ControleStatusItemPedido(getStatusProcesso.buscar(StatusProcessoEnum.A_FAZER.getId()))); 

		repository.save(itemPedido);

		return new ItemPedidoDtoDetalhar(itemPedido, true);
	}

	public ItemPedidoDtoDetalhar atualizar(ItemPedidoDtoCadastrar dados, Long id) {
		
		ItemPedido itemPedido = getItemPedido.buscar(id);
		Produto produto = getProduto.buscar(dados.idProduto());
		Pedido pedido = getPedido.buscar(dados.idPedido());
		
		itemPedido.atualizarInformacoes(dados.observacoes(), pedido, produto);
		
		repository.save(itemPedido);
				
		return new ItemPedidoDtoDetalhar(itemPedido, true);
	}
	
	public ItemPedidoDtoDetalhar buscarPorId(Long id) {
		return new ItemPedidoDtoDetalhar(repository.getReferenceById(id), true);
	}


	@Override
	public Page<ItemPedidoDtoDetalhar> listarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(item -> new ItemPedidoDtoDetalhar(item, true));
	}

	
	public Page<ItemPedidoDtoDetalhar> listarTodosPorStatus(
			Pageable paginacao, StatusProcessoEnum statusProcesso, TipoProdutoEnum tipoProdutoEnum) {
		
		if(!Objects.isNull(statusProcesso) && !Objects.isNull(tipoProdutoEnum) ) {
			
			StatusProcesso status = getStatusProcesso.buscar(statusProcesso.getId());
			TipoProduto tipoProduto =  getTipoProduto.buscar(tipoProdutoEnum.getId());
			
			return repository
					.findByControleStatusItemPedido_StatusProcessoAndProduto_TipoProduto(status, tipoProduto, paginacao)
					.map(item -> new ItemPedidoDtoDetalhar(item, true));
					
		}
		else if(!Objects.isNull(statusProcesso)) {
			
		StatusProcesso status = getStatusProcesso.buscar(statusProcesso.getId());
		
		return repository
				.findByControleStatusItemPedido_StatusProcesso(status, paginacao)
				.map(item -> new ItemPedidoDtoDetalhar(item, true));
				
		}else if (!Objects.isNull(tipoProdutoEnum)) {
			
		TipoProduto tipoProduto =  getTipoProduto.buscar(tipoProdutoEnum.getId());
			
		return repository
				.findByProduto_TipoProduto(tipoProduto, paginacao)
				.map(item -> new ItemPedidoDtoDetalhar(item, true));
		}
		
		StatusProcesso status = getStatusProcesso.buscar(StatusProcessoEnum.CANCELADO.getId());
		
		return repository.findByControleStatusItemPedido_StatusProcessoNot(status, paginacao)
				.map(item -> new ItemPedidoDtoDetalhar(item, true));
	}
	
	public Page<ItemPedidoDtoDetalhar> listarTodosPorStatusMeusItensPedidos(
			Pageable paginacao, StatusProcessoEnum statusProcesso) {

		Funcionario funcionario = tokenService.getFuncionarioAutenticado();
		
		if(!Objects.isNull(statusProcesso)) {
			
		StatusProcesso status = getStatusProcesso.buscar(statusProcesso.getId());
		
		return repository
				.findByPedido_FuncionarioAndControleStatusItemPedido_StatusProcesso(funcionario, status, paginacao)
				.map(item -> new ItemPedidoDtoDetalhar(item, true));
				
		}
		
		StatusProcesso status = getStatusProcesso.buscar(StatusProcessoEnum.CANCELADO.getId());
		
		return repository.findByPedido_FuncionarioAndControleStatusItemPedido_StatusProcessoNot(funcionario, status, paginacao)
				.map(item -> new ItemPedidoDtoDetalhar(item, true));
	}



	public void deletar(Long id) {
		
		ItemPedido itemPedido = getItemPedido.buscar(id);
		itemPedido.getControleStatusItemPedido().setStatus(getStatusProcesso.buscar(StatusProcessoEnum.CANCELADO.getId()));
		
		repository.save(itemPedido);
		
	}

}

package com.projetoweb4.comandaRestaurante.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projetoweb4.comandaRestaurante.dto.itemPedido.ItemPedidoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.dto.statusItemPedido.ControleStatusItemPedidoDtoUpdate;
import com.projetoweb4.comandaRestaurante.entity.ControleStatusItemPedido;
import com.projetoweb4.comandaRestaurante.entity.Funcionario;
import com.projetoweb4.comandaRestaurante.entity.ItemPedido;
import com.projetoweb4.comandaRestaurante.enumeration.StatusProcessoEnum;
import com.projetoweb4.comandaRestaurante.repository.StatusPedidoRepository;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarItemPedido;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarStatusProcesso;
import com.projetoweb4.comandaRestaurante.service.recurso.TokenService;
import com.projetoweb4.comandaRestaurante.validacoes.ValidacaoException;

@Service
public class ControleStatusItemPedidoService {

	@Autowired
	private StatusPedidoRepository repository;
	
	@Autowired
	private BuscarItemPedido getItemPedido;
	
	@Autowired
	private BuscarStatusProcesso getStatusProcesso;
	
	@Autowired
	private TokenService tokenService;

	public Page<ItemPedidoDtoDetalhar> atualizar(
			List<Long> idsItemPedido, 
			ControleStatusItemPedidoDtoUpdate dados,
			Pageable paginacao) {
		
		
		//criar metodo chamado valida itensPedido
		// se o usuario for cozinheiro e anymatch com statusPedido Enviado == entregue erro, somente garçon ou gerente pode mudar o status para entregue
		// se o usuario for garçon e anymatch com statusPedido Enviado == fazendo ou pronto erro, somente cozinheir ou gerente pode mudar o status para fazendo ou pronto
		
		
		//só pode ir para FAZENDO se o staus atual for A_FAZER
		//SÓ PODE ir para o PRONTO se o status atual for FAZENDO 
		//SÓ PODE ir para o Entregue se o status atual for Pronto
		
		// pega funcionario a partir do token jwt getFuncionarioAutenticado()
		Funcionario funcionario = tokenService.getFuncionarioAutenticado();
		
		//pegar cargo a partir do token jwt
		 String cargo = funcionario.getCargoFuncionario().getCargo();
		 
		 //StatusGeral statusGeral 
		
		 // Coleta os itens pedidos a partir dos IDs
	    List<ItemPedido> itensPedido = idsItemPedido.stream()
	        .map(item -> getItemPedido.buscar(item))
	        .collect(Collectors.toList());  // Coleta em uma lista

	    // Atualiza o controle de status para cada item pedido
	    for (ItemPedido item : itensPedido) {
	        ControleStatusItemPedido controleStatus = item.getControleStatusItemPedido();
	        validarPermissoes(funcionario, controleStatus, dados.status()); // Verifica permissões
	        // Atualiza campos de acordo com o status desejado
	        atualizarControleStatus(controleStatus, funcionario, dados, cargo);
	        
	        // Salva o controle de status no repositório
	        repository.save(controleStatus);
	    }

	    // Mapeia os itens atualizados para DTOs
	    List<ItemPedidoDtoDetalhar> detalhes = itensPedido.stream()
	            .map(item -> new ItemPedidoDtoDetalhar(item, false))  // Correção na chamada do construtor
	            .collect(Collectors.toList());

	    // Retorna a lista de DTOs como uma página
	    return new PageImpl<>(detalhes, paginacao, detalhes.size());
	}

	   // Método para obter o funcionário autenticado a partir do token JWT

    
    private void atualizarControleStatus(ControleStatusItemPedido controleStatus,
    		Funcionario funcionario, 
    		ControleStatusItemPedidoDtoUpdate dados, 
    		String cargo) {
    	
        controleStatus.setDescricaoStatus(dados.descricao());
        controleStatus.setFuncionario(funcionario);
        controleStatus.setStatus(getStatusProcesso.buscar(dados.status().getId()));
        
        if (dados.status() == StatusProcessoEnum.FAZENDO) {
            controleStatus.setDataHoraIniciado(LocalDateTime.now());
            controleStatus.setDataHoraPronto(null);
            controleStatus.setDataHoraEntregue(null);
        } else if (dados.status() == StatusProcessoEnum.PRONTO) {
            controleStatus.setDataHoraPronto(LocalDateTime.now());
            controleStatus.setDataHoraEntregue(null);
        } else if (dados.status() == StatusProcessoEnum.ENTREGUE) {
            controleStatus.setDataHoraEntregue(LocalDateTime.now());
        }else if (dados.status() == StatusProcessoEnum.A_FAZER) {
            controleStatus.setDataHoraIniciado(null);
            controleStatus.setDataHoraPronto(null);
            controleStatus.setDataHoraEntregue(null);
        } 
    }
    
    private void validarPermissoes(Funcionario funcionario, ControleStatusItemPedido controleStatus, StatusProcessoEnum novoStatus) {
        String cargo = funcionario.getCargoFuncionario().getCargo().toUpperCase();
        Short statusAtual = controleStatus.getStatus().getId();
        
        // Regras de transição de status baseadas no cargo
        if ("COZINHEIRO".equals(cargo)) {
            if (novoStatus == StatusProcessoEnum.ENTREGUE) {
                throw new ValidacaoException("Cozinheiros não têm permissão para marcar pedidos como ENTREGUE.");
            }
        } else if ("GARÇON".equals(cargo)) {
            if (novoStatus == StatusProcessoEnum.FAZENDO || novoStatus == StatusProcessoEnum.PRONTO) {
                throw new ValidacaoException("Garçons não têm permissão para marcar pedidos como FAZENDO ou PRONTO.");
            }
        } 
        

        // Regras de sequência de status
        if (!"GERENTE".equals(cargo)) {
	        if (novoStatus == StatusProcessoEnum.FAZENDO && statusAtual != StatusProcessoEnum.A_FAZER.getId()) {
	            throw new ValidacaoException("O status só pode mudar para FAZENDO se o status atual for A FAZER.");
	        }
	        if (novoStatus == StatusProcessoEnum.PRONTO && statusAtual != StatusProcessoEnum.FAZENDO.getId()) {
	            throw new ValidacaoException("O status só pode mudar para PRONTO se o status atual for FAZENDO.");
	        }
	        if (novoStatus == StatusProcessoEnum.ENTREGUE && statusAtual != StatusProcessoEnum.PRONTO.getId()) {
	            throw new ValidacaoException("O status só pode mudar para ENTREGUE se o status atual for PRONTO.");
	        }
	        if (novoStatus == StatusProcessoEnum.A_FAZER) {
	            throw new ValidacaoException("Apenas gerentes podem definir status como A FAZER.");
	        }
        }

    }
}

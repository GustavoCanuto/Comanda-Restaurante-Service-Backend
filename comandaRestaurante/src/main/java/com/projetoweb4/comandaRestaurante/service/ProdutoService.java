package com.projetoweb4.comandaRestaurante.service;

import java.io.IOException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projetoweb4.comandaRestaurante.dto.produto.ProdutoDtoCadastrar;
import com.projetoweb4.comandaRestaurante.dto.produto.ProdutoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.entity.Produto;
import com.projetoweb4.comandaRestaurante.entity.domain.StatusGeral;
import com.projetoweb4.comandaRestaurante.entity.domain.TipoProduto;
import com.projetoweb4.comandaRestaurante.enumeration.StatusGeralEnum;
import com.projetoweb4.comandaRestaurante.enumeration.TipoProdutoEnum;
import com.projetoweb4.comandaRestaurante.repository.ProdutoRepository;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarProduto;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarStatusGeral;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarTipoProduto;

@Service
public class ProdutoService implements CrudService<ProdutoDtoDetalhar, ProdutoDtoCadastrar, Long>{

	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private BuscarTipoProduto getTipoProduto;
	
	@Autowired
	private BuscarStatusGeral getStatusGeral;
	
	@Autowired
	private BuscarProduto getProduto;
	
	@Autowired
	private UploadService uploadService;
	
	public ProdutoDtoDetalhar cadastrar(ProdutoDtoCadastrar dados) throws IOException {
		TipoProduto tipoProduto = getTipoProduto.buscar(dados.tipoProduto().getId());
		
		StatusGeral statusGeral = getStatusGeral.buscar(StatusGeralEnum.ATIVO.getId());

		String imagemUrl = uploadService.uploadImage(dados.imagem(), "produto_", "imagesProduto");
		
		Produto produto = new Produto(dados, tipoProduto, imagemUrl, statusGeral); 

		repository.save(produto);

		return new ProdutoDtoDetalhar(produto);
	}


	public ProdutoDtoDetalhar atualizar(ProdutoDtoCadastrar dados, Long id) throws IOException {
		Produto produto = getProduto.buscar(id);
		
		TipoProduto tipoProduto = getTipoProduto.buscar(dados.tipoProduto().getId());
		
		StatusGeral statusGeral = getStatusGeral.buscar(StatusGeralEnum.ATIVO.getId());

		String imagemUrl = uploadService.uploadImage(dados.imagem(), "produto_", "imagesProduto");
		
		produto.atualizarInformacoes(dados, tipoProduto, imagemUrl, statusGeral); 

		repository.save(produto);

		return new ProdutoDtoDetalhar(produto);
	}
	public ProdutoDtoDetalhar buscarPorId(Long id) {
		return new ProdutoDtoDetalhar(repository.getReferenceById(id));
	}

	public Page<ProdutoDtoDetalhar> listarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(ProdutoDtoDetalhar::new);
	}

	public Page<ProdutoDtoDetalhar> listarTodosPorStatus(
			Pageable paginacao, StatusGeralEnum statusGeral, TipoProdutoEnum tipoProdutoEnum) {
		
		if(!Objects.isNull(statusGeral) && !Objects.isNull(tipoProdutoEnum) ) {
			
			StatusGeral status = getStatusGeral.buscar(statusGeral.getId());
			TipoProduto tipoProduto = getTipoProduto.buscar(tipoProdutoEnum.getId());
			
			return repository
					.findByStatusGeralAndTipoProduto(status, tipoProduto, paginacao)
					.map(ProdutoDtoDetalhar::new);
			
		}
		else if(!Objects.isNull(statusGeral)) {
			return repository
					.findByStatusGeral(getStatusGeral.buscar(statusGeral.getId()), paginacao)
					.map(ProdutoDtoDetalhar::new);
					
		}
		else if (!Objects.isNull(tipoProdutoEnum)) {
			return repository
					.findByTipoProduto(getTipoProduto.buscar(tipoProdutoEnum.getId()), paginacao)
					.map(ProdutoDtoDetalhar::new);
		}
		
		return repository.findByStatusGeralNot(getStatusGeral.buscar(StatusGeralEnum.DESATIVADO.getId()),paginacao)
				.map(ProdutoDtoDetalhar::new);
	}

	
	public void deletar(Long id) {
		//implementar delete de imagem por link
		Produto produto = getProduto.buscar(id);
		produto.setStatusGeral(getStatusGeral.buscar(StatusGeralEnum.DESATIVADO.getId()));
		
		repository.save(produto);
	}

}

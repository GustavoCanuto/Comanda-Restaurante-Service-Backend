package com.projetoweb4.comandaRestaurante.service;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.projetoweb4.comandaRestaurante.dto.produto.ProdutoDtoCadastrar;
import com.projetoweb4.comandaRestaurante.dto.produto.ProdutoDtoDetalhar;
import com.projetoweb4.comandaRestaurante.entity.Produto;
import com.projetoweb4.comandaRestaurante.entity.domain.TipoProduto;
import com.projetoweb4.comandaRestaurante.infra.FtpProperties;
import com.projetoweb4.comandaRestaurante.repository.ProdutoRepository;
import com.projetoweb4.comandaRestaurante.service.buscador.BuscarTipoProduto;
import com.projetoweb4.comandaRestaurante.service.recurso.FtpService;


@Service
public class ProdutoService{

	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private BuscarTipoProduto getTipoProduto;
	
	@Autowired
	private FtpService ftpService;
	
	@Autowired
	private FtpProperties ftpProperties;
	
	//migrar fariaveis para o proprietes
	private final String FTP_SERVER = "012024devweb3g8.ok.etc.br";
	private final int FTP_PORT = 21;
	private final String FTP_USER = "w012024devweb3g81";
	private final String FTP_PASS = "devWeb319@2024";
	

	public ProdutoDtoDetalhar cadastrar(ProdutoDtoCadastrar dados,  MultipartFile imagem) throws IOException {
		TipoProduto tipoProduto = getTipoProduto.buscar(dados.tipoProduto().getId());

		String imagemUrl = "";
	    
	    
	    if (imagem != null && !imagem.isEmpty()) {
	        String originalFileName = imagem.getOriginalFilename();
	        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

	        String hash = generateMD5(originalFileName);
	     // Gerar um token único usando UUID
	        String token = UUID.randomUUID().toString();
	        
	        String fileName = "produto_" + hash + token + fileExtension;
	        InputStream inputStream = imagem.getInputStream();
	        String filePath = "public_html/imagesProduto/" + fileName;
	        String imagePath = "/imagesProduto/" + fileName;

	        try {
	            ftpService.uploadFile(FTP_SERVER, FTP_PORT, FTP_USER, FTP_PASS, filePath, inputStream);
	            imagemUrl = "https://" + FTP_SERVER + imagePath;
	        } catch (IOException e) {
	            throw new IOException("Failed to upload file: " + e.getMessage(), e);
	        }
	    }
		
		//resto codigo
		Produto produto = new Produto(dados, tipoProduto, imagemUrl); 

		repository.save(produto);

		return new ProdutoDtoDetalhar(produto);
	}

	private String generateMD5(String input) {
	    try {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        byte[] messageDigest = md.digest(input.getBytes());
	        StringBuilder hexString = new StringBuilder();
	        for (byte b : messageDigest) {
	            String hex = Integer.toHexString(0xff & b);
	            if (hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }
	        return hexString.toString();
	    } catch (NoSuchAlgorithmException e) {
	        throw new RuntimeException(e);
	    }
	}

	public ProdutoDtoDetalhar buscarPorId(Long id) {
		return new ProdutoDtoDetalhar(repository.getReferenceById(id));
	}

	public Page<ProdutoDtoDetalhar> listarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(ProdutoDtoDetalhar::new);
	}

	public void deletar(Long id) {
		repository.deleteById(id);
		
	}

	public ProdutoDtoDetalhar atualizar(ProdutoDtoCadastrar dados, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}

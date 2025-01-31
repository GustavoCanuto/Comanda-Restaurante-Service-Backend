package com.projetoweb4.comandaRestaurante.service;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<T, G, ID> {

	T cadastrar(G dados) throws IOException ;
	
	T buscarPorId(ID id);

	Page<T> listarTodos(Pageable paginacao);
	
	void deletar(ID id);
	
	T atualizar(G dados, ID id) throws IOException;

}

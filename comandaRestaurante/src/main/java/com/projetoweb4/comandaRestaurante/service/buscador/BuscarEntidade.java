package com.projetoweb4.comandaRestaurante.service.buscador;

public interface BuscarEntidade<T, G> {
	
	T buscar(G id);

}
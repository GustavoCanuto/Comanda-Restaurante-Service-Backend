package com.projetoweb4.comandaRestaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoweb4.comandaRestaurante.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	boolean existsByCpf(String cpf);

}

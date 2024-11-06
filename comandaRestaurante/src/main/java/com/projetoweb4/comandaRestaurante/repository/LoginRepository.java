package com.projetoweb4.comandaRestaurante.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.projetoweb4.comandaRestaurante.entity.Funcionario;
import com.projetoweb4.comandaRestaurante.entity.Login;
import com.projetoweb4.comandaRestaurante.entity.domain.CargoFuncionario;
import com.projetoweb4.comandaRestaurante.entity.domain.StatusGeral;

public interface LoginRepository extends JpaRepository<Login, Long> {

	UserDetails findByEmailAndStatusGeral_Id(String login, Short id);
	  
	boolean existsByEmail(String email);
	
	Login findByFuncionario(Funcionario funcionario);
	
	Page<Login> findByStatusGeral(StatusGeral statusGeral, Pageable paginacao);
	
	Page<Login> findByStatusGeralAndFuncionario_CargoFuncionario(StatusGeral statusGeral, CargoFuncionario cargoFuncionario, Pageable paginacao);
	
	Page<Login> findByFuncionario_CargoFuncionario(CargoFuncionario cargoFuncionario, Pageable paginacao);
	
	Page<Login> findByStatusGeralNot(StatusGeral statusGeral, Pageable paginacao);

}

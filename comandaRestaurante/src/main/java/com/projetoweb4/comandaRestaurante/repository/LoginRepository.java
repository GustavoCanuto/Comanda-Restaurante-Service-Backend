package com.projetoweb4.comandaRestaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.projetoweb4.comandaRestaurante.entity.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {

	UserDetails findByEmailAndStatusGeral_Id(String login, Short id);
	  
	boolean existsByEmail(String email);

}

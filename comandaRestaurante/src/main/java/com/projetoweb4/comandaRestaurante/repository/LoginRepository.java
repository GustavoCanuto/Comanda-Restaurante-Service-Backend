package com.projetoweb4.comandaRestaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.projetoweb4.comandaRestaurante.entity.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {

	UserDetails findByEmail(String login);
	  
	boolean existsByEmail(String email);

}

package com.projetoweb4.comandaRestaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoweb4.comandaRestaurante.entity.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {

	boolean existsByEmail(String email);

}
package com.projetoweb4.comandaRestaurante.repository.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoweb4.comandaRestaurante.entity.domain.StatusGeral;

public interface StatusGeralRepository extends JpaRepository<StatusGeral, Short> {

}
package com.projetoweb4.comandaRestaurante.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projetoweb4.comandaRestaurante.enumeration.StatusGeralEnum;
import com.projetoweb4.comandaRestaurante.repository.LoginRepository;
import com.projetoweb4.comandaRestaurante.validacoes.ValidacaoException;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private LoginRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      var usuario = repository.findByEmailAndStatusGeral_Id(username, StatusGeralEnum.ATIVO.getId());
      if(Objects.nonNull(usuario)) return usuario;
      throw new ValidacaoException("Usuário não encontrado");
    }
}

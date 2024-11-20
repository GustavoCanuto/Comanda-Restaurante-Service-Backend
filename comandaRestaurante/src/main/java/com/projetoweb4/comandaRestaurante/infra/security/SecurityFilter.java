package com.projetoweb4.comandaRestaurante.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.projetoweb4.comandaRestaurante.enumeration.StatusGeralEnum;
import com.projetoweb4.comandaRestaurante.repository.LoginRepository;
import com.projetoweb4.comandaRestaurante.service.recurso.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);

        try {
        if (tokenJWT != null) {
            var subject = tokenService.getSubject(tokenJWT);
            var usuario = repository.findByEmailAndStatusGeral_Id(subject, StatusGeralEnum.ATIVO.getId());

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            // Log das roles para o usuário autenticado
            System.out.println("Usuário autenticado: " + usuario.getUsername());
            usuario.getAuthorities().forEach(authority -> System.out.println("Role enviada: " + authority.getAuthority()));
        }

        filterChain.doFilter(request, response);
        
        } catch (Exception ex) {
            // Configura a resposta com status 403 e inclui o token
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Acesso negado\", \"tokenRecebido\": \"" + (tokenJWT != null ? tokenJWT : "Nenhum token enviado") + "\"}");
            response.getWriter().flush();
        }
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "").trim();
        }

        return null;
    }

}

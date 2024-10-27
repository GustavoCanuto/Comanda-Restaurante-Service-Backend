package com.projetoweb4.comandaRestaurante.service.recurso;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.projetoweb4.comandaRestaurante.entity.Login;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Login usuario) {
        try {
        //	if(usuario.getStatusGeral().getId() == StatusGeralEnum.ATIVO.getId() && Objects.nonNull(usuario.getEmail())) {
	            var algoritmo = Algorithm.HMAC256(secret);
	            return JWT.create()
	                    .withIssuer("API comanda service")
	                    .withSubject(usuario.getEmail())
	                    .withClaim("idUsuario", usuario.getId())
	                    .withClaim("funcionario", usuario.getFuncionario().getId())
	                    .withClaim("cargo", (long) usuario.getFuncionario().getCargoFuncionario().getId())
	                    .withClaim("statusGeral", (long) usuario.getStatusGeral().getId())
	                    .withExpiresAt(dataExpiracao())
	                    .sign(algoritmo);
        //	}
        //	return "Usuário não encontrado";
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerar token jwt", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API comanda service")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(10).toInstant(ZoneOffset.of("-03:00"));
    }

    
    // Método para extrair o ID do funcionário a partir do token JWT
    public Long getFuncionarioIdFromToken(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            DecodedJWT decodedJWT = JWT.require(algoritmo)
                    .withIssuer("API comanda service")
                    .build()
                    .verify(tokenJWT);

            // Extrai a claim "funcionario" do token
            return decodedJWT.getClaim("funcionario").asLong();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!", exception);
        }
    }
    
    
}

package com.projetoweb4.comandaRestaurante.infra;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.projetoweb4.comandaRestaurante.validacoes.EnumInvalidoException;
import com.projetoweb4.comandaRestaurante.validacoes.ValidacaoException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErros {

	// exemplo http://localhost:8080/id/15000
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> tratarErro404() {

		return ResponseEntity.notFound().build();

	}

	@ExceptionHandler(ValidacaoException.class)
	public ResponseEntity<String> tratarErroRegraDeNegocio(ValidacaoException ex) {

		return ResponseEntity.badRequest().body(ex.getMessage());

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> tratarErro500(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + ex.getLocalizedMessage());
	}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }

    }
    
    private record ErrorEnumResponse(String mensagem, List<String> opcoes) {}
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorEnumResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {

        if (ex.getCause() instanceof JsonMappingException) {
            JsonMappingException jsonMappingException = (JsonMappingException) ex.getCause();
            if (jsonMappingException.getCause() instanceof EnumInvalidoException) {
                EnumInvalidoException enumException = (EnumInvalidoException) jsonMappingException.getCause();
                return handleEnumInvalidoException(enumException);
            }
        }

        throw new IllegalArgumentException("Erro desconhecido durante o parsing do JSON.");
    }
    
    @ExceptionHandler(EnumInvalidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorEnumResponse> handleEnumInvalidoException(EnumInvalidoException ex) {
        String mensagem = "Valor " + ex.getValorInvalido() + " inválido. Utilize uma das opções:";
        List<String> opcoes = ex.getOpcoes();
        ErrorEnumResponse errorResponse = new ErrorEnumResponse(mensagem, opcoes);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}

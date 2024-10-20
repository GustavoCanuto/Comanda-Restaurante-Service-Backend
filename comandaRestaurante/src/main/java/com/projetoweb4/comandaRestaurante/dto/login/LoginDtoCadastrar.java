package com.projetoweb4.comandaRestaurante.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginDtoCadastrar(
		@NotBlank
		@Email
		@Size(max = 255)
		String email,
		@NotBlank
		@Size(max = 255)
		String senha,
		@NotNull
		Long idFuncionario
		) {

}

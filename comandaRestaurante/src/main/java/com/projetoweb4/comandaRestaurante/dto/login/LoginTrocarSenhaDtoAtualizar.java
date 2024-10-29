package com.projetoweb4.comandaRestaurante.dto.login;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginTrocarSenhaDtoAtualizar(
		@NotBlank
		@Size(max = 255)
		String senhaAntiga,
		@NotBlank
		@Size(max = 255)
		String novaSenha
		) {

}

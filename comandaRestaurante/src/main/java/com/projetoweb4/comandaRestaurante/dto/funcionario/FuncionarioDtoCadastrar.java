package com.projetoweb4.comandaRestaurante.dto.funcionario;

import com.projetoweb4.comandaRestaurante.entity.domain.CargoFuncionario;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FuncionarioDtoCadastrar(
		@NotBlank
		@Size(max = 255)
		String nome,
		@NotBlank
		@Size(max = 14)
		String cpf,
		@NotNull
		@Enumerated(EnumType.STRING)
        CargoFuncionario cargoFuncionario
		) {

}

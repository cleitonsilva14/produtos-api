package io.api.produtos.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoDto(
		@NotBlank(message = "Nome deve ser informado") String nome, 
		@NotNull(message = "Deve ser informado o código do produto") Long codigo, 
		@DecimalMin(value = "0.0", message = "valor não pode ser null",  inclusive = false) BigDecimal preco) {
	
}

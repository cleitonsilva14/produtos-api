package io.api.produtos.dto;

import jakarta.validation.constraints.NotBlank;

public record ProdutoUpdateNomeDto(
		@NotBlank(message = "Nome deve ser informado") String nome) {
}

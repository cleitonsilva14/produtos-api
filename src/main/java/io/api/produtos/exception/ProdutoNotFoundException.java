package io.api.produtos.exception;

public class ProdutoNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ProdutoNotFoundException(String message) {
		super(message);
	}
}

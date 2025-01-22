package com.robust.api.exception;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException (Long id) {
		super("Recurso não encontrado: " + id);
	}
}

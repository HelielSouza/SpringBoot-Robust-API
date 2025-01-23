package com.robust.api.exception;

public class RequiredObjectIsNullException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RequiredObjectIsNullException () {
		super("Não é possível persistir um objeto nulo");
	}
}

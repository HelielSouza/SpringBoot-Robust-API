package com.robust.api.exception;

public class RequiredObjectIsNullException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RequiredObjectIsNullException () {
		super("O objeto requerido não pode ser nulo");
	}
}

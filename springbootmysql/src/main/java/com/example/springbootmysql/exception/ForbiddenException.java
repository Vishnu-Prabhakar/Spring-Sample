package com.example.springbootmysql.exception;

public class ForbiddenException extends CustomException {

	private static final long serialVersionUID = 8571459916152111756L;

	public ForbiddenException(String message) {
		super(message);
	}
}

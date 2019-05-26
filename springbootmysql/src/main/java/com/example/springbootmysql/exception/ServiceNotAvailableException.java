package com.example.springbootmysql.exception;

public class ServiceNotAvailableException extends CustomException {

	private static final long serialVersionUID = 8538759173756377370L;

	public ServiceNotAvailableException(String message) {
		super(message);
	}
}

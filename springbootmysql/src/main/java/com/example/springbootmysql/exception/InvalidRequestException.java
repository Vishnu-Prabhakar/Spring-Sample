package com.example.springbootmysql.exception;

public class InvalidRequestException extends CustomException {

	private static final long serialVersionUID = -2509652874379467282L;

	public InvalidRequestException(String message) {
		super(message);
	}

}

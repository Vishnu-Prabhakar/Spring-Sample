package com.example.springbootmysql.exception;

public class InvalidAuthorizationTokenException extends CustomException {

	private static final long serialVersionUID = -3332292346834265371L;

	public InvalidAuthorizationTokenException(String message) {
		super(message);
	}
}

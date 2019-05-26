package com.example.springbootmysql.exception;

public class UnauthorizedException extends CustomException {

	private static final long serialVersionUID = 758350403883966397L;

	public UnauthorizedException(String message) {
		super(message);
	}
}

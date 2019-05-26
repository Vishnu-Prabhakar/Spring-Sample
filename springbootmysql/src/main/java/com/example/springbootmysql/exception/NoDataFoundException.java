package com.example.springbootmysql.exception;

public class NoDataFoundException extends CustomException {

	private static final long serialVersionUID = -2509652874379467281L;

	public NoDataFoundException(String message) {
		super(message);
	}

}

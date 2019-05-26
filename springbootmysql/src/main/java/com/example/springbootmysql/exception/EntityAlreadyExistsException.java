package com.example.springbootmysql.exception;

public class EntityAlreadyExistsException extends CustomException {

	private static final long serialVersionUID = -2509652874379467281L;

	public EntityAlreadyExistsException(String message) {
		super(message);
	}

}

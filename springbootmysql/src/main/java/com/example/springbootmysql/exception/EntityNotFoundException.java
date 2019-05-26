package com.example.springbootmysql.exception;

public class EntityNotFoundException extends CustomException {

	private static final long serialVersionUID = -2574437287406326922L;

	public EntityNotFoundException(String message) {
		super(message);
	}

}

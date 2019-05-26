package com.example.springbootmysql.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ExceptionResponse {
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
	private String timestamp;
	private String message;
	private String details;
	private String httpCodeMessage;
	private String traceId;
}
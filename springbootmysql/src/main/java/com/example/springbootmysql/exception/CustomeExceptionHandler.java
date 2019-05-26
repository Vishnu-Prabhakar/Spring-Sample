package com.example.springbootmysql.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.HttpStatus.UNSUPPORTED_MEDIA_TYPE;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;

import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.springbootmysql.constants.ExceptionMessage;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomeExceptionHandler extends ResponseEntityExceptionHandler {

	private Logger log = LoggerFactory.getLogger(CustomeExceptionHandler.class);

	/**
	 * Handle MissingServletRequestParameterException. Triggered when a 'required'
	 * request parameter is missing.
	 *
	 * @param ex      MissingServletRequestParameterException
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the exceptionResponseEntity object
	 */
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String errorMessage = ex.getParameterName() + ExceptionMessage.PARARMETER_IS_MISSING;
		return buildExceptionReponseEntity(BAD_REQUEST, errorMessage, request);
	}

	/**
	 * Handle ServletRequestBindingException. Triggered when HTTP request method not
	 * supported
	 * 
	 * @param ex      ServletRequestBindingException
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the exceptionResponseEntity object
	 */
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return buildExceptionReponseEntity(METHOD_NOT_ALLOWED, ex.getMessage(), request);
	}

	/**
	 * Handle ServletRequestBindingException. Triggered when a 'required' request
	 * header parameter is missing.
	 * 
	 * @param ex      ServletRequestBindingException
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the exceptionResponseEntity object
	 */
	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {	
			return buildExceptionReponseEntity(BAD_REQUEST, ex.getMessage(), request);
	}

	/**
	 * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is
	 * invalid as well.
	 *
	 * @param ex      HttpMediaTypeNotSupportedException
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the exceptionResponseEntity object
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		StringBuilder errorMessage = new StringBuilder();
		errorMessage.append(ex.getContentType());
		errorMessage.append(" media type is not supported. Supported media type is application/json");
		return buildExceptionReponseEntity(UNSUPPORTED_MEDIA_TYPE, errorMessage.toString(), request);
	}

	/**
	 * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid
	 * validation.
	 *
	 * @param ex      the MethodArgumentNotValidException that is thrown when @Valid
	 *                validation fails
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the exceptionResponseEntity object
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return buildExceptionReponseEntity(BAD_REQUEST, ex.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getDefaultMessage()).collect(Collectors.toList()).toString(), request);
	}

	/**
	 * Handle HttpMessageNotReadableException. Happens when request JSON is
	 * malformed.
	 *
	 * @param ex      HttpMessageNotReadableException
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the ApiError object
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return buildExceptionReponseEntity(BAD_REQUEST, ExceptionMessage.MALFORMED_JSON_REQUEST, request);

	}

	/**
	 * Handle Exception, handle custom exception EntityNotFoundException.class
	 *
	 * @param ex the Exception
	 * @return the ApiError object
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFoundException(Exception ex, WebRequest request) {
		return buildExceptionReponseEntity(NOT_FOUND, ex.getMessage(), request);
	}

	/**
	 * Handle Exception, handle custom exception UnauthorizedException.class
	 *
	 * @param ex the Exception
	 * @return the ApiError object
	 */
	@ExceptionHandler(UnauthorizedException.class)
	protected ResponseEntity<Object> handleUnauthorizedException(Exception ex, WebRequest request) {
		return buildExceptionReponseEntity(UNAUTHORIZED, ex.getMessage(), request);
	}
	
	/**
	 * Handle Exception, handle custom exception ForbiddenException.class
	 *
	 * @param ex the Exception
	 * @return the ApiError object
	 */
	@ExceptionHandler(ForbiddenException.class)
	protected ResponseEntity<Object> handleForbiddenExceptionException(Exception ex, WebRequest request) {
		return buildExceptionReponseEntity(FORBIDDEN, ex.getMessage(), request);
	}

	/**
	 * Handle Exception, handle custom exception
	 * InvalidAuthorizationTokenException.class
	 *
	 * @param ex the Exception
	 * @return the ApiError object
	 */
	@ExceptionHandler(InvalidAuthorizationTokenException.class)
	protected ResponseEntity<Object> handleInvalidAuthorizationTokenException(Exception ex, WebRequest request) {
		return buildExceptionReponseEntity(BAD_REQUEST, ex.getMessage(), request);
	}

	/**
	 * Handle Exception, handle custom exception
	 * IdentityServiceNotAvailableException.class
	 *
	 * @param ex the Exception
	 * @return the ApiError object
	 */
	@ExceptionHandler(ServiceNotAvailableException.class)
	protected ResponseEntity<Object> handleIdentityServiceNotAvailableException(Exception ex, WebRequest request) {
		return buildExceptionReponseEntity(INTERNAL_SERVER_ERROR, ex.getMessage(), request);
	}

	/**
	 * Handle Exception, handle custom exception EntityAlreadyExistsException.class
	 *
	 * @param ex the Exception
	 * @return the ApiError object
	 */
	@ExceptionHandler(EntityAlreadyExistsException.class)
	protected ResponseEntity<Object> handleEntityAlreadyExistsException(Exception ex, WebRequest request) {
		return buildExceptionReponseEntity(BAD_REQUEST, ex.getMessage(), request);
	}

	/**
	 * Handle Exception, handle custom exception MaximumSaveRangeLimitBreachException.class
	 *
	 * @param ex the Exception
	 * @return the ApiError object
	 */
	@ExceptionHandler(NoDataFoundException.class)
	protected ResponseEntity<Object> handleMaximumSaveRangeLimitBreachException(Exception ex, WebRequest request) {
		return buildExceptionReponseEntity(OK, ex.getMessage(), request);
	}
	
	/**
	 * Handle generic exceptions
	 *
	 * @param ex      the Exception
	 * @param request the WebRequest
	 * @return the ApiError object
	 */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
		log.error("Exception: ", ex);
		return buildExceptionReponseEntity(INTERNAL_SERVER_ERROR,
				ExceptionMessage.ERROR_OCCURRED_WHILE_PROCESSING_YOUR_REQUEST, request);
	}

	/**
	 * Construct exception response entity
	 * 
	 * @param status
	 * @param errorMessage
	 * @param request
	 * @return
	 */
	protected ResponseEntity<Object> buildExceptionReponseEntity(HttpStatus status, String errorMessage,
			WebRequest request) {
		return new ResponseEntity<>(ExceptionResponse.builder().httpCodeMessage(status.getReasonPhrase())
				.details(request.getDescription(false)).message(errorMessage).timestamp(new Date().toString())
				.traceId(request.getHeader("TraceId")).build(), status);
	}

}

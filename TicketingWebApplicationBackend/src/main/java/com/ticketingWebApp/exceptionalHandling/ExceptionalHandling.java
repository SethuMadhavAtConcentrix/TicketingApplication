package com.ticketingWebApp.exceptionalHandling;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ticketingWebApp.dao.ApiResponse;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionalHandling {

	@ExceptionHandler(SourceUnAvailable.class)
	public ResponseEntity<ApiResponse> sourceunAvailableException(SourceUnAvailable exception) {
		String message = exception.getMessage();
		ApiResponse responseApi = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(responseApi, HttpStatus.NOT_FOUND);
	}
	
	/*
	 * @ExceptionHandler(ConstraintViolationException.class) public
	 * ResponseEntity<ApiResponse>
	 * constraintViolationException(ConstraintViolationException exception){
	 * 
	 * String message = exception.getMessage(); ApiResponse responseApi = new
	 * ApiResponse(message, false);
	 * 
	 * return new ResponseEntity<ApiResponse>(responseApi, HttpStatus.BAD_REQUEST);
	 * }
	 */
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValid(MethodArgumentNotValidException exception){
		Map<String, String> response = new HashMap<>();
		exception.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			response.put(fieldName, message);
		});
		
		return new ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_REQUEST);
	}
	
	

}

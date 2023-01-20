package com.appTicketing.exceptionalHandling;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.appTicketing.dao.ResponseApi;

@RestControllerAdvice
public class ExceptionHandling {

	@ExceptionHandler(SourceUnAvailable.class)
	public ResponseEntity<ResponseApi> sourceunAvailableException(SourceUnAvailable exception) {
		String message = exception.getMessage();
		ResponseApi responseApi = new ResponseApi(message, false);
		return new ResponseEntity<ResponseApi>(responseApi, HttpStatus.NOT_FOUND);
	}
	
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

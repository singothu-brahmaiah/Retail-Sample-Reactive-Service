package com.reactive.springboot.advice;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.reactive.springboot.customexception.EmptyInputException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ProductControllerAdvice {

	/* custom exception for understanding the exception error */
	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<String> handleEmptyInput(EmptyInputException emptyInputException) {
		log.info("testing....");
		return new ResponseEntity<String>("Input fields are empty. Please check request", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException noSuchElementException) {
		return new ResponseEntity<String>("No value present in DB. Please check request", HttpStatus.NOT_FOUND);
	}

}

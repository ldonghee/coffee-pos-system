package com.dhlee.coffeepossystem.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

import com.dhlee.coffeepossystem.common.dto.ExceptionResponse;

@Slf4j
@RestController
@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {
	/**
	 * Exception 발생 시, 실행되는 메서드
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception e) {
		log.error("Not Handle Exception", e);
		return new ResponseEntity<>(new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()),
									HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

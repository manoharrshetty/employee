package com.emp.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.emp.exception.CustomErrorResponse;
import com.emp.exception.EmpRelatedException;
/**
 * handles exception from all the controllers.
 * @author manoh
 *
 */
@ControllerAdvice
public class AppControllerErrorAdvice extends ResponseEntityExceptionHandler {
	Logger logger = LoggerFactory.getLogger(AppControllerErrorAdvice.class);	
	
	
	@ExceptionHandler(EmpRelatedException.class)
    public ResponseEntity<CustomErrorResponse> customHandleNotFound(Exception ex, WebRequest request) {
		return error(HttpStatus.NOT_FOUND,ex);

    }
	@ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomErrorResponse> customHandleOthers(RuntimeException ex, WebRequest request) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR,ex);

    }
	
	
	
    private ResponseEntity<CustomErrorResponse> error(HttpStatus status, Exception e) {
        logger.error("Following error thrown in the Employee Application", e);
    	CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(e.getMessage());
        errors.setStatus(status.value());
        return new ResponseEntity<>(errors, status);
    
    }

}
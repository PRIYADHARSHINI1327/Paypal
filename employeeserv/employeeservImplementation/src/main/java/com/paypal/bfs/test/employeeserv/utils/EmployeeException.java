package com.paypal.bfs.test.employeeserv.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class EmployeeException{
	
	private String INCORRECT_REQUEST = "INCORRECT_REQUEST";
    private String BAD_REQUEST = "BAD_REQUEST";
     
    @ExceptionHandler(EmployeeNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException
                        (EmployeeNotFoundException ex, WebRequest request) 
    {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(INCORRECT_REQUEST, details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidEmployeeDobException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException
                        (InvalidEmployeeDobException ex, WebRequest request) 
    {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(BAD_REQUEST, details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}

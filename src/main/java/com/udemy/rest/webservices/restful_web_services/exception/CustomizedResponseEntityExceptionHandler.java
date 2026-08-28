package com.udemy.rest.webservices.restful_web_services.exception;

import com.udemy.rest.webservices.restful_web_services.users.UserNotFoundException;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
    ErrorDetails errorDetails =  new ErrorDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));

    return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails =  new ErrorDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    protected  ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ErrorDetails errorDetails =  new ErrorDetails(LocalDate.now(),
                                   "total errors are: "+ ex.getErrorCount() + " first one is: "+ex.getFieldError().getDefaultMessage(), request.getDescription(false));

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);


    }
}

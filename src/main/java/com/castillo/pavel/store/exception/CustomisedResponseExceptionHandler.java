package com.castillo.pavel.store.exception;

import com.castillo.pavel.store.exception.custom.CompanyNotFoundException;
import com.castillo.pavel.store.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@ControllerAdvice
public class CustomisedResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAll(Exception ex, WebRequest request) {
        String uri = ServletUriComponentsBuilder.fromCurrentRequest().path("").toUriString();

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), uri);

        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleAll(CompanyNotFoundException ex, WebRequest request) {
        String uri = ServletUriComponentsBuilder.fromCurrentRequest().path("").toUriString();

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), uri);

        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

}

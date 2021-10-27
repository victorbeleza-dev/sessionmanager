package com.sessionmanager.ws.exceptions;

import com.sessionmanager.model.response.ApiErrorVO;
import com.sessionmanager.model.response.ErrorMessage;
import javassist.NotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception e, WebRequest request){

        String errorDescription = e.getLocalizedMessage();
        if(errorDescription == null) errorDescription = e.toString();

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorDescription, new Date());
        return new ResponseEntity<Object>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = "Mandatory fields are null!";
        List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

        ApiErrorVO apiErrorVO = new ApiErrorVO(HttpStatus.BAD_REQUEST.value(), errorMessage, validationList);
        return new ResponseEntity<>(apiErrorVO, status);
    }

    @ExceptionHandler(value = {DuplicateKeyException.class})
    protected ResponseEntity<Object> handleDuplicateKeyException(DuplicateKeyException e, WebRequest request) {
        String errorDescription = e.getLocalizedMessage();
        if(errorDescription == null) errorDescription = e.toString();

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), errorDescription, new Date());
        return new ResponseEntity<Object>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException e, WebRequest request) {
        String errorDescription = e.getLocalizedMessage();
        if(errorDescription == null) errorDescription = e.toString();

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), errorDescription, new Date());
        return new ResponseEntity<Object>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {IllegalAccessException.class})
    protected ResponseEntity<Object> IllegalAccessException(IllegalAccessException e, WebRequest request) {
        String errorDescription = e.getLocalizedMessage();
        if(errorDescription == null) errorDescription = e.toString();

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), errorDescription, new Date());
        return new ResponseEntity<Object>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}

package br.ufmg.watchdogs.server.api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(value = {
            MyDataAlreadyExistsException.class,
            MyDataNotFoundException.class,
            MyInvalidEnumValueException.class,
            MyDataAlreadyExistsException.class,
            MyInvalidTokenException.class
    })
    public ResponseEntity<ExceptionEntity> handleExceptionBaseClass(
            MyBaseException exception,
            final HttpServletRequest request
    ) {

        return ResponseEntity
                .status(exception.getExceptionHttpStatus())
                .body(new ExceptionEntity(exception, request));
    }
}

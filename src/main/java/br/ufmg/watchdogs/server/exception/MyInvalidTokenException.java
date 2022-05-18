package br.ufmg.watchdogs.server.exception;

import org.springframework.http.HttpStatus;

public class MyInvalidTokenException extends MyBaseException {

    public MyInvalidTokenException(String message, String throwerClass) {
        super(message, throwerClass);
    }

    @Override
    public HttpStatus getExceptionHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getClassName() {
        return this.getClass().getName();
    }
}
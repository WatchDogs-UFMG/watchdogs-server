package br.ufmg.watchdogs.server.exception;

import org.springframework.http.HttpStatus;

public class MyInvalidEnumValueException extends MyBaseException {

    public MyInvalidEnumValueException(String message, String throwerClass) {
        super(message, throwerClass);
    }

    @Override
    public HttpStatus getExceptionHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getClassName() {
        return this.getClass().getName();
    }
}
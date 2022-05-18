package br.ufmg.watchdogs.server.exception;

import org.springframework.http.HttpStatus;

public class MyDataAlreadyExistsException extends MyBaseException {

    public MyDataAlreadyExistsException(String message, String throwerClass) {
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

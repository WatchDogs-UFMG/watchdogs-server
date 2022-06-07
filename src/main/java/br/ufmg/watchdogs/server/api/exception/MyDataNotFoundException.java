package br.ufmg.watchdogs.server.api.exception;

import org.springframework.http.HttpStatus;

public class MyDataNotFoundException extends MyBaseException {

    public MyDataNotFoundException(String message, String throwerClass) {
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

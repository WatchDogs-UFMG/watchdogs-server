package br.ufmg.watchdogs.server.api.exception;

import org.springframework.http.HttpStatus;

public class MyDateTimeParseException extends MyBaseException {

    public MyDateTimeParseException(String message, String throwerClass) {
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

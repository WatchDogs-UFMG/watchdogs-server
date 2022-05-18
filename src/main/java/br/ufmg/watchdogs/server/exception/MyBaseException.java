package br.ufmg.watchdogs.server.exception;

import org.springframework.http.HttpStatus;

abstract public class MyBaseException extends RuntimeException {

    protected String throwerClass;

    public MyBaseException(String message, String throwerClass) {
        super(message);
        this.throwerClass = throwerClass;
    }

    public String getThrowerClass() {
        return throwerClass;
    }

    abstract public HttpStatus getExceptionHttpStatus();

    public abstract String getClassName();
}

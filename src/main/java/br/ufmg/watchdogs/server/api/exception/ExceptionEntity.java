package br.ufmg.watchdogs.server.api.exception;

import br.ufmg.watchdogs.server.api.util.MyDateTimeFormatterUtil;
import br.ufmg.watchdogs.server.api.util.MyStringStackTraceUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class ExceptionEntity {

    private final String timestamp;
    private final int status;
    private final String error;
    private final String trace;
    private final String message;
    private final String path;

    public ExceptionEntity(MyBaseException exception, HttpServletRequest request) {
        this.timestamp = LocalDateTime.now().format(MyDateTimeFormatterUtil.FORMATTER);
        this.status = exception.getExceptionHttpStatus().value();
        this.error = exception.getExceptionHttpStatus().getReasonPhrase();
        this.trace = new MyStringStackTraceUtil().getStringStackTrace(exception);
        this.message = exception.getMessage() + (exception.getMessage().endsWith("!") ? "" : "!");
        this.path = request.getRequestURI();
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getTrace() {
        return trace;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}

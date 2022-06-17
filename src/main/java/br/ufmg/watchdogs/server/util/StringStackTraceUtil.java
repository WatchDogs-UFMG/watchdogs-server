package br.ufmg.watchdogs.server.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class StringStackTraceUtil {

    private final StringWriter stringWriter;
    private final PrintWriter printWriter;

    public StringStackTraceUtil() {
        this.stringWriter = new StringWriter();
        this.printWriter = new PrintWriter(this.stringWriter);
    }

    public String getStringStackTrace(Exception exception) {
        exception.printStackTrace(printWriter);
        return stringWriter.toString();
    }
}

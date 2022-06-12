package br.ufmg.watchdogs.server.api.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class MyStringStackTraceUtil {

    private final StringWriter stringWriter;
    private final PrintWriter printWriter;

    public MyStringStackTraceUtil() {
        this.stringWriter = new StringWriter();
        this.printWriter = new PrintWriter(this.stringWriter);
    }

    public String getStringStackTrace(Exception exception) {
        exception.printStackTrace(printWriter);
        return stringWriter.toString();
    }
}

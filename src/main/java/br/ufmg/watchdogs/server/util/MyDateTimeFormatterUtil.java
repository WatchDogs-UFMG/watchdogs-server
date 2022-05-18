package br.ufmg.watchdogs.server.util;

import java.time.format.DateTimeFormatter;

public class MyDateTimeFormatterUtil {

    public static final String PATTERN = "dd/MM/yyyy HH:mm:ss";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);
}

package br.ufmg.watchdogs.server.api.util;

import java.time.format.DateTimeFormatter;

public class MyDateFormatterUtil {

    public static final String PATTERN = "dd/MM/yyyy";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);
}

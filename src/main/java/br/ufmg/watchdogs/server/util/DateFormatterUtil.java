package br.ufmg.watchdogs.server.util;

import java.time.format.DateTimeFormatter;

public class DateFormatterUtil {

    public static final String PATTERN = "dd/MM/yyyy";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);
}

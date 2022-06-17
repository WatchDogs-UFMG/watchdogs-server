package br.ufmg.watchdogs.server.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterUtil {

    public static final String PATTERN = "dd/MM/yyyy HH:mm:ss";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    public static LocalDateTime parseFromFormattedString(String formattedString) {
        return LocalDateTime.parse(formattedString, FORMATTER);
    }

    public static String getFormattedDateTimeString(
            Long second,
            Long minute,
            Long hour,
            Long day,
            Long month,
            Long year
    ) {

        String secondString = NumberStringFormatterUtil.formatString(second, 2);
        String minuteString = NumberStringFormatterUtil.formatString(minute, 2);
        String hourString = NumberStringFormatterUtil.formatString(hour, 2);
        String dayString = NumberStringFormatterUtil.formatString(day, 2);
        String monthString = NumberStringFormatterUtil.formatString(month, 2);
        String yearString = NumberStringFormatterUtil.formatString(year, 4);

        String formattedString = PATTERN.replace("ss", secondString);
        formattedString = formattedString.replace("mm", minuteString);
        formattedString = formattedString.replace("HH", hourString);
        formattedString = formattedString.replace("dd", dayString);
        formattedString = formattedString.replace("MM", monthString);
        formattedString = formattedString.replace("yyyy", yearString);

        return formattedString;
    }
}

package br.ufmg.watchdogs.server.util;

public class NumberStringFormatterUtil {

    public static String formatString(Long value, Integer finalLength) {

        StringBuilder originalStringBuilder = new StringBuilder(value.toString());

        for (int i = originalStringBuilder.length(); i < finalLength; i++) {
            originalStringBuilder.insert(0, "0");
        }

        return originalStringBuilder.toString();
    }

    public static String formatString(String valueString, Integer finalLength) {

        StringBuilder originalStringBuilder = new StringBuilder(valueString);

        for (int i = originalStringBuilder.length(); i < finalLength; i++) {
            originalStringBuilder.insert(0, "0");
        }

        return originalStringBuilder.toString();
    }
}

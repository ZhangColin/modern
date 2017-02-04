package com.cartisan.modern.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static java.time.ZoneId.systemDefault;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.Date.from;

public class Formats {
    public static final String MONTH = "yyyy-MM";
    public static final String DAY = "yyyy-MM-dd";

    public static Date parseDay(String day) {
        return parseDate(day, DAY);
    }

    public static LocalDate parseMonth(String month){
        return LocalDate.parse(month + "-01", ofPattern(DAY));
    }

    public static LocalDate parseDayToLocalDate(String day) {
        return LocalDate.parse(day, ofPattern(DAY));
    }

    public static Date dateOf(LocalDate date) {
        return from(date.atStartOfDay(systemDefault()).toInstant());
    }

    private static Date parseDate(String source, String pattern){
        try{
            return new SimpleDateFormat(pattern).parse(source);
        } catch (ParseException e) {
            throw new IllegalStateException(e);
        }
    }
}

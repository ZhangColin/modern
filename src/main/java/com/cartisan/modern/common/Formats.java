package com.cartisan.modern.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Formats {
    public static final String MONTH = "yyyy-MM";
    public static final String DAY = "yyyy-MM-dd";

    public static Date parseDay(String day) {
        try {
            return new SimpleDateFormat(DAY).parse(day);
        } catch (ParseException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Date parseMonth(String month) {
        try {
            return new SimpleDateFormat(MONTH).parse(month);
        } catch (ParseException e) {
            throw new IllegalStateException(e);
        }
    }
}

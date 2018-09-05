package com.pact.consumer.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {

    public static Date parseDate(String date) {
        try {
            java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            Date sqlDate = new Date(date2.getTime());
            return sqlDate;
        } catch (ParseException e) {
            return null;
        }
    }
}

package com.lightquark.fuf.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Light Quark.
 */
public class TimestampFormatter
{
    private static final String DATE_FORMAT_FULL_SLASH = "dd/MMMM/yyyy HH:mm:ss:SSS";
    private static final String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT_DATE = "yyyy-MM-dd";
    private static final String DATE_FORMAT_DATE_E = "yyyy-MM-dd E";

    public static String format(long t)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_FULL);
        return sdf.format(new Timestamp(t));
    }

    public static String format(Date dt)
    {
        if (dt == null)
            return "";

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_FULL);
        return sdf.format(dt);
    }
}

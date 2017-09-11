package com.lightquark.fuf.utils;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Calendar;

/**
 * Created by Light Quark.
 */
public class CommonUtils
{
    public static boolean safeEquals(String s1, String s2)
    {
        return s1 != null && s2 != null && s1.equals(s2);
    }

    public static String getShortClassName(Object o)
    {
        if (o == null)
            return "";
        Class clazz = o.getClass();
        return clazz.getName().replace(clazz.getPackage().getName() + ".", "");
    }

    public static String convertToString(Object o)
    {
        return o != null ? ReflectionToStringBuilder.toString(o, ToStringStyle.SHORT_PREFIX_STYLE) : "";
    }

    public static void initTimeToStartOfTheDay(Calendar cal)
    {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
    }

    public static void initTimeToEndOfTheDay(Calendar cal)
    {
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
    }
}

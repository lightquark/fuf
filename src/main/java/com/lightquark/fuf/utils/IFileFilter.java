package com.lightquark.fuf.utils;

/**
 * Created by Light Quark.
 */
@FunctionalInterface
public interface IFileFilter
{
    boolean processLine(String line);
}

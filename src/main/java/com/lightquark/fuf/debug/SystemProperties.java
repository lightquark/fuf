package com.lightquark.fuf.debug;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Light Quark.
 */
public class SystemProperties
{
    private static final Logger LOG = LogManager.getLogger();

    /////////////////////////////////////////////////////////////////////////////
    //
    /////////////////////////////////////////////////////////////////////////////

    public static void printSystemProperties()
    {
        ArrayList<String> p = new ArrayList<>(100);
        System.getProperties().forEach((key, value) -> p.add(key + ": " + value));
        Collections.sort(p);

        LOG.info("System properties START");
        p.forEach(LOG::info);
        LOG.info("System properties END");
    }
}

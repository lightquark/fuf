package com.lightquark.fuf;

import com.lightquark.fuf.debug.DebugUsers;
import com.lightquark.fuf.localization.LocalTexts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Created by Light Quark.
 */
public class SampleApp
{
    private static final Logger LOG = LogManager.getLogger();

    public static void main(String args[]) throws Exception
    {
        String configPath = System.getProperty("user.dir") + File.separator + "config" + File.separator + args[0] + File.separator;

        DebugUsers debugUsers = new DebugUsers(configPath + "debug_users.txt");
        LocalTexts localTexts = new LocalTexts(configPath + "localization");
    }
}

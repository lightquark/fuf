package com.lightquark.fuf.localization;

import com.lightquark.fuf.utils.FileUtils;
import com.lightquark.fuf.utils.IFileFilter;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Light Quark.
 */
public class LocalTexts
{
    private static final Logger LOG = LogManager.getLogger();

    private static final String[] LANGS = { "en", "fr", "de", "it", "es", "ru", "uk" };
    private static final String DEFAULT_VALUE = "Value is not defined for this language";

    private HashMap<String, HashMap<String, String>> texts;

    /////////////////////////////////////////////////////////
    //
    /////////////////////////////////////////////////////////

    public LocalTexts(String dirName)
    {
        try
        {
            texts = new HashMap<>();
            for (String lang : LANGS)
            {
                texts.put(lang, loadFromFile(dirName + File.separator + lang + ".lang"));
            }

            // print texts in debug mode
            if (LOG.isDebugEnabled())
            {
                for (Map.Entry<String, HashMap<String, String>> entry : texts.entrySet())
                {
                    LOG.debug("LANG: " + entry.getKey());
                    HashMap<String, String> m = entry.getValue();
                    if (m != null)
                    {
                        for (Map.Entry<String, String> x : m.entrySet())
                            LOG.debug(x.getKey() + " = " + x.getValue());
                    }
                }
            }
        }
        catch (IOException e)
        {
            LOG.error(e.getMessage() + "\n" + ExceptionUtils.getStackTrace(e));
        }
    }

    /////////////////////////////////////////////////////////
    //
    /////////////////////////////////////////////////////////

    public String getText(String lang, String key)
    {
        HashMap<String, String> m = texts.get(lang);
        return m != null ? m.get(key) : DEFAULT_VALUE;
    }

    /////////////////////////////////////////////////////////
    //
    /////////////////////////////////////////////////////////

    private HashMap<String, String> loadFromFile(String filePath) throws IOException
    {
        HashMap<String, String> m = new HashMap<>(100);

        final IFileFilter fileFilter = line ->
        {
            line = line.trim();
            if (line.isEmpty() || line.startsWith("#"))
                return false;

            String[] arr = line.split("=");
            if (arr.length == 2)
                m.put(arr[0].trim(), arr[1].trim());

            return true;
        };

        FileUtils.doFileFilter(filePath, fileFilter);

        return m;
    }
}

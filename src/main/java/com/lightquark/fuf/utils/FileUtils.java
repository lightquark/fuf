package com.lightquark.fuf.utils;

import org.apache.commons.lang3.CharEncoding;

import java.io.*;

/**
 * Created by Light Quark.
 */
public class FileUtils
{
    public static String loadFile(String filePath) throws IOException
    {
        StringBuilder sb = new StringBuilder(1000);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)), CharEncoding.UTF_8)))
        {
            String line;
            while ((line = in.readLine()) != null)
            {
                sb.append(line).append('\n');
            }
        }
        return sb.toString();
    }

    public static void saveFile(String fileName, String content) throws IOException
    {
        try (Writer w = new OutputStreamWriter(new FileOutputStream(new File(fileName)), CharEncoding.UTF_8))
        {
            w.write(content);
        }
    }

    public static void doFileFilter(String filePath, IFileFilter ff) throws IOException
    {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath).getAbsoluteFile()), CharEncoding.UTF_8)))
        {
            String line;
            while ((line = in.readLine()) != null)
            {
                ff.processLine(line);
            }
        }
    }
}

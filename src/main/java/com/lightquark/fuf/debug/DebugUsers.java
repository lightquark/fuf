package com.lightquark.fuf.debug;

import com.lightquark.fuf.utils.FileUtils;
import com.lightquark.fuf.utils.IFileFilter;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashSet;

/**
 * Created by Light Quark.
 */
public class DebugUsers
{
    private static final Logger LOG = LogManager.getLogger();

    private HashSet<String> debugUsers;

    /////////////////////////////////////////////////////////
    //
    /////////////////////////////////////////////////////////

    public DebugUsers(String filePath)
    {
        try
        {
            debugUsers = loadStringsFromFile(filePath);

            if (LOG.isInfoEnabled())
                debugUsers.forEach(value -> LOG.info("DEBUG USER: " + value));
        }
        catch (IOException e)
        {
            LOG.error(e.getMessage() + "\n" + ExceptionUtils.getStackTrace(e));
        }
    }

    /////////////////////////////////////////////////////////
    //
    /////////////////////////////////////////////////////////

    public boolean isDebugUser(String userID)
    {
        return debugUsers.contains(userID);
    }

    /////////////////////////////////////////////////////////
    //
    /////////////////////////////////////////////////////////

    private HashSet<Long> loadLongsFromFile(String filePath) throws IOException
    {
        HashSet<Long> users = new HashSet<>();

        final IFileFilter fileFilter = line ->
        {
            line = line.trim();
            if (line.isEmpty() || line.startsWith("#"))
                return false;

            users.add(Long.valueOf(line));
            return true;
        };

        FileUtils.doFileFilter(filePath, fileFilter);

        return users;
    }

    private HashSet<String> loadStringsFromFile(String filePath) throws IOException
    {
        HashSet<String> users = new HashSet<>();

        final IFileFilter fileFilter = line ->
        {
            line = line.trim();
            if (line.isEmpty() || line.startsWith("#"))
                return false;

            users.add(line);
            return true;
        };

        FileUtils.doFileFilter(filePath, fileFilter);

        return users;
    }
}

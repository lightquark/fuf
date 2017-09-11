package com.lightquark.fuf.storage.sql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by Light Quark.
 */
public class SQLHelper
{
    private static final Logger LOG = LogManager.getLogger();

    public static Set<String> getSetString(String query, Object[] params) throws SQLException
    {
        Connection con = DatabaseFactory.getConnection();
        PreparedStatement statement = con.prepareStatement(query);
        fillParams(statement, params);

        if (LOG.isDebugEnabled())
            LOG.debug(statement.toString());

        Set<String> data = new HashSet<>();
        ResultSet result = statement.executeQuery();
        while (result.next())
        {
            data.add(result.getString(1));
        }

        DatabaseFactory.close(con, statement, result);
        return data;
    }

    /**
     * Sample:
     * List<String> ids = SQLHelper.getListString("SELECT `account_id` FROM `accounts` WHERE `account_id` > ? ORDER BY `account_id` LIMIT ?", new Object[] { startId, limit });
     */
    public static List<String> getListString(String query, Object[] params) throws SQLException
    {
        Connection con = DatabaseFactory.getConnection();
        PreparedStatement statement = con.prepareStatement(query);
        fillParams(statement, params);

        if (LOG.isDebugEnabled())
            LOG.debug(statement.toString());

        List<String> data = new ArrayList<>();
        ResultSet result = statement.executeQuery();
        while (result.next())
        {
            data.add(result.getString(1));
        }

        DatabaseFactory.close(con, statement, result);
        return data;
    }

    public static Map<Integer, Integer> getMapIntInt(String query, Object[] params) throws SQLException
    {
        Connection con = DatabaseFactory.getConnection();
        PreparedStatement statement = con.prepareStatement(query);
        fillParams(statement, params);

        if (LOG.isDebugEnabled())
            LOG.debug(statement.toString());

        Map<Integer, Integer> data = new LinkedHashMap<>();
        ResultSet result = statement.executeQuery();
        while (result.next())
        {
            data.put(result.getInt(1), result.getInt(2));
            //LOG.debug(result.getInt(1) + " => " + result.getInt(2));
        }

        DatabaseFactory.close(con, statement, result);
        return data;
    }

    public static Map<String, Integer> getMapStringInt(String query, Object[] params) throws SQLException
    {
        Connection con = DatabaseFactory.getConnection();
        PreparedStatement statement = con.prepareStatement(query);
        fillParams(statement, params);

        if (LOG.isDebugEnabled())
            LOG.debug(statement.toString());

        Map<String, Integer> data = new LinkedHashMap<>();
        ResultSet result = statement.executeQuery();
        while (result.next())
        {
            data.put(result.getString(1), result.getInt(2));
        }

        DatabaseFactory.close(con, statement, result);
        return data;
    }

    public static Integer getInt(String query, Object[] params) throws SQLException
    {
        Connection con = DatabaseFactory.getConnection();
        PreparedStatement statement = con.prepareStatement(query);
        fillParams(statement, params);

        if (LOG.isDebugEnabled())
            LOG.debug(statement.toString());

        ResultSet result = statement.executeQuery();
        result.next();
        int data = result.getInt(1);

        DatabaseFactory.close(con, statement, result);
        return data;
    }

    /////////////////////////////////////////////////////////
    //
    /////////////////////////////////////////////////////////

    private static void fillParams(PreparedStatement statement, Object[] params) throws SQLException
    {
        if (params != null)
        {
            for (int i = 0; i < params.length; i++)
            {
                Object param = params[i];
                if (param instanceof String)
                    statement.setString(1 + i, (String) param);
                else if (param instanceof Boolean)
                    statement.setBoolean(1 + i, (boolean) param);
                else if (param instanceof Byte)
                    statement.setByte(1 + i, (byte) param);
                else if (param instanceof Short)
                    statement.setShort(1 + i, (short) param);
                else if (param instanceof Long)
                    statement.setLong(1 + i, (long) param);
                else if (param instanceof Integer)
                    statement.setInt(1 + i, (int) param);
                else if (param instanceof Double)
                    statement.setDouble(1 + i, (double) param);
                else if (param instanceof Float)
                    statement.setFloat(1 + i, (float) param);
                else if (param instanceof Timestamp)
                    statement.setTimestamp(1 + i, (Timestamp) param);
                else if (param instanceof Date)
                    statement.setDate(1 + i, (Date) param);
                else if (param instanceof Time)
                    statement.setTime(1 + i, (Time) param);
                else
                    LOG.error("Unsupported type of value");
            }
        }
    }
}

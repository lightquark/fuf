package com.lightquark.fuf.storage.sql;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Light Quark.
 */
public class DatabaseFactory
{
    private static final Logger LOG = LogManager.getLogger();

    private static ComboPooledDataSource cpds;

    /////////////////////////////////////////////////////////
    //
    /////////////////////////////////////////////////////////

    public static void init(SQLConfig config)
    {
        try
        {
            if (config != null)
                cpds = createDataSource(config);
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage() + "\n" + ExceptionUtils.getStackTrace(e));
        }
    }

    private static ComboPooledDataSource createDataSource(SQLConfig config) throws SQLException
    {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        //ds.setDriverClass("com.mysql.jdbc.Driver");
        ds.setJdbcUrl(config.getUrl());
        ds.setUser(config.getLogin());
        ds.setPassword(config.getPassword());
        ds.setMinPoolSize(1);
        ds.setMaxPoolSize(20);
        // cpds.setIdleConnectionTestPeriod(500000);
        ds.setIdleConnectionTestPeriod(10000);
        ds.setAcquireRetryAttempts(3);
        ds.setAcquireRetryDelay(500);
        ds.setAcquireIncrement(5);
        ds.setMaxIdleTime(11000);

        // test connection
        ds.getConnection().close();

        return ds;
    }

    public static void close(Connection con, Statement statement, ResultSet rs)
    {
        try
        {
            if (rs != null)
                rs.close();
            if (statement != null)
                statement.close();
            if (con != null)
                con.close();
        }
        catch (SQLException e)
        {
            LOG.error(e.getMessage() + "\n" + ExceptionUtils.getStackTrace(e));
        }
    }

    /////////////////////////////////////////////////////////
    //
    /////////////////////////////////////////////////////////

    public static Connection getConnection() throws SQLException
    {
        return cpds.getConnection();
        //try
        //{
        //    return cpds.getConnection();
        //}
        //catch (SQLException e)
        //{
        //    LOG.error(e.getMessage() + "\n" + ExceptionUtils.getStackTrace(e));
        //}
        //return null;
    }
}

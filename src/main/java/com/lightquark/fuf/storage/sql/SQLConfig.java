package com.lightquark.fuf.storage.sql;

/**
 * Created by Light Quark.
 */
public class SQLConfig
{
    private String url;
    private String login;
    private String password;

    /////////////////////////////////////////////////////////
    //
    /////////////////////////////////////////////////////////

    public String getUrl()
    {
        return url;
    }

    public void setUrl(final String url)
    {
        this.url = url;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(final String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(final String password)
    {
        this.password = password;
    }
}

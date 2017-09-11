package com.lightquark.fuf.http;

/**
 * Created by Light Quark.
 */
public class HttpRequestParam implements Comparable<HttpRequestParam>
{
    private final String key;
    private final String value;

    /////////////////////////////////////////////////////////////////////////////
    //
    /////////////////////////////////////////////////////////////////////////////

    public HttpRequestParam(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    /////////////////////////////////////////////////////////////////////////////
    //
    /////////////////////////////////////////////////////////////////////////////

    public String getKey()
    {
        return key;
    }

    public String getValue()
    {
        return value;
    }

    /////////////////////////////////////////////////////////////////////////////
    //
    /////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        HttpRequestParam that = (HttpRequestParam) o;

        return !(key != null ? !key.equals(that.key) : that.key != null);

    }

    @Override
    public int hashCode()
    {
        return key != null ? key.hashCode() : 0;
    }

    @Override
    public int compareTo(HttpRequestParam o)
    {
        return key.compareTo(o.key);
    }
}

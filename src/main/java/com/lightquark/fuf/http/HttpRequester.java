package com.lightquark.fuf.http;

import org.apache.commons.lang3.CharEncoding;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

/**
 * Created by Light Quark.
 */
public class HttpRequester
{
    private static final Logger LOG = LogManager.getLogger();

    //////////////////////////////////////////////////////////////////////////
    //
    //////////////////////////////////////////////////////////////////////////

    public static Document doGet(final DocumentBuilder builder, String url, String urlParameters) throws IOException, SAXException
    {
        if (LOG.isInfoEnabled())
            LOG.info("doGet url=" + url + " params=" + urlParameters);

        URL u = urlParameters.isEmpty() ? new URL(url) : new URL(url + '?' + urlParameters);
        HttpsURLConnection con = getHttpsURLConnection(u, HttpConstants.HTTP_METHOD_GET);

        final Document parse = builder.parse(new InputSource(con.getInputStream()));

        if (LOG.isInfoEnabled())
            LOG.info("doGet OK");

        con.disconnect();
        return parse;
    }

    /**
     * Sample:
     * DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
     * Document document = HttpRequester.doPost(builder, url, HttpRequester.buildParamsString(params, false));
     */
    public static Document doPost(final DocumentBuilder builder, String url, String urlParameters) throws IOException, SAXException
    {
        if (LOG.isInfoEnabled())
            LOG.info("doPost url=" + url + " params=" + urlParameters);

        URL u = new URL(url);
        HttpsURLConnection con = getHttpsURLConnection(u, HttpConstants.HTTP_METHOD_POST);

        //send request data
        byte[] bytes = urlParameters.getBytes(CharEncoding.UTF_8);
        con.setRequestProperty(HttpConstants.HEADER_CONTENT_LENGTH, Integer.toString(bytes.length));
        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream()))
        {
            wr.write(bytes);
            wr.flush();
        }

        //read the answer
        Document parse = builder.parse(new InputSource(con.getInputStream()));

        if (LOG.isInfoEnabled())
            LOG.info("doPost OK");

        con.disconnect();
        return parse;
    }

    //////////////////////////////////////////////////////////////////////////
    //
    //////////////////////////////////////////////////////////////////////////

    public static String buildParamsString(List<HttpRequestParam> params, boolean sort) throws UnsupportedEncodingException
    {
        if (sort)
            Collections.sort(params);

        if (!params.isEmpty())
        {
            StringBuilder sb = new StringBuilder(500);
            for (HttpRequestParam param : params)
            {
                sb.append(param.getKey()).append('=').append(URLEncoder.encode(param.getValue(), CharEncoding.UTF_8)).append('&');
            }
            return sb.substring(0, sb.length() - 1);
        }
        return "";
    }

    //////////////////////////////////////////////////////////////////////////
    //
    //////////////////////////////////////////////////////////////////////////

    private static HttpsURLConnection getHttpsURLConnection(final URL url, final String requestMethod) throws IOException
    {
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        con.setRequestMethod(requestMethod);
        con.setRequestProperty(HttpConstants.HEADER_ACCEPT, HttpConstants.CONTENT_TYPE_APP_XML);
        con.setRequestProperty(HttpConstants.HEADER_CHARSET, CharEncoding.UTF_8);
        con.setUseCaches(false);
        con.setDoOutput(true);
        con.setDoInput(true);
        return con;
    }
}

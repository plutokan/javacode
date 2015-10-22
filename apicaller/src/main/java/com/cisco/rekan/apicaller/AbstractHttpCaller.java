/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */

package com.cisco.rekan.apicaller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.springframework.util.Assert;


/**
 * <code>AbstractHttpCaller</code>
 *
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since MyCode Mar 21, 2014
 *
 */
public abstract class AbstractHttpCaller implements IHttpCaller {

    /**
     * logger of log4j 1.x.
     */
    protected static Logger logger = Logger.getLogger(AbstractHttpCaller.class);

    /** The server url. */
    private String serverURL = "";

    /** The parameters. */
    private List<NameValuePair> parameters = new ArrayList<NameValuePair>();

    /**
     * the caller.
     */
    private HttpClient httpClient = null;

    /**
     * @return the httpClient
     */
    public HttpClient getHttpClient() {
        return httpClient;
    }

    /**
     * @param httpClient the httpClient to set
     */
    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * @return the parameters
     */
    public List<NameValuePair> getParameters() {
        return parameters;
    }

    /**
     * @return the serverURL
     */
    public String getServerURL() {
        return serverURL;
    }

    /**
     * @param serverURL the serverURL to set
     */
    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }

    /* (non-Javadoc)
     * @see #callPostAPI()
     */
    @Override
    public HttpResponse getAPI(String... params) {
        Assert.notNull(this.serverURL);

        this.addParams(params);
        StringBuffer queryStr = new StringBuffer();
        for (NameValuePair param : this.parameters) {
            queryStr.append('&');
            queryStr.append(param.getName());
            queryStr.append('=');
            queryStr.append(param.getValue());
        }
        String getStr = queryStr.toString().replaceFirst("&", "?");
        getStr = this.serverURL + getStr;
        HttpGet httpGet = new HttpGet(getStr);

        HttpResponse response = this.call(httpGet);

        return response;
    }

    /**
     * call this method:<br>
     * <pre>
     * 1. Set the server URL, like "https://pluto.qa.webex.com/pluto/p.php".
     *    This method will construct the HttpClient via the kind of protocol. Now support "http" and "https".
     * 2. Add the parameters into <code>this.parameters</code>.
     *    (1) Please call the method {@link #addParam(String, String)}, like <code>super.addParam("userName", "pluto")</code>.
     *    (2) Implement the method {@link #addParams(String...)}. You can specify the parameter name in the implement
     *     method, like <code>super.addParam("userName", arg[0])</code>.
     * </pre>
     *
     * @return HttpResponse http response.
     */
    @Override
    public HttpResponse postAPI(String... params) {

        this.addParams(params);

        Assert.notNull(this.serverURL);
        HttpPost httpPost = new HttpPost(this.serverURL);
        try {
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(this.parameters);
            httpPost.setEntity(formEntity);
        } catch (UnsupportedEncodingException e) {
            logger.error(null, e);
        }

        HttpResponse response = call(httpPost);

        return response;
    }

    /**
     * @param httpUriRequest
     * @return
     */
    private HttpResponse call(HttpUriRequest httpUriRequest) {
        if (this.httpClient == null) {
            this.httpClient = Utils.getHttpClient(this.serverURL);
        }

        HttpResponse response = null;
        try {
            response = httpClient.execute(httpUriRequest);
            logger.info(response.getStatusLine());
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                Header[] headers = response.getAllHeaders();
                for (Header header : headers) {
                    logger.trace(header.getName() + " : " + header.getValue());
                }
            }
            CookieStore cookieStore = ((DefaultHttpClient) httpClient).getCookieStore();
            logger.trace(cookieStore.toString());
        } catch (IOException e) {
            logger.error(null, e);
        }
        return response;
    }


    /**
     * Call this method and return a DOM4J response.
     *
     * @param params HttpRequest parameters.
     * @return a document of DOM4J.
     */
    public Document callPostAPI(String... params) {
        HttpResponse response = this.postAPI(params);
        HttpEntity responseEntity = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(responseEntity);
        } catch (IOException e) {
            logger.error(null, e);
        }
        logger.debug(result);

        Document resultDoc = Utils.convertStr2Dom(result);

        return resultDoc;
    }

    /**
     * Adds the parms.
     *
     * @param params the params
     */
    public abstract void addParams(String... params);

    /**
     * Adds the param.
     *
     * @param paramName the param name
     * @param paramValue the param value
     */
    public void addParam(String paramName, String paramValue) {
        this.parameters.add(new BasicNameValuePair(paramName, paramValue));
    }

    /**
     * Clear.
     */
    protected void reset() {
        this.serverURL = "";
        this.parameters.clear();
        this.httpClient = null;
    }

}

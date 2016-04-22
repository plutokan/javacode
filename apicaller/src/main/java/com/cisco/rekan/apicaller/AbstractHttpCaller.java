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

    /**
     * The server url. E.g. "https://pluto.qa.webex.com/pluto/nobrowser.php".
     */
    private String serverURL = null;

    /** The parameters. */
    private List<NameValuePair> parameters = new ArrayList<NameValuePair>();

    /**
     * the caller.
     */
    private HttpClient httpClient = null;

    private CookieStore cookieStore;


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
    public HttpResponse get(String... params) {
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

        HttpResponse response = this.callViaHttpClient(httpGet);

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
    public HttpResponse post(String... params) {

        this.addParams(params);

        Assert.notNull(this.serverURL);
        HttpPost httpPost = new HttpPost(this.serverURL);
        try {
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(this.parameters);
            httpPost.setEntity(formEntity);
        } catch (UnsupportedEncodingException e) {
            logger.error(null, e);
        }

        HttpResponse response = callViaHttpClient(httpPost);

        return response;
    }

    /**
     * @param httpUriRequest
     * @return
     */
    protected HttpResponse callViaHttpClient(HttpUriRequest httpUriRequest) {
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
            cookieStore = ((DefaultHttpClient) httpClient).getCookieStore();
            logger.trace(cookieStore);
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
    public Document post4Document(String... params) {
        String result = post4String(params);
        Document resultDoc = Utils.convertStr2Dom(result);

        return resultDoc;
    }

    public String post4String(String... params) {
        HttpResponse response = this.post(params);
        HttpEntity responseEntity = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(responseEntity);
        } catch (IOException e) {
            logger.error(null, e);
        }
        logger.info(result);

        return result;
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
    public void addParam(String paramName, Object paramValue) {
        if (noParam(paramName)) {
            this.parameters.add(new BasicNameValuePair(paramName, String.valueOf(paramValue)));
        }
    }

    private boolean noParam(String paramName) {
        for (NameValuePair param : parameters) {
            if (param.getName().equals(paramName)) {
                return false;
            }
        }

        return true;
    }

    /**
     * This method must call before method "call".
     *
     * @return cookie store.
     */
    @Override
    public void setCookieStore(CookieStore cookieStore) {
        if (null == httpClient) {
            Assert.notNull(this.serverURL);
            httpClient = Utils.getHttpClient(serverURL);
        }

        if (null != httpClient
                && httpClient instanceof DefaultHttpClient) {
            ((DefaultHttpClient) httpClient).setCookieStore(cookieStore);
            logger.trace("Set cookie successfully! " + cookieStore.toString());
        }

        return;
    }


    /**
     * This method must call after method "call".
     *
     * @return cookie store.
     */
    @Override
    public CookieStore getCookieStore() {
/*        CookieStore cookieStore;

        if (null != httpClient
                && httpClient instanceof DefaultHttpClient) {
            cookieStore = ((DefaultHttpClient) httpClient).getCookieStore();
        } else {
            cookieStore = new BasicCookieStore();
        }
*/
        return cookieStore;
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

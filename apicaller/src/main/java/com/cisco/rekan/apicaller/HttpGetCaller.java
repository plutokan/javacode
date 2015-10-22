/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * Building D1, Innovation Park, 800 Wangjiang Xi Road, High-tech Zone, Hefei City,
 * Anhui Province, China All rights reserved.
 */
package com.cisco.rekan.apicaller;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

/**
 * <code>HttpGetCaller</code>
 *
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since apicaller Oct 21, 2015
 *
 */
public class HttpGetCaller {

    /**
     * logger of log4j 1.x.
     */
    protected static Logger logger = Logger.getLogger(HttpGetCaller.class);

    /**
     * the caller.
     */
    private HttpClient httpClient = Utils.getHttpsClient();

    /**
     * the http get.
     */
    private HttpGet httpGet = null;

    public HttpGetCaller(String url) {
        httpGet = new HttpGet(url);
    }

    public final HttpResponse get() {

        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            logger.info(response.getStatusLine());
        } catch (IOException e) {
            logger.error(null, e);
        }
        return response;

    }

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
     * This method must call after method "call".
     *
     * @return cookie store.
     */
    public final CookieStore getCookieStore() {
        CookieStore cookieStore = ((DefaultHttpClient) httpClient).getCookieStore();

        return cookieStore;
    }

}

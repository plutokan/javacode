/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */

package com.cisco.rekan.apicaller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.util.Assert;


/**
 * <code>HttpAPICaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Mar 21, 2014
 *
 */
public abstract class HttpAPICaller implements IAPICaller {

    /** The server url. */
    private String serverURL = "";

    /** The parameters. */
    private List<NameValuePair> parameters = new ArrayList<NameValuePair>();

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
     * @see com.cisco.rekan.apitest.IAPICaller#callAPI(java.lang.String[])
     */
    @Override
    public Document callAPI(String... params) {

        String url = this.serverURL;
        HttpClient httpclient = Utils.getHttpClient(url);

        this.addParams(params);
        HttpPost httpPost = new HttpPost(url);

        Document resultDoc = null;
        try {
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(this.parameters);
            httpPost.setEntity(formEntity);
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            String result = EntityUtils.toString(responseEntity);
            System.out.println(result);

            resultDoc = Utils.convertStr2Dom(result);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultDoc;
    }

    /**
     * Call get api.
     *
     * @param params the params
     */
    protected void callGetAPI(String... params) {

        String url = this.serverURL;
        HttpClient httpclient = Utils.getHttpClient(url);

        this.addParams(params);
        StringBuffer queryStr = new StringBuffer();
        for (NameValuePair param : this.parameters) {
            queryStr.append('&');
            queryStr.append(param.getName());
            queryStr.append('=');
            queryStr.append(param.getValue());
        }
        String getStr = queryStr.toString().replaceFirst("&", "?");
        getStr = url + getStr;
        HttpGet httpGet = new HttpGet(getStr);

        try {
            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity responseEntity = response.getEntity();
            String result = EntityUtils.toString(responseEntity);
            System.out.println(result);

        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }

    /**
     * Adds the parms.
     *
     * @param params the params
     * @return
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
    protected void clear() {
        this.serverURL = "";
        this.parameters.clear();
    }

    /**
     * Assert xml result.
     *
     * @param dom the dom
     * @param expectedResult the expected result
     */
    public static void assertXMLResult(Document dom, String nodePath, String expectedResult) {
        Element element = (Element) dom.selectSingleNode(nodePath);
        Assert.notNull(element);
        String result = element.getText();
        assertEquals(expectedResult, result);
    }

}

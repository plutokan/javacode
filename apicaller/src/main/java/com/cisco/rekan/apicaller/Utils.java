/*
 * Copyright (C) Cisco Systems(China)Research and Development Co., Ltd. Hefei
 * Branch Office No. 308 Xiangzhang Drive, Hefei New and High Technology Area,
 * Hefei, Anhui, China All rights reserved.
 */

package com.cisco.rekan.apicaller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.CharEncoding;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.Assert;

/**
 * <code>Utils</code>
 * 
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Oct 27, 2013
 */
public final class Utils {

    protected static Logger logger = Logger.getLogger(Utils.class);

    private Utils() {
    }

    /**
     * Convert str2 dom.
     * 
     * @param xmlStr
     *            the xml str
     * @return the document
     */
    public final static Document convertStr2Dom(final String xmlStr) {
        Assert.notNull(xmlStr);
        StringReader reader = new StringReader(xmlStr);
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(reader);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return document;
    }

    /**
     * Gets the http client.
     *
     * @param url the url
     * @return the http client
     */
    public static HttpClient getHttpClient(String url) {
        HttpClient httpclient = null;

        if (url.startsWith("https://")) {
            httpclient = Utils.getHttpsClient();
        } else if (url.startsWith("http://")) {
            httpclient = Utils.getHttpClient();
        } else {
            throw new RuntimeException("Not supported protocol.");
        }

        return httpclient;
    }

    /**
     * Gets the http client.
     *
     * @return the http client
     */
    public static HttpClient getHttpClient() {
//        HttpClient httpclient = HttpClients.createDefault();
        HttpClient httpclient = new DefaultHttpClient();

        return httpclient;
    }

    /**
     * Gets the https client.
     *
     * @return the https client
     */
    public static HttpClient getHttpsClient() {
        HttpClient httpclient = getHttpClient();
        httpclient = WebClientDevWrapper.wrapClient(httpclient);

        return httpclient;
    }

    /**
     * 
     * @author shipengzhi(shipengzhi@sogou-inc.com)
     */
    public static class WebClientDevWrapper {

        @SuppressWarnings("deprecation")
		public static org.apache.http.client.HttpClient wrapClient(org.apache.http.client.HttpClient base) {
            try {
                SSLContext ctx = SSLContext.getInstance("SSLv3");
                X509TrustManager tm = new X509TrustManager() {

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                    }

                };
                ctx.init(null, new TrustManager[] { tm }, null);
                SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                SchemeRegistry registry = new SchemeRegistry();
                registry.register(new Scheme("https", 443, ssf));
                registry.register(new Scheme("http", 80, ssf));
                ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager(registry);

                return new DefaultHttpClient(mgr, base.getParams());
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }

    /**
     * Assert xml result.
     *
     * @param dom the dom
     * @param nodePath the node full path in document, like "/root/Meeting/...".
     * @param expectedResult the expected result
     */
    public static void assertXMLResult(Document dom, String nodePath, String expectedResult) {
        Element element = (Element) dom.selectSingleNode(nodePath);
        Assert.notNull(element);
        String result = element.getText();
        assertEquals(expectedResult, result);
    }

    /**
     * Parse the URI string to the name value pair list.
     *
     * @param uri URL like {@literal "http://www.example.com/something.html?one=1&two=2&three=3&three=3a"}.
     * @return {@link java.util.List} name value pair list.
     * @throws URISyntaxException If the parameter don't match the URI format.
     */
    private static List<NameValuePair> parseURI(String uri) throws URISyntaxException {
        Assert.notNull(uri);
        List<NameValuePair> params = URLEncodedUtils.parse(new URI(uri), CharEncoding.UTF_8);

        if (CollectionUtils.isNotEmpty(params)) {
            for (NameValuePair param : params) {
                logger.info(param.getName() + " : " + param.getValue());
            }
        }

        return params;
    }

    public static String getParamValue4URI(String uri, String paramName) {
        Assert.notNull(paramName);

        List<NameValuePair> params = null;
        try {
            params = parseURI(uri);
        } catch (URISyntaxException e) {
            logger.error(null, e);;
        }

        String paramValue = null;
        if (CollectionUtils.isNotEmpty(params)) {
            for (NameValuePair param : params) {
                if (paramName.equals(param.getName())) {
                    paramValue = param.getValue();
                    break;
                }
            }
        }
        return paramValue;
    }

    /**
     * @param response HttpResponse.
     */
    public static void printContent(HttpResponse response) {
        HttpEntity responseEntity = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(responseEntity);
        } catch (IOException e) {
            logger.error(null, e);
        }
        logger.info(result);
    }

}

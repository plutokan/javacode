/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */

package com.cisco.rekan.apicaller.urlapi;

import org.apache.http.HttpCoreUtils;
import org.apache.http.HttpResponse;
import org.jsoup.JsoupUtils;
import org.springframework.util.Assert;

import com.cisco.rekan.apicaller.AbstractHttpCaller;
import com.cisco.rekan.apicaller.HttpPostCaller;
import com.cisco.rekan.apicaller.ICaller;
import com.cisco.rekan.apicaller.IHttpCaller;


/**
 * <code>AbstractURLAPICaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Mar 21, 2014
 *
 */
public abstract class AbstractURLAPICaller extends AbstractHttpCaller {

    private String scheme = Constants.PROTOCOL_HTTPS;
    private String domainURL = "t30sp.qa.webex.com";
//    private String domainURL = "hm1web4.qa.webex.com";
//    private String domainURL = "t30ptcc.qa.webex.com";
//    private String domainURL = "m1aweb3s2.qa.webex.com";
    private String siteName = "t30sp";

    /**
     * @return the scheme
     */
    protected String getScheme() {
        return scheme;
    }

    /**
     * @return the domainURL
     */
    protected String getDomainURL() {
        return domainURL;
    }

    /**
     * @return the siteName
     */
    protected String getSiteName() {
        return siteName;
    }

    /**
     * Gets the php name.
     *
     * @return the php name
     */
    protected abstract String getPhpName();

    public AbstractURLAPICaller() {
        super();

        super.setServerURL(this.setupURI());
    }

    /**
     * @param scheme
     * @param domainURL
     * @param siteName
     */
    public AbstractURLAPICaller(String scheme, String domainURL, String siteName) {
        super();
        this.scheme = scheme;
        this.domainURL = domainURL;
        this.siteName = siteName;

        super.setServerURL(this.setupURI());
    }

    /**
     * Used by {@link com.cisco.rekan.apicaller.urlapi.w.COCaller#get()},
     * {@link com.cisco.rekan.apicaller.urlapi.w.COCaller#post()}.
     * <pre>
     * 1. Parse the relative path from "text/html".
     * 2. Setup the uri with "https://" + domain name + relative path.
     * 3. Post the uri with {@link com.cisco.rekan.apicaller.HttpPostCaller#post(String...)}.
     * 4. Validate the return response. The cookies must have "tiket".
     * </pre>
     *
     * @param src HttpResponse.
     * @return HttpResponse.
     */
    protected HttpResponse redirectViaJsp(HttpResponse src) {

        String url = JsoupUtils.parseLocationHref4JsScript(src, super.getServerURL());
        if (null == url) {
            return null;
        }
        if (url.startsWith("/")) {
            url = this.scheme + "://" + this.domainURL + url;
        }
        logger.info(url);

        ICaller<HttpResponse> caller = new HttpPostCaller(url);
        ((IHttpCaller) caller).setCookieStore(getCookieStore());
        HttpResponse response2 = caller.call();
        String ticket = HttpCoreUtils.getCookieValue(getCookieStore(), "ticket");
        org.junit.Assert.assertNotNull(ticket);
        logger.info("Redirect successfully, tiket in cookie: " + ticket);

        return response2;
    }

    private String setupURI() {
        Assert.notNull(this.scheme);
        Assert.notNull(this.domainURL);
        Assert.notNull(this.siteName);

        return this.scheme + "://" + this.domainURL + "/" + this.siteName + "/" + this.getPhpName();
    }

    protected HttpResponse get2post(HttpResponse src) {

        String url = JsoupUtils.parsePostUrlFromJS(src, super.getServerURL());
        if (null == url) {
            return null;
        }
        if (url.startsWith("/")) {
            url = this.scheme + "://" + this.domainURL + url;
        }
        logger.info(url);

        ICaller<HttpResponse> caller = new HttpPostCaller(url);
        ((IHttpCaller) caller).setCookieStore(getCookieStore());
        HttpResponse response2 = caller.call();
        String ticket = HttpCoreUtils.getCookieValue(getCookieStore(), "ticket");
        org.junit.Assert.assertNotNull(ticket);
        logger.info("Redirect successfully, tiket in cookie: " + ticket);

        return response2;
    }

}

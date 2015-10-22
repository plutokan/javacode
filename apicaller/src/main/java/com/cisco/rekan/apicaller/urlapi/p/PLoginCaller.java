/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * Building D1, Innovation Park, 800 Wangjiang Xi Road, High-tech Zone, Hefei City,
 * Anhui Province, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.p;

import org.apache.http.HttpCoreUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.log4j.Logger;
import org.jsoup.JsoupUtils;
import org.junit.Test;
import org.springframework.util.Assert;

import com.cisco.rekan.apicaller.HttpGetCaller;
import com.cisco.rekan.apicaller.IHttpCaller;
import com.cisco.rekan.apicaller.Utils;
import com.cisco.rekan.apicaller.urlapi.AbstractURLAPITest;

/**
 * <code>PLoginCaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since apicaller Sep 30, 2015
 *
 */
public class PLoginCaller extends AbstractURLAPITest {

    private String wid;
    private String pw;
    private CookieStore cookieStore;
    static Logger logger = Logger.getLogger(PLoginCaller.class);

    /**
     * Constructor.
     */
    public PLoginCaller() {
        super();
    }

    /* (non-Javadoc)
     * @see com.cisco.rekan.apicaller.urlapi.AbstractURLAPITest#getPhpName()
     */
    @Override
    protected String getPhpName() {
        return "p.php";
    }

    /* (non-Javadoc)
     * @see com.cisco.rekan.apicaller.HttpAPICaller#addParams(java.lang.String[])
     */
    @Override
    public void addParams(String... params) {
        super.addParam("AT", "LI");
        Assert.notNull(wid);
        super.addParam("WID", wid);
        super.addParam("PW", pw);

        super.addParam("MU", "GoBack");
    }

    
    /**
     * Call this method to login WebEx site.
     * <pre>
     * {@literal
https://pluto.qa.webex.com/pluto/p.php?AT=LI&WID=pluto&PID=jpd0rPXB47rTGvrlW1KimA&PW=P%40ss1234&MU=GoBack
RESPONSE: https://pluto.qa.webex.com/mw3100/mywebex/default.do?siteurl=pluto&AT=LI&WID=pluto&ST=SUCCESS&CSRF=c652e364-4971-4295-8bf9-73a2b85e9a69
     * }
     * </pre>
     * @param wid WebEx user ID.
     * @param password WebEx user password.
     * @return String CSRF string.
     */
    public HttpResponse login(String wid, String password) {
        this.setWid(wid);
        this.setPw(password);
        HttpResponse response = super.post();
//        Utils.printHeaders(response);
//      CookieStore cookieStore = HttpCoreUtils.parseCookie4Headers(response, super.getDomainURL());
        logger.trace(super.getCookieStore());

        String url = JsoupUtils.parseLocationHref4JsScript(response, super.getServerURL());
        logger.info(url);
        String result = Utils.getParamValue4URI(url, "ST");
        org.junit.Assert.assertEquals("p.php login failed!", "SUCCESS", result);

        IHttpCaller mwCaller = new HttpGetCaller(url);
        mwCaller.setCookieStore(super.getCookieStore());
        HttpResponse response2 = mwCaller.get();
//        Utils.printHeaders(response2);
        this.cookieStore = mwCaller.getCookieStore();
        logger.trace(this.getCookieStore());
        String ticket = HttpCoreUtils.getCookieValue(this.getCookieStore(), "ticket");
        org.junit.Assert.assertNotNull(ticket);
        logger.info(ticket);

        return response2;
    }

    @Test
    public void testLogin() {
        PLoginCaller caller = new PLoginCaller();
        caller.login("pluto", "P@ss1234");
    }

    /**
     * @return the wid
     */
    protected String getWid() {
        return wid;
    }

    /**
     * @param wid the wid to set
     */
    protected void setWid(String wid) {
        this.wid = wid;
    }

    /**
     * @return the pw
     */
    protected String getPw() {
        return pw;
    }

    /**
     * @param pw the pw to set
     */
    protected void setPw(String pw) {
        this.pw = pw;
    }

    /**
     * @return the cookieStore
     */
    @Override
    public CookieStore getCookieStore() {
        return cookieStore;
    }

}

/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * Building D1, Innovation Park, 800 Wangjiang Xi Road, High-tech Zone, Hefei City,
 * Anhui Province, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.w;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.junit.Test;

import com.cisco.rekan.apicaller.Utils;
import com.cisco.rekan.apicaller.urlapi.AbstractURLAPICaller;
import com.cisco.rekan.apicaller.urlapi.URLAPIUtils;
import com.cisco.rekan.apicaller.urlapi.p.PLoginCaller;

/**
 * <code>COCaller</code>
 *
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since apicaller Oct 12, 2015
 *
 */
public class COCaller extends AbstractURLAPICaller {

    private String bu = "www.baidu.com";
    private String csrf = null;

    /**
     * @return the bu
     */
    protected String getBu() {
        return bu;
    }

    /**
     * @param bu the bu to set
     */
    protected void setBu(String bu) {
        this.bu = bu;
    }

    /**
     * @return the csrf
     */
    protected String getCsrf() {
        return csrf;
    }

    /**
     * @param csrf the csrf to set
     */
    protected void setCsrf(String csrf) {
        this.csrf = csrf;
    }

    /* (non-Javadoc)
     * @see com.cisco.rekan.apicaller.urlapi.AbstractURLAPITest#getPhpName()
     */
    @Override
    protected String getPhpName() {
        return "w.php";
    }

    // TODO
    public HttpResponse createOneClickMeeting() {
        return get();
    }

    public HttpResponse get() {
        HttpResponse response = super.get(csrf);
        Utils.printHeaders(response);
        logger.debug(super.getCookieStore());

        HttpResponse response2 = super.redirectViaJsp(response);
        return response2;
    }

    public HttpResponse post() {
        HttpResponse response = super.post(csrf);
        Utils.printHeaders(response);
        logger.debug(super.getCookieStore());

        HttpResponse response2 = super.redirectViaJsp(response);
        return response2;
    }

    /**
     * E.g. 
     * {@literal
     * w.php?AT=CO&BU=www.baidu.com&CSRF=d7b7890c-edff-40ba-987f-01592e32b178
     * }
     *
     * @param params the parameters like example.
     */
    @Override
    public void addParams(String... params) {
        super.addParam("AT", "CO");

        if (params.length >= 1) {
            csrf = params[0];
        }
        if (StringUtils.isNotEmpty(bu)) {
            super.addParam("BU", bu);
        }
        if (StringUtils.isNotEmpty(csrf)) {
            super.addParam("CSRF", csrf);
        }
    }

    @Test
    public void testCreate() {
        PLoginCaller loginCaller = new PLoginCaller();
        loginCaller.login("pluto", "P@ss1234");
        CookieStore cookieStore = loginCaller.getCookieStore();

        COCaller coCaller = new COCaller();
        coCaller.setCookieStore(cookieStore);
        coCaller.setCsrf(URLAPIUtils.getCsrf(cookieStore));
        HttpResponse response = coCaller.get();
        Utils.printContent(response);
    }

}

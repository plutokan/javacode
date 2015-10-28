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
 * <code>SOCaller</code>, setup one-click meeting.
 *
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since apicaller Oct 12, 2015
 *
 */
public class SOCaller extends AbstractURLAPICaller {

    private String bu = "www.baidu.com";
    private String pw = "111111";
    private String tp = "OneClick+with+CSRF-MC";
    private String sp = "3";
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
     * @return the tp
     */
    protected String getTp() {
        return tp;
    }

    /**
     * @param tp the tp to set
     */
    protected void setTp(String tp) {
        this.tp = tp;
    }

    /**
     * @return the sp
     */
    protected String getSp() {
        return sp;
    }

    /**
     * @param sp the sp to set
     */
    protected void setSp(String sp) {
        this.sp = sp;
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

    /**
     * E.g. 
     * {@literal
     * w.php?AT=SO&PW=111111&BU=www.baidu.com&TP=OneClick+with+CSRF-MC&SP=3
     *     &CSRF=d7b7890c-edff-40ba-987f-01592e32b178
     * }
     *
     * @param params the parameters like example.
     */
    @Override
    public void addParams(String... params) {
        super.addParam("AT", "SO");
        if (params.length >= 1) {
            csrf = params[0];
        }

        if (StringUtils.isNotEmpty(bu)) {
            super.addParam("BU", bu);
        }
        if (StringUtils.isNotEmpty(pw)) {
            super.addParam("PW", pw);
        }
        if (StringUtils.isNotEmpty(tp)) {
            super.addParam("TP", tp);
        }
        if (StringUtils.isNotEmpty(sp)) {
            super.addParam("SP", sp);
        }
        if (StringUtils.isNotEmpty(csrf)) {
            super.addParam("CSRF", csrf);
        }
    }

    @Test
    public void testSO() {
        PLoginCaller loginCaller = new PLoginCaller();
        loginCaller.login("pluto", "P@ss1234");
        CookieStore cookieStore = loginCaller.getCookieStore();

//        {
//        SOCaller soCaller = new SOCaller();
//        soCaller.setCsrf(URLAPIUtils.getCsrf(cookieStore));
//        HttpResponse response = soCaller.get();
//        Utils.printHeaders(response);
//        Utils.printCookies(soCaller.getCookieStore());
//        Utils.printContent(response);
//        }

        {
        SOCaller soCaller2 = new SOCaller();
        soCaller2.setCsrf(URLAPIUtils.getCsrf(cookieStore));
        soCaller2.setCookieStore(cookieStore);
        HttpResponse response2 = soCaller2.post();
        Utils.printHeaders(response2);
        Utils.printCookies(soCaller2.getCookieStore());
        Utils.printContent(response2);
        }
    }

}

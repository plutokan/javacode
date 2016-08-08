/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.user;

import org.apache.http.HttpResponse;
import org.dom4j.Document;
import org.dom4j.Element;
import org.junit.Assert;
import org.junit.Test;

import com.cisco.rekan.apicaller.Utils;
import com.cisco.rekan.apicaller.urlapi.AbstractURLAPICaller;

/**
 * <code>LoginCaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 8, 2016
 *
 */
public class LoginCaller extends AbstractURLAPICaller {

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.urlapi.AbstractURLAPITest#getPhpName()
     */
    @Override
    public String getPhpName() {
        return "user.php";
    }

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.HttpAPICaller#addParams(java.lang.String[])
     */
    @Override
    public void addParams(String... params) {
        // ?AT=GetAuthInfo&UN=pluto&PW=P@ss123&isUTF8=1&getEncryptedPwd=true
        super.addParam("AT", "Login");
        super.addParam("UN", params[0]);
        super.addParam("PWPW", params[1]);
        super.addParam("isUTF8", "1");
    }

    /**
     * @param userID
     * @param password
     * @return
     */
    public static String getSessionTicket(final String userID, final String password) {
        LoginCaller caller = new LoginCaller();
        
        Document dom = caller.post4Document(userID, password);

        Element e1 = (Element) dom.selectSingleNode("//AuthInfo/Result");
        Assert.assertEquals("SUCCESS", e1.getText());

        Element e2 = (Element) dom.selectSingleNode("//AuthInfo/SessionTicket");
        String sessionTicket = e2.getText();
        System.out.println(sessionTicket);

        return sessionTicket;
    }

    @Test
    public void testGet() {
        HttpResponse response = super.get("pluto", "P@ss12345");
        Utils.printContent(response);
    }

    @Test
    public void testPost() {
        HttpResponse response = super.post("pluto", "P@ss12345");
        Utils.printContent(response);
    }

    @Test
    public void testGetSK() {
        String sk = getSessionTicket("pluto", "P@ss123");
        System.out.println(sk);
    }

}

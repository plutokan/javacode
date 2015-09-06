/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.datemeeting;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import junit.framework.Assert;

import org.dom4j.Document;
import org.dom4j.Element;
import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPITest;

/**
 * <code>RegistrationCaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 27, 2014
 *
 */
public class RegistrationCaller extends AbstractURLAPITest {

    /**
     * Instantiates a new registration caller.
     */
    public RegistrationCaller() {
        super();
    }

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.HttpAPICaller#addParams(java.lang.String[])
     */
    @Override
    public void addParams(String... params) {
        super.addParam("AT", "Registration");

        super.addParam("UN", params[0]);
        super.addParam("PW", params[1]);

        super.addParam("isUTF8", "1");
    }

    @Test
    public void testRegister() {
        RegistrationCaller caller = new RegistrationCaller();
        caller.register("pluto", "P@ss123");
    }

    /**
     * Register.
     *
     * @param userName the user name
     * @param password the password
     * @return the string
     */
    public String register(String userName, String password) {
        // datameeting.php?AT=Registration&UN=admin&PW=P@ss123
        Document dom = super.callAPI(userName, password);

        Element e1 = (Element) dom.selectSingleNode("//registrationResp/status");
        Assert.assertEquals("SUCCESS", e1.getText());

        Element e2 = (Element) dom.selectSingleNode("//registrationResp/token");
        String token = e2.getText();
        System.out.println(token);

        return token;
    }

    /**
     * Gets the uRL encode token.
     *
     * @param userName the user name
     * @param password the password
     * @return the uRL encode token
     */
    public String getURLEncodeToken(String userName, String password) {
        String token = this.register(userName, password);
        try {
            token = URLEncoder.encode(token, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
        }
        System.out.println("URL encoded token: " + token);

        return token;
    }

    @Override
    public String getPhpName() {
        return "/datameeting.php";
    }

}

/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.user;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.cisco.rekan.apicaller.Utils;
import com.cisco.rekan.apicaller.urlapi.AbstractURLAPICaller;

/**
 * <code>UserGetAuthInfoCaller</code>
 *
http://deweb2.qa.webex.com/dewd-pluto/user.php?AT=GetAuthInfo&UN=pluto&PW=P@ss123&isUTF8=1&getEncryptedPwd=true
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 22, 2014
 *
 */
public class UserGetAuthInfoCaller extends AbstractURLAPICaller {

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
        super.addParam("AT", "GetAuthInfo");
        super.addParam("UN", params[0]);
        super.addParam("PW", params[1]);
        super.addParam("isUTF8", "1");
        super.addParam("getEncryptedPwd", "true");
    }

    /**
     * Clients call this API to get the encrypted password, and store in the clients.
     * Next time, when user need login to AS (get the session ticket), call GetAuthInfo with the parameter like "EPW".
     *
     * @param userID WebEx user ID.
     * @param password user password.
     * @return the encrypted password to store in clients.
     */
    public static String getEPW(final String userID, final String password) {
        UserGetAuthInfoCaller caller = new UserGetAuthInfoCaller();
        HttpResponse response = caller.post(userID, password);

        HttpEntity responseEntity = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(responseEntity);
        } catch (IOException e) {
            logger.error(null, e);
        }

        return result;
    }

    @Test
    public void testGet() {
        HttpResponse response = super.get("pluto", "P@ss1234");
        Utils.printContent(response);
    }

    @Test
    public void testPost() {
        String sk = getEPW("pluto", "P@ss123");
        System.out.println(sk);
    }

}

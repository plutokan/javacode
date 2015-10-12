/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.user;

import org.apache.http.HttpResponse;
import org.junit.Test;

import com.cisco.rekan.apicaller.Utils;
import com.cisco.rekan.apicaller.urlapi.AbstractURLAPITest;

/**
 * <code>UserGetAuthInfoCaller</code>
 *
http://deweb2.qa.webex.com/dewd-pluto/user.php?AT=GetAuthInfo&UN=pluto&PW=P@ss123&isUTF8=1&getEncryptedPwd=true
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 22, 2014
 *
 */
public class UserGetAuthInfoCaller extends AbstractURLAPITest {

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

    @Test
    public void test() {
        HttpResponse response = super.getAPI("pluto", "P@ss123");
        Utils.printContent(response);
    }

}

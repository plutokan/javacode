/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.nobrowser;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPITest;
import com.cisco.rekan.apicaller.urlapi.DocshowParser;
import com.cisco.rekan.apicaller.urlapi.datemeeting.RegistrationCaller;
import com.cisco.rekan.apicaller.urlapi.m.MCaller;

/**
 * <code>HMCaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 27, 2014
 *
 */
public class HMCaller extends AbstractURLAPITest {

    private static final String USER_NAME = "pluto";
    private static final String USER_PASSWORD = "P@ss1234";
    static Logger logger = Logger.getLogger(HMCaller.class);

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.urlapi.AbstractURLAPITest#getPhpName()
     */
    @Override
    public String getPhpName() {
        return "nobrowser.php";
    }

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.HttpAPICaller#addParams(java.lang.String[])
     */
    @Override
    public void addParams(String... params) {
        // nobrowser.php?AT=HM&MK=017170569&DocshowVer=1.0&FeatureSupport=2&OS=iPhone&isUTF8=1&IT=15&VER=6%2E0
        super.addParam("AT", "HM");

        super.addParam("MK", StringUtils.deleteWhitespace(params[0]));
        super.addParam("WUN", USER_NAME);
//        super.addParam("PWPW", USER_PASSWORD);
//        super.addParam("PPW", USER_PASSWORD);

        super.addParam("EM", "rekan@cisco.com");
        super.addParam("DN", "steady wang");

        super.addParam("DocshowVer", "2.0");
        super.addParam("FeatureSupport", "2");
        super.addParam("OS", "iPhone");
        super.addParam("isUTF8", "1");
        super.addParam("IT", "15");
        super.addParam("VER", "6.0");
    }

    @Test
    public void testMC() throws IOException {
        RegistrationCaller loginCaller = new RegistrationCaller();
        String token = loginCaller.register(USER_NAME, USER_PASSWORD);
        super.addParam("SK", token);

        Document docshow = super.callPostAPI("213 627 366");
        docshow = DocshowParser.getClientparam(docshow);

        DocshowParser.printVideoAddresses(docshow);
    }

    @Test
    public void testMC2() {
        MCaller mCaller = new MCaller();
        HttpClient httpClient = mCaller.getHttpClient();
        super.setHttpClient(httpClient);
        super.callPostAPI("210 493 470");
    }

    @Test
    public void testEC() throws IOException {
        RegistrationCaller loginCaller = new RegistrationCaller();
        String token = loginCaller.register(USER_NAME, USER_PASSWORD);
        super.addParam("SK", token);

        Document docshow = super.callPostAPI("159 546 916");
        docshow = DocshowParser.getClientparam(docshow);
        System.out.println(docshow.toString());
        System.out.println("------------------------");

        System.out.println("@" + DocshowParser.getNodeContent(docshow, "//root/User/UserID") + "@");
        System.out.println("@" + DocshowParser.getNodeContent(docshow, "//root/User/UserEmail") + "@");
        String userName = DocshowParser.getNodeContent(docshow, "//root/User/UserName");
        System.out.println("@" + new String(Base64.decodeBase64(userName)) + "@");
    }

    @Test
    public void testTC() throws IOException {
        RegistrationCaller loginCaller = new RegistrationCaller();
        String token = loginCaller.register(USER_NAME, USER_PASSWORD);
        super.addParam("SK", token);

        Document docshow = super.callPostAPI("155 334 976", USER_NAME);
        docshow = DocshowParser.getClientparam(docshow);

        String uploadURL = DocshowParser.getUploadURL(docshow);
        System.out.println(uploadURL);
    }

}

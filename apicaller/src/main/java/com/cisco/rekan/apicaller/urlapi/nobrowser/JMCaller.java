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
import org.dom4j.Document;
import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPITest;
import com.cisco.rekan.apicaller.urlapi.DocshowParser;
import com.cisco.rekan.apicaller.urlapi.datemeeting.RegistrationCaller;

/**
 * <code>HMCaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 27, 2014
 *
 */
public class JMCaller extends AbstractURLAPITest {

    private static final String USER_NAME = "pluto4";
    private static final String USER_PASSWORD = "P@ss123";

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
        super.addParam("AT", "JM");
        super.addParam("MK", StringUtils.deleteWhitespace(params[0]));
        super.addParam("WUN", USER_NAME);
        super.addParam("ST", "6");  // TC - 7, EC - 6.
        super.addParam("PPW", "");
        super.addParam("PWPW", USER_PASSWORD);

        super.addParam("EM", "rekan@cisco.com");
        super.addParam("DN", "steady wang");

        super.addParam("DocshowVer", "1.0");
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

        Document docshow = super.callPostAPI("150 334 765", USER_NAME);
        docshow = DocshowParser.getClientparam(docshow);
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

        Document docshow = super.callPostAPI("157 322 706", USER_NAME);
        docshow = DocshowParser.getClientparam(docshow);

        String uploadURL = DocshowParser.getUploadURL(docshow);
        System.out.println(uploadURL);

    }

}

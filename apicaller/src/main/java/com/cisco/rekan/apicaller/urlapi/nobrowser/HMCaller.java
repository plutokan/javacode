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
public class HMCaller extends AbstractURLAPITest {

    private static final String USER_NAME = "pluto";
    private static final String USER_PASSWORD = "P@ss1234";

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
        super.addParam("PPW", "P@ss123");

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

        Document docshow = super.callAPI("212 829 481", USER_NAME);
        docshow = DocshowParser.getClientparam(docshow);

        String encryptedConfID2 = DocshowParser.getNodeContent(docshow, "//root/Security/EncryptedConfID2");
        System.out.println("-----" + encryptedConfID2);
//        System.out.println("-----" + WebStringUtil.decrypt(encryptedConfID2));
    }

    @Test
    public void testEC() throws IOException {
        RegistrationCaller loginCaller = new RegistrationCaller();
        String token = loginCaller.register(USER_NAME, USER_PASSWORD);
        super.addParam("SK", token);

        Document docshow = super.callAPI("159 546 916");
        docshow = DocshowParser.getClientparam(docshow);
        System.out.println(docshow.toString());
        System.out.println("------------------------");

        System.out.println("@" + DocshowParser.getNodeContent(docshow, "//root/User/UserID") + "@");
        System.out.println("@" + DocshowParser.getNodeContent(docshow, "//root/User/UserEmail") + "@");
        String userName = DocshowParser.getNodeContent(docshow, "//root/User/UserName");
        System.out.println("@" + new String(Base64.decodeBase64(userName)) + "@");
        System.out.println("@" + new String(Base64.decodeBase64("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")) + "@");
    }

    @Test
    public void testTC() throws IOException {
        RegistrationCaller loginCaller = new RegistrationCaller();
        String token = loginCaller.register(USER_NAME, USER_PASSWORD);
        super.addParam("SK", token);

        Document docshow = super.callAPI("155 334 976", USER_NAME);
        docshow = DocshowParser.getClientparam(docshow);

        String uploadURL = DocshowParser.getUploadURL(docshow);
        System.out.println(uploadURL);
    }

}

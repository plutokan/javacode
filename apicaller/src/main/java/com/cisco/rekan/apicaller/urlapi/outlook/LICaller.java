/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.outlook;

import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPITest;

/**
 * <code>LICaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 25, 2014
 *
 */
public class LICaller extends AbstractURLAPITest {

    private static final String USER_NAME = "pluto";
    private static final String USER_PASSWORD = "P@ss123";
    private static final String USER_ENCRYPTED_PASSWORD = "2212d3586374481f0a5e40";
    private static final String USER_NEW_PASSWORD = "P@ssword123";
    private static final String USER_NEW_ENCRYPTED_PASSWORD = "49ad251d013d203b2b3835020076";

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.urlapi.AbstractURLAPITest#getPhpName()
     */
    @Override
    public String getPhpName() {
        return "outlook.php";
    }

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.HttpAPICaller#addParams(java.lang.String[])
     */
    @Override
    public void addParams(String... params) {
        super.addParam("AT", params[0]);
        super.addParam("WID", USER_NAME);

        super.addParam("NeedCheckPassword", "1");
        super.addParam("isUTF8", "1");
        super.addParam("AUAll", "1");
        super.addParam("WithMPS", "1");
        super.addParam("SupportAES", "1");
        super.addParam("PT", "1");
        super.addParam("BF", "1");
    }

    @Test
    public void testLI() {
        // outlook.php?AT=LI&WID=pluto&WPW=...
        super.addParam("WPW", USER_ENCRYPTED_PASSWORD);
        super.post4Document("LI");

        // outlook.php?AT=LI&WID=pluto&PWPW=P@ss123
        super.reset();
        super.addParam("PWPW", USER_PASSWORD);
        super.post4Document("LI");
    }

    @Test
    public void testLI_updatePwd() {
        // outlook.php?AT=LI&WID=pluto&WPW=...&NPW=...
        super.addParam("WPW", USER_ENCRYPTED_PASSWORD);
        super.addParam("NPW", USER_NEW_ENCRYPTED_PASSWORD);
        super.post4Document("LI");

        // outlook.php?AT=LI&WID=pluto&PWPW=...&PNPW=...
        super.reset();
        super.addParam("PWPW", USER_NEW_PASSWORD);
        super.addParam("PNPW", USER_PASSWORD);
        super.post4Document("LI");    }

}

/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.outlook;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPITest;
import com.cisco.rekan.apicaller.urlapi.Constants;

/**
 * <code>GMRSCaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 25, 2014
 *
 */
public class GMRSCaller extends AbstractURLAPITest {

    private static final String USER_NAME = "pluto";
    private static final String USER_PASSWORD = "P@ss123";
    private static final String USER_ENCRYPTED_PASSWORD = "2212d3586374481f0a5e40";

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.urlapi.AbstractURLAPITest#getDomainURL()
     */
    @Override
    public String getDomainURL() {
        return Constants.VSCM_DOMAIN_URL;
    }

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.urlapi.AbstractURLAPITest#getSiteName()
     */
    @Override
    public String getSiteName() {
        return Constants.VSCM_WEBEX_SITE_NAME;
    }

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
        super.addParam("AT", "GMRS");
        super.addParam("MK", StringUtils.deleteWhitespace(params[0]));

        super.addParam("WID", USER_NAME);

//        super.addParam("NeedCheckPassword", "1");
//        super.addParam("isUTF8", "1");
//        super.addParam("AUAll", "1");
//        super.addParam("WithMPS", "1");
//        super.addParam("SupportAES", "1");
//        super.addParam("PT", "1");
//        super.addParam("BF", "1");
    }

    @Test
    public void testGMRS() {
        // outlook.php?AT=LI&WID=pluto&WPW=...
        super.addParam("WPW", USER_ENCRYPTED_PASSWORD);
        super.addParam("ST", "6");
        super.callAPI("152 546 004");

        // outlook.php?AT=LI&WID=pluto&PWPW=P@ss123
        super.clear();
        super.addParam("PWPW", USER_PASSWORD);
        super.addParam("ST", "6");
        super.callAPI("152 546 004");
    }

}

/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.outlook;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPICaller;

/**
 * <code>GMRSCaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 25, 2014
 *
 */
public class GetMeetingCaller extends AbstractURLAPICaller {

    private static final String USER_NAME = "pluto";
    private static final String USER_PASSWORD = "P@ss1234";

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
        super.addParam("AT", "GetMeeting");
        super.addParam("MK", StringUtils.deleteWhitespace(params[0]));
        super.addParam("OS", "OI");

        super.addParam("WID", USER_NAME);
        super.addParam("PWPW", USER_PASSWORD);
    }

    @Test
    public void test() {
        super.post4Document("224 426 302 ");
    }

}

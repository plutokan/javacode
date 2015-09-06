/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.cm;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPITest;
import com.cisco.rekan.apicaller.urlapi.Constants;

/**
 * <code>ConnectMeetingCaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 14, 2014
 *
 */
public class ConnectMeetingCaller extends AbstractURLAPITest {

    @Override
    public String getDomainURL() {
        return Constants.VSCM_DOMAIN_URL;
    }

    @Override
    public String getSiteName() {
        return Constants.VSCM_WEBEX_SITE_NAME;
    }

    @Override
    public String getPhpName() {
        return "cm.php";
    }

    @Override
    public void addParams(String... params) {
        super.addParam("AT", params[0]);
        super.addParam("MK", params[1]);
    }

    @Test
    public void test() throws UnsupportedEncodingException {
        // https://deweb2.qa.webex.com/dewd-pluto/cm.php?AT=JM&MK=150631521
        super.callAPI("JM", "150631521");
    }

}

/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * Building D1, Innovation Park, 800 Wangjiang Xi Road, High-tech Zone, Hefei City,
 * Anhui Province, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi;

import org.junit.Assert;
import org.junit.Test;

import com.webex.webapp.common.exception.WbxAppTokenException;

/**
 * <code>AppTokenUtilTest</code>
 *
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since apicaller Apr 12, 2016
 *
 */
public class AppTokenUtilTest {

    @Test
    public void test() throws WbxAppTokenException {
        String appName = "APP_Tahoe";
        String appToken = AppTokenUtil.createAPPToken(appName);
        Assert.assertTrue(com.webex.webapp.common.util.security.AppTokenUtil.verifyTicket(appToken, appName));
    }

}

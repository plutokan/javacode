/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * Building D1, Innovation Park, 800 Wangjiang Xi Road, High-tech Zone, Hefei City,
 * Anhui Province, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi;

import org.apache.log4j.Logger;
import org.junit.Assert;

import com.webex.webapp.common.exception.WbxAppTokenException;

/**
 * <code>AppTokenUtil</code>
 *
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since apicaller Apr 12, 2016
 *
 */
public final class AppTokenUtil {

    protected static Logger logger = Logger.getLogger(AppTokenUtil.class);

    private AppTokenUtil() {
    }

    /**
     * @param appName
     * @return
     * @throws WbxAppTokenException
     */
    public static String createAPPToken(String appName) {

        String appToken = null;
        try {
            appToken = com.webex.webapp.common.util.security.AppTokenUtil.makeTicket2(appName);
        } catch (WbxAppTokenException e) {
            logger.error(appName, e);
            Assert.fail("Failed to get the app token of " + appName);
        }

        return appToken;
    }

}

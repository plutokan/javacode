/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.nobrowser;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPICaller;
import com.cisco.rekan.apicaller.urlapi.DocshowParser;
import com.cisco.rekan.apicaller.urlapi.user.LoginCaller;

/**
 * <code>EMCaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 8, 2016
 *
 */
public class EMCaller extends AbstractURLAPICaller {

    private static final String USER_NAME = "pluto";
    private static final String USER_PASSWORD = "P@ss123";
    static Logger logger = Logger.getLogger(EMCaller.class);

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
        // nobrowser.php?AT=EM&OS=iPhone&isUTF8=1&IT=15&WUN=...&MK=...&SK=...
        super.addParam("AT", "EM");

        super.addParam("MK", StringUtils.deleteWhitespace(params[0]));
        super.addParam("WUN", USER_NAME);

        super.addParam("OS", "iPhone");
        super.addParam("isUTF8", "1");
        super.addParam("IT", "15");
    }

    @Test
    public void testMC() throws IOException {
        String token = LoginCaller.getSessionTicket(USER_NAME, USER_PASSWORD);
        super.addParam("SK", token);

        Document result = super.post4Document("221 502 260");
        DocshowParser.assertSuccess(result);
        logger.log(Level.INFO, result.asXML());
    }

}

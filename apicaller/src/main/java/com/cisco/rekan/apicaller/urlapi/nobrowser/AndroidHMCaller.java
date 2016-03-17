/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.nobrowser;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPICaller;
import com.cisco.rekan.apicaller.urlapi.DocshowParser;
import com.cisco.rekan.apicaller.urlapi.datemeeting.RegistrationCaller;


/**
 * <code>AndroidHMCaller</code>
 *
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since apicaller Oct 28, 2015
 *
 */
public class AndroidHMCaller extends AbstractURLAPICaller {

    private static final String USER_NAME = "pluto";
    private static final String USER_PASSWORD = "P@ss1234";
    static Logger logger = Logger.getLogger(AndroidHMCaller.class);

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

        if (params.length > 0) {
            super.addParam("MK", StringUtils.deleteWhitespace(params[0]));
        }
        super.addParam("WUN", USER_NAME);

        super.addParam("EM", "rekan@cisco.com");
        super.addParam("DN", "Renhua Kan");

        super.addParam("ST", "1");
        super.addParam("SST", "1");
        super.addParam("MN", "Pluto's meeting");
        super.addParam("MPW", "111111");
        super.addParam("DU", "60");
        super.addParam("scheduled", "1");
        super.addParam("isScheduleOnly", "1");
        super.addParam("FeatureSupport", "3");
        super.addParam("OS", "Android");
        super.addParam("isUTF8", "1");
        super.addParam("IT", "22");
    }

    @Test
    public void schedule() throws IOException {
        RegistrationCaller loginCaller = new RegistrationCaller();
        String token = loginCaller.register(USER_NAME, USER_PASSWORD);
        super.addParam("SK", token);

        Document docshow = super.post4Document();
        logger.debug(docshow.asXML());
        DocshowParser.assertSuccess(docshow);
    }

}

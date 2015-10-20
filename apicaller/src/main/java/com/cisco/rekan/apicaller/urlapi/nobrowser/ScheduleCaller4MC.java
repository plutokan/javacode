/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * Building D1, Innovation Park, 800 Wangjiang Xi Road, High-tech Zone, Hefei City,
 * Anhui Province, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.nobrowser;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Node;
import org.junit.Assert;
import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPITest;
import com.cisco.rekan.apicaller.urlapi.Constants;
import com.cisco.rekan.apicaller.urlapi.datemeeting.RegistrationCaller;

/**
 * <code>ScheduleCaller4MC</code>
 *
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since apicaller Oct 20, 2015
 *
 */
public class ScheduleCaller4MC extends AbstractURLAPITest {

    private static final String USER_NAME = "pluto";
    private static final String USER_PASSWORD = "P@ss1234";
    static Logger logger = Logger.getLogger(ScheduleCaller4MC.class);

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
    }

    /**
     * CSCuw69997
     *
     * @throws IOException
     */
    @Test
    public void schedule() throws IOException {

        super.addParam("AT", "HM");
        super.addParam("WUN", USER_NAME);
        super.addParam("MN", "test meeting 002");
        super.addParam("MPW", "y3BsuC29");
        super.addParam("MDT", "10-21-2015 1:0");
        super.addParam("DU", "60");
        super.addParam("FeatureSupport", "3");
        super.addParam("scheduled", "1");
        super.addParam("OS", "iPhone");
        super.addParam("isUTF8", "1");
        super.addParam("IT", "15");
        super.addParam("VER", "8.5");

        RegistrationCaller loginCaller = new RegistrationCaller();
        loginCaller.setDomainURL(Constants.QA_SITE);
        loginCaller.setSiteName(Constants.QA_SITE_NAME);
        String token = loginCaller.register(USER_NAME, USER_PASSWORD);
        super.addParam("SK", token);

        this.setDomainURL(Constants.QA_SITE);
        this.setSiteName(Constants.QA_SITE_NAME);
        Document docshow = super.callPostAPI();
        Node statusNode = docshow.selectSingleNode("//MeetingData/Status");
        Assert.assertNotNull(statusNode);
        String status = statusNode.getText();
        Assert.assertEquals("SUCCESS", status);

    }

}

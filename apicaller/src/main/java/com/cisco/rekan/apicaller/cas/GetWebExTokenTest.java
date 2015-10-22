/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */

package com.cisco.rekan.apicaller.cas;

import static org.junit.Assert.assertEquals;

import org.dom4j.Document;
import org.dom4j.Element;
import org.junit.Test;
import org.springframework.util.Assert;


/**
 * <code>GetWebExTokenTest</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Nov 5, 2013
 *
 */
public class GetWebExTokenTest extends AbstractCASTest {

    @Override
    public void addParams(String... params) {
        super.addParam("cmd", "getwebextoken");
        super.addParam("username", params[0]);
        super.addParam("password", params[1]);
        super.addParam("isp", params[2]);
        super.addParam("service", params[3]);
    }

    @Test
    public void getwebextoken_JBR() {
        Document dom = new GetWebExTokenTest().callCAS("brook1@szc761.com",
                                                       "P@ss123",
                                                       "JBR",
                                                       null);
        Element element = (Element) dom.selectSingleNode("//LoginResponse/response/result");
        Assert.notNull(element);
        String result = element.getText();
        assertEquals("SUCCESS", result);
    }

    @Test
    public void getwebextoken_connect() {
        Document dom = new GetWebExTokenTest().callCAS("brook1@szc761.com",
                                                       "P@ss123",
                                                       null,
                                                       "connect");
        Element element = (Element) dom.selectSingleNode("//LoginResponse/response/result");
        Assert.notNull(element);
        String result = element.getText();
        assertEquals("SUCCESS", result);
    }

    /**
szv14epmeetonly.qa.webex.com
ciadmin@szv14epmeetonly.com

szv14efmeetingonly2.qa.webex.com
ciadmin@szv14efmeetingonly2.com
     */
    @Test
    public void getwebextoken_connect_EP() {
        Document dom = new GetWebExTokenTest().callCAS("ciadmin@szv14epmeetonly.com",
                                                       "P@ss123",
                                                       null,
                                                       "connect");
        assertXMLResultFailed(dom);
    }

    @Test
    public void getwebextoken_connect_EF_MeetOnly() {
        Document dom = new GetWebExTokenTest().callCAS("ciadmin@szv14efmeetingonly2.com",
                                                       "P@ssword123",
                                                       null,
                                                       "connect");
        assertXMLResultFailed(dom);
    }

}

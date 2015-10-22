/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */

package com.cisco.rekan.apicaller.cas;

import org.dom4j.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * <code>FederatedSSOTest</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Dec 2, 2013
 *
 */
public class FederatedSSOTest extends AbstractCASTest {

    @Override
    public void addParams(String... params) {
        super.addParam("org", params[0]);
        super.addParam("type", params[1]);
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setServerURL("https://10.79.159.62:444/cas/FederatedSSO.do");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void troubleShooting_1() {
        Document dom = super.callCAS("pfsso.com", "connect2");
        assertXMLResultFailed(dom);
    }

}

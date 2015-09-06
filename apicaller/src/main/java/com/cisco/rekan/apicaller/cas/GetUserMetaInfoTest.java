/*
 * Copyright (C) Cisco Systems(China)Research and Development Co., Ltd. Hefei
 * Branch Office No. 308 Xiangzhang Drive, Hefei New and High Technology Area,
 * Hefei, Anhui, China All rights reserved.
 */

package com.cisco.rekan.apicaller.cas;

import org.junit.After;
import org.junit.Before;

/**
 * <code>GetUserMetaInfoTest</code>
 * 
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Oct 27, 2013
 */
public class GetUserMetaInfoTest extends AbstractCASTest {

    @Override
    public void addParams(String... params) {
        super.addParam("cmd", "getusermetainfo");
        super.addParam("email", params[0]);
        super.addParam("token", params[1]);
        super.addParam("clientid", params[2]);
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setServerURL("https://10.79.159.62:444/cas/auth.do");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

}

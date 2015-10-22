/*
 * Copyright (C) Cisco Systems(China)Research and Development Co., Ltd. Hefei
 * Branch Office No. 308 Xiangzhang Drive, Hefei New and High Technology Area,
 * Hefei, Anhui, China All rights reserved.
 */

package com.cisco.rekan.apicaller.cas;

import org.dom4j.Document;
import org.junit.Test;

import com.webex.webapp.common.exception.WbxAppTokenException;
import com.webex.webapp.common.util.security.AppTokenUtil;

/**
 * <code>GenerateWebExTokenTest</code>
 * 
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Oct 27, 2013
 */
public class GenerateWebExTokenTest extends AbstractCASTest {

    @Override
    public void addParams(String... params) {
        super.addParam("cmd", "generateWebExToken");
        super.addParam("appname", params[0]);
        super.addParam("user", params[1]);

        String token = null;
        try {
//            token = URLEncoder.encode(AppTokenUtil.makeToken(params[0]));
//            System.out.println(URLDecoder.decode(token));
//            boolean isValid = AppTokenUtil.verifyToken(params[0], URLDecoder.decode(token));
//            Assert.isTrue(isValid);
            token = AppTokenUtil.makeURLSafeToken(params[0]);
        } catch (WbxAppTokenException e2) {
            e2.printStackTrace();
        }
        super.addParam("token", token);
    }

    @Test
    public void generatewebextoken() {
        Document dom = new GenerateWebExTokenTest().callCAS(Constants.APPNAME_CI,
                                                            Constants.USER_NORMAL);
        assertXMLResultSuccess(dom);
    }

}

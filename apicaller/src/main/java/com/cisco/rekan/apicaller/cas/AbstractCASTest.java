/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */

package com.cisco.rekan.apicaller.cas;

import org.dom4j.Document;

import com.cisco.rekan.apicaller.HttpAPICaller;
import com.cisco.rekan.apicaller.Utils;


/**
 * <code>AbstractCASTest</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Nov 5, 2013
 *
 */
public abstract class AbstractCASTest extends HttpAPICaller {

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.IAPICaller#callAPI(java.lang.String[])
     */
    public Document callAPI(String... params) {
        if (null == super.getServerURL()) {
            super.setServerURL(Constants.CAS_SERVER_URL);
        }

        return super.callPostAPI(params);
    }

    /**
     * Assert xml result success.
     *
     * @param dom the dom
     */
    public static void assertXMLResultSuccess(Document dom) {
        Utils.assertXMLResult(dom, "//LoginResponse/response/result", "SUCCESS");
    }

    /**
     * Assert xml result failed.
     *
     * @param dom the dom
     */
    public static void assertXMLResultFailed(Document dom) {
        Utils.assertXMLResult(dom, "//LoginResponse/response/result", "FAILURE");
    }

}

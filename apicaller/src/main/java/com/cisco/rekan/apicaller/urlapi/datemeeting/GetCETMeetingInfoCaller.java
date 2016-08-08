/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.datemeeting;

import org.dom4j.Document;
import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPICaller;

/**
 * <code>RegistrationCaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 27, 2014
 *
 */
public class GetCETMeetingInfoCaller extends AbstractURLAPICaller {

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.HttpAPICaller#addParams(java.lang.String[])
     */
    @Override
    public void addParams(String... params) {
        super.addParam("AT", "GetCETMeetingInfo");

        super.addParam("TOKEN", params[0]);
        super.addParam("MK", params[1]);

        super.addParam("isUTF8", "1");
    }

    @Test
    public void testCallee() {
        String sk = "GetCMRVersion";
        Document dom = super.post4Document(sk, "211286163");
        System.out.println(dom.asXML());
    }

    @Override
    public String getPhpName() {
        return "/datameeting.php";
    }

}

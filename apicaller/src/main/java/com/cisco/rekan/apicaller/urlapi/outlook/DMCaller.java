/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.outlook;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.junit.Assert;
import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPICaller;

/**
 * <code>DMCaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 25, 2014
 *
 */
public class DMCaller extends AbstractURLAPICaller {

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.urlapi.AbstractURLAPITest#getPhpName()
     */
    @Override
    public String getPhpName() {
        return "outlook.php";
    }

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.HttpAPICaller#addParams(java.lang.String[])
     */
    @Override
    public void addParams(String... params) {
        super.addParam("AT", "DM");
        super.addParam("WID", params[0]);
        super.addParam("PWPW", params[1]);
        super.addParam("MK", params[2]);

        // other info
        super.addParam("NeedCheckPassword", "1");
        super.addParam("ST", "1");  // MC
        super.addParam("isUTF8", "1");
        super.addParam("clientversion", "31.0.0.926");
    }

    public static void deleteMeeting(String userName, String plainPassword, String strMeetingKey) {
        DMCaller caller = new DMCaller();
        strMeetingKey = StringUtils.deleteWhitespace(strMeetingKey);
        Document response = caller.post4Document(userName, plainPassword, strMeetingKey);
        String status = response.selectSingleNode("//OutlookDeleteMeeting/Status").getText();
        Assert.assertEquals("SUCCESS", status);
    }

    @Test
    public void test() {
        deleteMeeting("dats01", "P@ss1234", "212 223 924");
    }

}

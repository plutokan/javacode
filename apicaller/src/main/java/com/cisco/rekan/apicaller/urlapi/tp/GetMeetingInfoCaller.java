/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.tp;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPICaller;

/**
 * <code>GetMeetingInfoCaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 22, 2014
 *
 */
public class GetMeetingInfoCaller extends AbstractURLAPICaller {

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.urlapi.AbstractURLAPITest#getPhpName()
     */
    @Override
    public String getPhpName() {
        return "datameeting.php";
    }

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.HttpAPICaller#addParams(java.lang.String[])
     */
    @Override
    public void addParams(String... params) {
    }

    /**
     * @param token
     * @param meetingKey
     * @return
     */
    public static String getCETMeetingInfo(final String token, final String meetingKey) {
        // <protocol>://sitename.webex.com/sitename/datameeting.php?AT=GetCETMeetingInfo&TOKEN=<userToken>&MK=<meetingkey>
        GetMeetingInfoCaller caller = new GetMeetingInfoCaller();
        caller.addParam("AT", "GetCETMeetingInfo");
        caller.addParam("TOKEN", token);
        caller.addParam("MK", meetingKey);
        HttpResponse response = caller.post();

        HttpEntity responseEntity = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(responseEntity);
        } catch (IOException e) {
            logger.error(null, e);
        }

        return result;
    }

    @Test
    public void testGetCETMeetingInfo() {
        String meetingInfo = getCETMeetingInfo("aaa", "211286163");
        System.out.println(meetingInfo);
    }

}

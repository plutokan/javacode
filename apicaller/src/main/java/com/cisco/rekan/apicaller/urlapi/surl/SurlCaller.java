/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.surl;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPICaller;
import com.cisco.rekan.apicaller.urlapi.DocshowParser;

/**
 * <code>TelemetryCaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 22, 2014
 *
 */
public class SurlCaller extends AbstractURLAPICaller {

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.urlapi.AbstractURLAPITest#getPhpName()
     */
    @Override
    public String getPhpName() {
        return "surl.php";
    }

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.HttpAPICaller#addParams(java.lang.String[])
     */
    @Override
    public void addParams(String... params) {
        super.addParam("AT", "JoinMeetingTicket");
        super.addParam("WID", "jie01");
        super.addParam("PW", "P@ssword123");
        super.addParam("MK", "239716453");
        super.addParam("MPW", "DSHM7GTJ");
    }

    /**
     * @return the temp ticket.
     */
    public static String callJoinMeetingTicket() {
        SurlCaller caller = new SurlCaller();
        Document doc = caller.post4Document();
        String tempTicket = DocshowParser.getNodeContent(doc, "//JoinMeetingTicket/result");

        return tempTicket;
    }

    @Test
    public void testTelemetry4PT() {
        String tempTicket = callJoinMeetingTicket();
        System.out.println(tempTicket);
    }

}

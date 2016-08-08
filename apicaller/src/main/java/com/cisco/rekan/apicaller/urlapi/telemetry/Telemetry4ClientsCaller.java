/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.telemetry;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPICaller;

/**
 * <code>TelemetryCaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 22, 2014
 *
 */
public class Telemetry4ClientsCaller extends AbstractURLAPICaller {

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.urlapi.AbstractURLAPITest#getPhpName()
     */
    @Override
    public String getPhpName() {
        return "telemetry.php";
    }

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.HttpAPICaller#addParams(java.lang.String[])
     */
    @Override
    public void addParams(String... params) {
        super.addParam("AT", "TMI");
        super.addParam("WID", "pluto");
        super.addParam("PWPW", "P@ss1234");
    }

    /**
     * @param token
     * @param meetingKey
     * @return
     */
    public static String callTelemetry4Clients(String integrationType) {
        Telemetry4ClientsCaller caller = new Telemetry4ClientsCaller();
        caller.addParam("IT", integrationType);
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
    public void testTelemetry4PT() {
        String telemetryInfo = callTelemetry4Clients("PT");
        System.out.println(telemetryInfo);
    }

    @Test
    public void testTelemetry4iOS() {
        String telemetryInfo = callTelemetry4Clients("15");
        System.out.println(telemetryInfo);
    }

    @Test
    public void testTelemetry4Nokia() {
        String telemetryInfo = callTelemetry4Clients("18");
        System.out.println(telemetryInfo);
    }

    @Test
    public void testTelemetry4BB() {
        String telemetryInfo = callTelemetry4Clients("19");
        System.out.println(telemetryInfo);
    }

    @Test
    public void testTelemetry4Sumsung() {
        String telemetryInfo = callTelemetry4Clients("21");
        System.out.println(telemetryInfo);
    }

    @Test
    public void testTelemetry4Andriod() {
        String telemetryInfo = callTelemetry4Clients("22");
        System.out.println(telemetryInfo);
    }

}

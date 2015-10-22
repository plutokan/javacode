/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * Building D1, Innovation Park, 800 Wangjiang Xi Road, High-tech Zone, Hefei City,
 * Anhui Province, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.w;

import org.apache.http.HttpResponse;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPITest;

/**
 * <code>COCaller</code>
 *
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since apicaller Oct 12, 2015
 *
 */
public class COCaller extends AbstractURLAPITest {

    /* (non-Javadoc)
     * @see com.cisco.rekan.apicaller.urlapi.AbstractURLAPITest#getPhpName()
     */
    @Override
    protected String getPhpName() {
        return "w.php";
    }

    // TODO
    public HttpResponse saveOneClickMeeting() {
        return null;
    }

    /**
     * E.g. 
     * {@literal
     * w.php?AT=CO&BU=www.baidu.com&CSRF=d7b7890c-edff-40ba-987f-01592e32b178
     * }
     *
     * @param params the parameters like example.
     */
    @Override
    public void addParams(String... params) {
        super.addParam("AT", "CO");
        super.addParam("BU", "www.baidu.com");

        if (params.length >= 1) {
            super.addParam("CSRF", params[0]);
        }
    }

}

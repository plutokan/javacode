/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * Building D1, Innovation Park, 800 Wangjiang Xi Road, High-tech Zone, Hefei City,
 * Anhui Province, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.w;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPITest;

/**
 * <code>SOCaller</code>
 *
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since apicaller Oct 12, 2015
 *
 */
public class SOCaller extends AbstractURLAPITest {

    /* (non-Javadoc)
     * @see com.cisco.rekan.apicaller.urlapi.AbstractURLAPITest#getPhpName()
     */
    @Override
    protected String getPhpName() {
        return "w.php";
    }


    /**
     * E.g. 
     * {@literal
     * w.php?AT=SO&PW=111111&BU=www.baidu.com&TP=OneClick+with+CSRF-MC&SP=3
     *     &CSRF=d7b7890c-edff-40ba-987f-01592e32b178
     * }
     *
     * @param params the parameters like example.
     */
    @Override
    public void addParams(String... params) {
        super.addParam("AT", "SO");
        super.addParam("PW", "111111");
        super.addParam("BU", "www.baidu.com");
        super.addParam("TP", "OneClick+with+CSRF-MC");

        if (params.length >= 1) {
            super.addParam("CSRF", params[0]);
        }
    }

}

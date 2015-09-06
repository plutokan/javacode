/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.r;

import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPITest;
import com.cisco.rekan.apicaller.urlapi.Constants;

/**
 * <code>LICaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 26, 2014
 *
 */
public class LICaller extends AbstractURLAPITest {

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.urlapi.AbstractURLAPITest#getDomainURL()
     */
    @Override
    public String getDomainURL() {
        return Constants.VSCM_DOMAIN_URL;
    }

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.urlapi.AbstractURLAPITest#getSiteName()
     */
    @Override
    public String getSiteName() {
        return Constants.VSCM_WEBEX_SITE_NAME;
    }

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.urlapi.AbstractURLAPITest#getPhpName()
     */
    @Override
    public String getPhpName() {
        return "r.php";
    }

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.HttpAPICaller#addParams(java.lang.String[])
     */
    @Override
    public void addParams(String... params) {
        super.addParam("AT", "LI");
    }

    @Test
    public void test() {
        
    }

}

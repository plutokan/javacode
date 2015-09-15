/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */

package com.cisco.rekan.apicaller.urlapi;

import org.dom4j.Document;

import com.cisco.rekan.apicaller.HttpAPICaller;


/**
 * <code>AbstractURLAPITest</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Mar 21, 2014
 *
 */
public abstract class AbstractURLAPITest extends HttpAPICaller {

    private String domainURL = Constants.WEBEX_SITE_1;
    private String siteName = Constants.WEBEX_SITE_NAME_1;

    /**
     * Gets the php name.
     *
     * @return the php name
     */
    public abstract String getPhpName();

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.IAPICaller#callAPI(java.lang.String[])
     */
    @Override
    public Document callAPI(String... params) {
        super.setServerURL(getDomainURL() + getSiteName() + "/" + this.getPhpName());

        return super.callAPI(params);
    }

    @Override
    protected void callGetAPI(String... params) {
        super.setServerURL(this.getDomainURL() + this.getSiteName() + "/" + this.getPhpName());
        super.callGetAPI(params);

        return;
    }


    /**
     * @return the domainURL
     */
    public String getDomainURL() {
        return domainURL;
    }

    /**
     * @param domainURL the domainURL to set
     */
    public void setDomainURL(String domainURL) {
        this.domainURL = domainURL;
    }

    /**
     * @return the siteName
     */
    public String getSiteName() {
        return siteName;
    }

    /**
     * @param siteName the siteName to set
     */
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

}

/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */

package com.cisco.rekan.apicaller.urlapi;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
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

    private String protocol = Constants.PROTOCOL_HTTPS;
    private String domainURL = Constants.WEBEX_SITE_1;
    private String siteName = Constants.WEBEX_SITE_NAME_1;

    /**
     * Gets the php name.
     *
     * @return the php name
     */
    protected abstract String getPhpName();

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.IAPICaller#callAPI(java.lang.String[])
     */
    @Override
    public Document callPostAPI(String... params) {
        if (StringUtils.isEmpty(super.getServerURL())) {
            super.setServerURL(this.setupURI());
        }

        return super.callPostAPI(params);
    }

    private String setupURI() {
        return getProtocol() + "://" + getDomainURL() + "/" + getSiteName() + "/" + this.getPhpName();
    }

    @Override
    public HttpResponse getAPI(String... params) {
        if (StringUtils.isEmpty(super.getServerURL())) {
            super.setServerURL(this.setupURI());
        }

        return super.getAPI(params);
    }

    @Override
    public HttpResponse postAPI(String... params) {
        if (StringUtils.isEmpty(super.getServerURL())) {
            super.setServerURL(this.setupURI());
        }

        return super.postAPI(params);
    }

    /**
     * @return the protocol
     */
    protected String getProtocol() {
        return protocol;
    }

    /**
     * @param protocol the protocol to set
     */
    protected void setProtocol(String protocol) {
        this.protocol = protocol;
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

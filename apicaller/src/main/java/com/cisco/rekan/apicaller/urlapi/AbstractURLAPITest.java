/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */

package com.cisco.rekan.apicaller.urlapi;

import org.springframework.util.Assert;

import com.cisco.rekan.apicaller.AbstractHttpCaller;


/**
 * <code>AbstractURLAPITest</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Mar 21, 2014
 *
 */
public abstract class AbstractURLAPITest extends AbstractHttpCaller {

    private String scheme = Constants.PROTOCOL_HTTPS;
    private String domainURL = Constants.WEBEX_SITE_1;
    private String siteName = Constants.WEBEX_SITE_NAME_1;

    /**
     * @return the scheme
     */
    protected String getScheme() {
        return scheme;
    }

    /**
     * @return the domainURL
     */
    protected String getDomainURL() {
        return domainURL;
    }

    /**
     * @return the siteName
     */
    protected String getSiteName() {
        return siteName;
    }

    /**
     * Gets the php name.
     *
     * @return the php name
     */
    protected abstract String getPhpName();

    public AbstractURLAPITest() {
        super();

        super.setServerURL(this.setupURI());
    }

    /**
     * @param scheme
     * @param domainURL
     * @param siteName
     */
    public AbstractURLAPITest(String scheme, String domainURL, String siteName) {
        super();
        this.scheme = scheme;
        this.domainURL = domainURL;
        this.siteName = siteName;

        super.setServerURL(this.setupURI());
    }

    private String setupURI() {
        Assert.notNull(this.scheme);
        Assert.notNull(this.domainURL);
        Assert.notNull(this.siteName);

        return this.scheme + "://" + this.domainURL + "/" + this.siteName + "/" + this.getPhpName();
    }

}

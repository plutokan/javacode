/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * Building D1, Innovation Park, 800 Wangjiang Xi Road, High-tech Zone, Hefei City,
 * Anhui Province, China All rights reserved.
 */
package com.cisco.rekan.apicaller;

import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;
import org.junit.Assert;

/**
 * <code>HttpGetCaller</code>
 *
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since apicaller Oct 21, 2015
 *
 */
public final class HttpPostCaller extends AbstractHttpCaller implements ICaller<HttpResponse> {

    /**
     * logger of log4j 1.x.
     */
    protected static Logger logger = Logger.getLogger(HttpPostCaller.class);

    public HttpPostCaller(String url) {
        Assert.assertNotNull(url);

        super.setServerURL(url);
    }

    /* (non-Javadoc)
     * @see com.cisco.rekan.apicaller.AbstractHttpCaller#addParams(java.lang.String[])
     */
    @Override
    public void addParams(String... params) {
        Assert.assertArrayEquals(new String[0], params);
    }

    @Override
    public final HttpResponse get(String... params) {
        Assert.fail();

        return null;
    }

    /* (non-Javadoc)
     * @see com.cisco.rekan.apicaller.ICaller#call()
     */
    @Override
    public HttpResponse call() {
        return post();
    }

}

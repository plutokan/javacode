/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * Building D1, Innovation Park, 800 Wangjiang Xi Road, High-tech Zone, Hefei City,
 * Anhui Province, China All rights reserved.
 */
package com.cisco.rekan.apicaller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
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
        super();
        Assert.assertNotNull(url);
        try {
            List<NameValuePair> params = URLEncodedUtils.parse(new URI(url), CharEncoding.UTF_8);

            if (CollectionUtils.isNotEmpty(params)) {
                for (NameValuePair param : params) {
                    super.addParam(param.getName(), param.getValue());
                }
            }
        } catch (URISyntaxException e) {
            logger.error(null, e);
        }

//        url = StringUtils.substringBefore(url, "?");
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

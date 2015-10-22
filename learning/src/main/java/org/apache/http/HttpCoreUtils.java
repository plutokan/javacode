/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * Building D1, Innovation Park, 800 Wangjiang Xi Road, High-tech Zone, Hefei City,
 * Anhui Province, China All rights reserved.
 */
package org.apache.http;

import java.net.HttpCookie;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.log4j.Logger;

/**
 * <code>HttpCoreUtils</code>
 *
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since learning Oct 21, 2015
 *
 */
public final class HttpCoreUtils {

    static Logger logger = Logger.getLogger(HttpCoreUtils.class);

    private HttpCoreUtils() {
    }

    public static String getCookieValue(CookieStore cookieStore, String name) {
        if (null == cookieStore) {
            return null;
        }
        String value = null;

        List<Cookie> cookies = cookieStore.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                value = cookie.getValue();
                break;
            }
        }

        return value;
    }

    /**
     * Parse the cookies from heads.
     *
     * @param response HttpResponse.
     * @param domain name.
     * @return a new CookieStore for httpclient.
     */
    public static CookieStore parseCookie4Headers(HttpResponse response, String domainName) {
        CookieStore cookieStore = new BasicCookieStore();
        Header[] headers = response.getAllHeaders();
        for (Header header : headers) {
            logger.debug(header.getName() + " : " + header.getValue());
            if ("Set-Cookie".equals(header.getName())) {
                List<HttpCookie> httpCookies = HttpCookie.parse(header.getValue());
                if (CollectionUtils.isNotEmpty(httpCookies)) {
                    for (HttpCookie httpCookie : httpCookies) {
                        Cookie cookie = HttpCoreUtils.cvtHttpCookie2Cookie(httpCookie, domainName);
                        cookieStore.addCookie(cookie);
                    }
                }
            }
        }
        logger.debug(cookieStore);

        return cookieStore;
    }

    /**
     * Cookie convert.
     *
     * @param httpCookie cookie in package <code>java.net</code>.
     * @param domainName domain name for the new cookie.
     * @return cookie in package <code>org.apache.http</code>.
     */
    public static final Cookie cvtHttpCookie2Cookie(HttpCookie httpCookie, String domainName) {
        BasicClientCookie cookie = new BasicClientCookie(httpCookie.getName(), httpCookie.getValue());
        String domain = httpCookie.getDomain();
//        if (StringUtils.isEmpty(domain)) {
            domain = domainName;
//        }
        cookie.setDomain(domain);
        cookie.setPath(httpCookie.getPath());
        cookie.setSecure(httpCookie.getSecure());
        cookie.setVersion(httpCookie.getVersion());

        final long maxAge = httpCookie.getMaxAge();
        if (maxAge > 0) {
            final Date expiryDate = new Date(System.currentTimeMillis() + maxAge * 1000L);
            cookie.setExpiryDate(expiryDate);
            cookie.setAttribute(ClientCookie.MAX_AGE_ATTR, Long.toString(maxAge));
        }

        logger.debug(cookie);
        return cookie;
    }

}

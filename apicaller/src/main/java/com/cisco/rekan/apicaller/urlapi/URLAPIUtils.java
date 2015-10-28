/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * Building D1, Innovation Park, 800 Wangjiang Xi Road, High-tech Zone, Hefei City,
 * Anhui Province, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi;

import org.apache.http.HttpCoreUtils;
import org.apache.http.client.CookieStore;

import com.cisco.rekan.apicaller.Utils;

/**
 * <code>URLAPIUtils</code>
 *
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since apicaller Oct 23, 2015
 *
 */
public final class URLAPIUtils {

    private URLAPIUtils() {
    }

    public static String getCsrf(CookieStore cookieStore) {
        if (null == cookieStore) {
            return null;
        }

        String csrf = HttpCoreUtils.getCookieValue(cookieStore, "_csrf_");
        return csrf;
    }

    public static String getCsrf(String url) {
        if (null == url) {
            return null;
        }

        String csrf = Utils.getParamValue4URI(url, "CSRF");
        return csrf;
    }

}

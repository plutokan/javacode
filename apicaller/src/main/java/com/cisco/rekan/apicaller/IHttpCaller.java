/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */

package com.cisco.rekan.apicaller;

import org.apache.http.HttpResponse;


/**
 * <code>IHttpCaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Nov 5, 2013
 *
 */
public interface IHttpCaller extends ICookieSupport {

    /**
     * Call API via get method.
     *
     * @param params the parameters
     * @return the string
     */
    HttpResponse get(String... params);

    /**
     * Call API via post method.
     *
     * @param params the parameters
     * @return the string
     */
    HttpResponse post(String... params);

}

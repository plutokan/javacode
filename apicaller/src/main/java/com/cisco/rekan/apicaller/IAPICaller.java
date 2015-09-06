/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */

package com.cisco.rekan.apicaller;

import org.dom4j.Document;


/**
 * <code>IAPICaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Nov 5, 2013
 *
 */
public interface IAPICaller {

    /**
     * Call API.
     *
     * @param params the parameters
     * @return the string
     */
    Document callAPI(String... params);

}

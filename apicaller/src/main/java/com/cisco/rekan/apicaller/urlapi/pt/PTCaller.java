/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */

package com.cisco.rekan.apicaller.urlapi.pt;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPICaller;


/**
 * <code>PTCaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Mar 21, 2014
 *
 */
public class PTCaller extends AbstractURLAPICaller {

    @Override
    public void addParams(String... params) {
        if (params.length > 0) {
            super.addParam("", params[0]);
        }
    }

    @Override
    public String getDomainURL() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getSiteName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getPhpName() {
        // TODO Auto-generated method stub
        return null;
    }

}

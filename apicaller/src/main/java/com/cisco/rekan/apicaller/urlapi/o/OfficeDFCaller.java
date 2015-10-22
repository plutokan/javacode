/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.o;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPITest;

/**
 * <code>OfficeDFCaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 14, 2014
 *
 */
public class OfficeDFCaller extends AbstractURLAPITest {

    @Override
    public String getPhpName() {
        return "o.php";
    }

    @Override
    public void addParams(String... params) {
        super.addParam("AT", "DF");
        super.addParam("FN", params[0]);
        super.addParam("BU", params[1]);
    }

    @Test
    public void test() throws UnsupportedEncodingException {
        // o.php?AT=DF&FN=5_Full.txt&BU=
        String serverURL = URLEncoder.encode(this.getDomainURL() + this.getSiteName(), "UTF-8");
        super.post4Document("/5_Full.txt", serverURL);
    }

}

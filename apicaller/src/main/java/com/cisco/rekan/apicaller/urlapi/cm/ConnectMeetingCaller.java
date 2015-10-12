/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.cm;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPITest;

/**
 * <code>ConnectMeetingCaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 14, 2014
 *
 */
public class ConnectMeetingCaller extends AbstractURLAPITest {

    @Override
    public String getPhpName() {
        return "cm.php";
    }

    @Override
    public void addParams(String... params) {
        super.addParam("AT", params[0]);
        super.addParam("MK", params[1]);
    }

    @Test
    public void test() throws UnsupportedEncodingException {
        // /cm.php?AT=JM&MK=150631521
        super.callPostAPI("JM", "150631521");
    }

}

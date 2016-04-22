/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.logevent;

import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPICaller;

/**
 * <code>LogEventCaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 25, 2014
 *
 */
public class LogEventCaller extends AbstractURLAPICaller {

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.urlapi.AbstractURLAPITest#getPhpName()
     */
    @Override
    public String getPhpName() {
        return "logevent.php";
    }

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.HttpAPICaller#addParams(java.lang.String[])
     */
    @Override
    public void addParams(String... params) {
        // &siteurl=robin&UserName=jeffery+iphone&Email=jeffery%40test2.com&GuestID=2125675162&GuestID=2125675162
        // &HostID=0&Email=jeffery@test2.com&ConfID=26666671971239761&SessionID=91475956&Module=101&ErrorCode=2000
        super.addParam("UserName", "jeffery iphone");
        super.addParam("Email", "jeffery@test2.com");
        super.addParam("GuestID", "2125675162");
        super.addParam("HostID", "0");
        super.addParam("ConfID", "26666671971239763");
        super.addParam("SessionID", "91475956");
        super.addParam("Module", "101");
        super.addParam("ErrorCode", "2016");
    }

    @Test
    public void test() {
        super.setServerURL("https://f1766.qa.webex.com/f1766/logevent.php");

        super.post4String();
    }

}

/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.j;

import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPITest;
import com.cisco.rekan.apicaller.urlapi.Constants;

/**
 * <code>JoinMeetingCaller</code>
 * Because of the "j.php" service ID is -1. APIEntryAction returns the redirected URL to browser directly,
 * not through the URLBridgeServlet. So this caller cannot work normally.

https://deweb2.qa.webex.com/url0201lsp11/j.do?S=150631521&rnd=0482308160&needFilter=false&siteurl=dewd-pluto
https://deweb2.qa.webex.com/mw0401lsp11/mywebex/default.do?service=1&main_url=%2Fmc0901lsp11%2Fe.do%3Fsiteurl%3Ddewd-pluto%26AT%3DMI%26EventID%3D336642%26Host%3D275bdaea4a05002c043c24%26NewLoginFlag%3D1&siteurl=dewd-pluto
https://deweb2.qa.webex.com/mw0401lsp11/mywebex/default.do?service=1&main_url=%2Fmc0901lsp11%2Fe.do%3Fsiteurl%3Ddewd-pluto%26AT%3DMI%26EventID%3D336642%26Host%3DZWRhZGQ2NWQ0NjY1NWY0YTEw0%26NewLoginFlag%3D1&siteurl=dewd-pluto

https://deweb2.qa.webex.com/url0201lsp11/j.do?J=150631521&rnd=0482308160&needFilter=false&siteurl=dewd-pluto
https://deweb2.qa.webex.com/mw0401lsp11/mywebex/default.do?service=1&main_url=%2Fmc0901lsp11%2Fe.do%3Fsiteurl%3Ddewd-pluto%26AT%3DMI%26EventID%3D336642%26Host%3Da82c3e20093d4323&siteurl=dewd-pluto
https://deweb2.qa.webex.com/mw0401lsp11/mywebex/default.do?service=1&main_url=%2Fmc0901lsp11%2Fe.do%3Fsiteurl%3Ddewd-pluto%26AT%3DMI%26EventID%3D336642%26Host%3DOTc4Zjc5MzhiYjU1MWUyMzE4MTYxOA2&siteurl=dewd-pluto
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 21, 2014
 *
 */
public class JoinMeetingCaller extends AbstractURLAPITest {

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.urlapi.AbstractURLAPITest#getDomainURL()
     */
    @Override
    public String getDomainURL() {
        return Constants.VSCM_DOMAIN_URL;
    }

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.urlapi.AbstractURLAPITest#getSiteName()
     */
    @Override
    public String getSiteName() {
        return "url0201lsp11";
    }

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.urlapi.AbstractURLAPITest#getPhpName()
     */
    @Override
    public String getPhpName() {
        return "j.do?rnd=0482308160&needFilter=false&siteurl=dewd-pluto";
    }

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.HttpAPICaller#addParams(java.lang.String[])
     */
    @Override
    public void addParams(String... params) {
        // &rnd=0482308160&needFilter=false&siteurl=dewd-pluto


        super.addParam("MeetingKey", params[0]);
    }

    @Test
    public void test() {
        super.callAPI("150631521");
    }

}

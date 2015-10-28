package com.cisco.rekan.apicaller.urlapi.m;

import org.junit.Before;
import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPICaller;

/**
 * <code>MCaller</code>
 * <pre>
 *
(1) CSCuv94401
Set service type to MC:
https://pluto.qa.webex.com/pluto/o.php?AT=ST&SP=MC&BU=http%3A%2F%2Fwww.baidu.com

Login site:
https://pluto.qa.webex.com/pluto/p.php?AT=LI&WID=pluto&PID=jpd0rPXB47rTGvrlW1KimA&PW=P%40ss1234&MU=GoBack
RESPONSE: https://pluto.qa.webex.com/mw3100/mywebex/default.do?siteurl=pluto&AT=LI&WID=pluto&ST=SUCCESS&CSRF=c652e364-4971-4295-8bf9-73a2b85e9a69

Schedule an MC meeting:
https://pluto.qa.webex.com/pluto/m.php?AT=SM&CSRF=c652e364-4971-4295-8bf9-73a2b85e9a69&MN=f814724&YE=2100&MO=1&DA=1&HO=12&MI=30&PW=12345678
RESPONSE:
https://m3bweb4.qa.webex.com/mc3100/m.do?AT=SM&HO=12&CSRF=c652e364-4971-4295-8bf9-73a2b85e9a69&renewticket=0&isurlact=true&targetAction=m.do&apiname=m.php&MN=f814724&DA=1&YE=2100&MO=1&targetApp=mc3100&needFilter=false&serviceType=MC&siteurl=pluto&PW=12345678&MI=30&rnd=6039901294&entactname=%2Fm.do&entappname=url3100
/cmp3100/m.do?siteurl=pluto&AT=SM&HO=12&CSRF=c652e364-4971-4295-8bf9-73a2b85e9a69&renewticket=0&isurlact=true&targetAction=m.do&apiname=m.php&MN=f814724&DA=1&YE=2100&MO=1&targetApp=mc3100&needFilter=false&serviceType=MC&siteurl=pluto&PW=12345678&MI=30&rnd=7763431883&entactname=%2Fm.do&entappname=url3100
ERROR PAGE:
Error. Invalid input or system error. Please try again or contact your site administrator.

2015-09-16 16:10:47,586 133640370 ERROR /cmp3100 [http-apr-8081-exec-1] com.webex.webapp.common.biz.meeting.AbstractMeetingMgr     - com.webex.common.exception.WbxException: Date should not be later than 12/31/2050 SiteID = 256762
 *
 * </pre>
 * 
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since 2.0
 *
 */
public class MCaller extends AbstractURLAPICaller {

    @Before
    public void initilize() {
        super.setServerURL("https://pluto.qa.webex.com/pluto/");
    }

    @Test
    public void testM() {

    }

    /*
     * (non-Javadoc)
     * @see com.cisco.rekan.apicaller.urlapi.AbstractURLAPITest#getPhpName()
     */
    @Override
    protected String getPhpName() {
        return "m.php";
    }

    /*
     * (non-Javadoc)
     * @see com.cisco.rekan.apicaller.HttpAPICaller#addParams(java.lang.String[])
     */
    @Override
    public void addParams(String... params) {
        // TODO Auto-generated method stub

    }

}

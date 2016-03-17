package com.cisco.rekan.apicaller.urlapi.m;


import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPICaller;
import com.cisco.rekan.apicaller.urlapi.p.PLoginCaller;

/**
 * <code>LSCaller</code>
 * Checked the API for CSCuw16893. <b>This API should be discard long time ago.</b>
 * <pre>
https://cisco.webex.com/cisco/m.php?AT=LS

RESPONSE:

ERROR PAGE:
 *
 * </pre>
 * 
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since 2.0
 *
 */
@Deprecated
public class LSCaller extends AbstractURLAPICaller {

    public HttpResponse call() {
        super.post4Document();
        return null;
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
        super.addParam("AT", "LS");
    }

    @Test
    public void test() {
        final String webexID = "pluto";
        final String webexPassword = "P@ss1234";
        PLoginCaller loginCaller = new PLoginCaller();
        loginCaller.login(webexID, webexPassword);
        CookieStore cookieStore = loginCaller.getCookieStore();

        {
            LSCaller caller = new LSCaller();
            caller.setCookieStore(cookieStore);
            caller.call();
        }
    }

}

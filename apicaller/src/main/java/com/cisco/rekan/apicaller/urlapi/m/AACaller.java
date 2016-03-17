package com.cisco.rekan.apicaller.urlapi.m;


import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.junit.Assert;
import org.junit.Test;

import com.cisco.rekan.apicaller.Utils;
import com.cisco.rekan.apicaller.urlapi.AbstractURLAPICaller;
import com.cisco.rekan.apicaller.urlapi.p.PLoginCaller;

/**
 * <code>MCaller</code>
 * <pre>
https://f463-pluto.qa.webex.com/f463-pluto/m.php?AT=AA&EI=1&MK=214611290&EM=pluto2@qa.webex.com&FN=PK2
https://pluto.qa.webex.com/pluto/m.php?AT=AA&EI=1&MK=214778934&EM=pluto2@qa.webex.com&FN=pluto
RESPONSE:

ERROR PAGE:
 *
 * </pre>
 * 
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since 2.0
 *
 */
public class AACaller extends AbstractURLAPICaller {

    private String ei;
    private String meetingKey;
    private String[] emails;
    private String[] fullNames;

    /**
     * @return the ei
     */
    public String getEi() {
        return ei;
    }

    /**
     * @param ei the ei to set
     */
    public void setEi(String ei) {
        this.ei = ei;
    }

    /**
     * @return the meetingKey
     */
    public String getMeetingKey() {
        return meetingKey;
    }

    /**
     * @param meetingKey the meetingKey to set
     */
    public void setMeetingKey(String meetingKey) {
        this.meetingKey = meetingKey;
    }

    /**
     * @return the emails
     */
    public String[] getEmails() {
        return emails;
    }

    /**
     * @param emails the emails to set
     */
    public void setEmails(String[] emails) {
        this.emails = emails;
    }

    /**
     * @return the fullNames
     */
    public String[] getFullNames() {
        return fullNames;
    }

    /**
     * @param fullNames the fullNames to set
     */
    public void setFullNames(String[] fullNames) {
        this.fullNames = fullNames;
    }

    public HttpResponse call() {
//        super.post4Document();
//        return null;
        HttpResponse response = super.post();
        Utils.printHeaders(response);
        logger.debug(super.getCookieStore());

        HttpResponse response2 = super.get2post(response);
        return response2;
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
        super.addParam("AT", "AA");
//        if (params.length >= 1) {
//            csrf = params[0];
//        }

        if (StringUtils.isNotEmpty(ei)) {
            super.addParam("EI", ei);
        }
        if (StringUtils.isNotEmpty(meetingKey)) {
            super.addParam("MK", StringUtils.deleteWhitespace(meetingKey));
        }
        if (null != emails) {
            Assert.assertEquals(emails.length, fullNames.length);
            if (emails.length == 1) {
                super.addParam("EM", emails[0]);
                super.addParam("FN", fullNames[0]);
            } else {
                for (int i = 0; i < emails.length; i++) {
                    super.addParam("EM" + (i + 1), emails[i]);
                    super.addParam("FN" + (i + 1), fullNames[i]);
                }
            }
        }
    }

    @Test
    public void test() {
        final String webexID = "pluto";
        final String webexPassword = "P@ss1234";
        PLoginCaller loginCaller = new PLoginCaller();
        loginCaller.login(webexID, webexPassword);
        CookieStore cookieStore = loginCaller.getCookieStore();

        {
            AACaller caller = new AACaller();
            caller.setCookieStore(cookieStore);
            caller.setEi("1");
            caller.setMeetingKey("214 611 290");
            caller.setEmails(new String[] { "pluto@qa.webex.com" });
            caller.setFullNames(new String[] { "Renhua Kan" });
            HttpResponse res = caller.call();
            
            Utils.printContent(res);
        }
    }

}

/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.meeting;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPICaller;
import com.cisco.rekan.apicaller.urlapi.AppTokenUtil;
import com.cisco.rekan.apicaller.urlapi.DateUtils;
import com.webex.webapp.common.exception.WbxAppTokenException;

/**
 * <code>MeetingCaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 27, 2014
 *
 */
public class MeetingCaller extends AbstractURLAPICaller {

    static Logger logger = Logger.getLogger(MeetingCaller.class);

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.urlapi.AbstractURLAPITest#getPhpName()
     */
    @Override
    public String getPhpName() {
        return "meeting.php";
    }

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.HttpAPICaller#addParams(java.lang.String[])
     */
    @Override
    public void addParams(String... params) {
        // AT=GetInfo&UUID=4g9tgwnbRkminnymq1SnBg&AppName=APP_Tahoe&AppToken=******&CurrentTime=2016-01-06 02:07:12&isUTF8=1
        super.addParam("AT", "GetInfo");

        super.addParam("UUID", params[0]);
        String appToken = AppTokenUtil.createAPPToken(params[1]);
        super.addParam("AppName", params[1]);
        super.addParam("AppToken", appToken);

        Calendar currentCal = Calendar.getInstance();
        String currentTime = DateUtils.ISO_DATETIME_FORMAT.format(currentCal);
        super.addParam("CurrentTime", currentTime);
        super.addParam("isUTF8", "1");
    }

    /**
     * API response as below.
     * <pre>
     * ActionType=GetMeetingInfo&ResultCode=3&Description=Success&SiteName=t30mmp&SiteID=452487&ConfID=17130403210986755
     * &HostID=1500198636&CalloutType=1&PriAccessNumbers=&Backup1AccessNumbers=&Backup2AccessNumbers=&EntryExitTone=0
     * &LanguageID=1&MeetingStartTime=&ScheduledEndTime=2016-01-05 18:09:00&SiteFullUrl=t30mmp.webex.com
     * &MeetingKey=131837987&MuteUponEntry=0&WbxDisplayName=Cisco WebEx Meeting&AttendeeID=7&UsePSTN=false
     * &TelePlusAttendeeID=14203&BuildNumber=31.0.0.4908&EnforceAudioLogin=0&EnforceAudioPassword=0&AudioPassword=null
     * &ExtendParams=CallIn%1
     * </pre>
     * @throws WbxAppTokenException 
     */
    @Test
    public void test4UUID() throws WbxAppTokenException {

        String serverURL = "https://cet68.qa.webex.com/cet68/meeting.php";
        super.setServerURL(serverURL);
        super.post4String("gpxa0ApLT4q5JwKpQSqubA", "APP_Tahoe");

    }

    @Test
    public void test4MeetingKey() throws WbxAppTokenException {

        super.setServerURL("https://cet68.qa.webex.com/cet68/meeting.php");
        String meetingKey = "";
        super.addParam("MK", StringUtils.deleteWhitespace(meetingKey)); // meeting key will override the UUID parameter.
        super.post4String("gpxa0ApLT4q5JwKpQSqubA", "APP_Tahoe");

    }

}

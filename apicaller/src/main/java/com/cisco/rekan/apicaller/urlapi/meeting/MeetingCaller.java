/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.meeting;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPICaller;
import com.cisco.rekan.apicaller.urlapi.DocshowParser;
import com.cisco.rekan.apicaller.urlapi.datemeeting.RegistrationCaller;

/**
 * <code>MeetingCaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 27, 2014
 *
 */
public class MeetingCaller extends AbstractURLAPICaller {

    private static final String USER_NAME = "pluto";
    private static final String USER_PASSWORD = "P@ss1234";
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

        super.addParam("UUID", StringUtils.deleteWhitespace(params[0]));

        super.addParam("EM", "rekan@cisco.com");
        super.addParam("DN", "steady wang");

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
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        // TODO use apptoken replace the session ticket.
        RegistrationCaller loginCaller = new RegistrationCaller();

        Document docshow = super.post4Document("213 627 366");
        docshow = DocshowParser.getClientparam(docshow);

        DocshowParser.printVideoAddresses(docshow);
    }

}

/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.outlook;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.junit.Assert;
import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPICaller;

/**
 * <code>GMRSCaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 25, 2014
 *
 */
public class EditMeetingCaller extends AbstractURLAPICaller {

    private static final String USER_NAME = "pluto";
    private static final String USER_PASSWORD = "P@ss1234";

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.urlapi.AbstractURLAPITest#getPhpName()
     */
    @Override
    public String getPhpName() {
        return "outlook.php";
    }

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.HttpAPICaller#addParams(java.lang.String[])
     */
    @Override
    public void addParams(String... params) {
        super.addParam("AT", "EditMeeting");
        super.addParam("MK", StringUtils.deleteWhitespace(params[0]));
        super.addParam("OS", "OI");

        super.addParam("WID", USER_NAME);
        super.addParam("PWPW", USER_PASSWORD);
    }

    public static String updateSingleMeeting(String meetingKey, String meetingTopic, String startTime, String endTime,
            int duration, String repeatType, String dayInterval, String originalStartTime) {
        EditMeetingCaller caller = new EditMeetingCaller();
        if (StringUtils.isNotEmpty(meetingTopic)) {
            caller.addParam("NewMeetingTopic", meetingTopic);
        }
        if (StringUtils.isNotEmpty(startTime)) {
            caller.addParam("NewStartTime", startTime);
            caller.addParam("NewEndTime", endTime);
            caller.addParam("NewDuration", duration);
        }
        if (StringUtils.isNotEmpty(repeatType)) {
            caller.addParam("RT", repeatType);
            caller.addParam("DayInterval", dayInterval);
        }
        if (StringUtils.isNotEmpty(originalStartTime)) {
            caller.addParam("OriginalStartTime", originalStartTime);
        }

        Document scheduleResponse = caller.post4Document(meetingKey);
        String status = scheduleResponse.selectSingleNode("//OutlookScheduleMeeting/Status").getText();
        Assert.assertEquals("SUCCESS", status);
        String strMeetingKey = scheduleResponse.selectSingleNode("//OutlookScheduleMeeting/MK").getText();

        return strMeetingKey;

    }

    @Test
    public void testSingle() {
        updateSingleMeeting("224 426 302 ", "PlutoTestMeeting011-update02",
                "20161014T11:00", "20161014T11:30", 30, null, null, null);
    }

    @Test
    public void testRepeated() {
        updateSingleMeeting("220 712 971 ", "PlutoTestMeeting012-exp02",
                null, null, 10, null, null, "20161015T11:00");
    }

}

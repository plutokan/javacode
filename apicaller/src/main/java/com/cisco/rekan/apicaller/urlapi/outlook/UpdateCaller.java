/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.outlook;

import java.util.Date;

import org.dom4j.Document;
import org.junit.Assert;

import com.cisco.rekan.apicaller.urlapi.Constants;

/**
 * <code>ScheduleCaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 25, 2014
 *
 */
public class UpdateCaller extends ScheduleCaller {

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
        super.addParams(params);

        super.addParam("MK", params[3]);
    }

    /**
     * schedule killers meeting.
     *
     * @param userName webex user name.
     * @param userPlainPassword user plain password.
     * @param meetingTopic meeting topic.
     * @return
     */
    public static Document disableKillers4Series(String seriesMeetingKey, String userName, String userPlainPassword,
            String meetingTopic, ExceptionMeetingsParam params) {
        UpdateCaller caller = new UpdateCaller();

        caller.addParam("ExceptionMeetingList", params.toString());
        caller.addParam("MT", Constants.MEETING_TYPE_ID_PRO_DEFAULT);
        caller.addParam("OA", "190");
        caller.addRepeatedParams4Daily(); // repeated info same as the old series meeting.

        Document scheduleResponse = caller.post4Document(userName, userPlainPassword, meetingTopic, seriesMeetingKey);
        String status = scheduleResponse.selectSingleNode("//OutlookScheduleMeeting/Status").getText();
        Assert.assertEquals("SUCCESS", status);

        return scheduleResponse;
    }

    public static void updateMeetingTopic(String userName, String userPlainPassword, long meetingKey,
            String meetingTopic) {
        
    }

    public static void updateMeetingTime(String userName, String userPlainPassword, long meetingKey,
            Date startTime) {
        
    }

    public static void updateMeetingType(String userName, String userPlainPassword, long meetingKey,
            long meetingTypeID) {
        
    }

    public static void updateMeetingAudio(String userName, String userPlainPassword, long meetingKey,
            int audioType) {
        
    }

}

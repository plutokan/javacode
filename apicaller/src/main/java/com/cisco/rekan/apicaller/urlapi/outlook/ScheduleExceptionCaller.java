/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.outlook;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.FastDateFormat;
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
public class ScheduleExceptionCaller extends ScheduleCaller {

    public static final FastDateFormat ISO_DATETIME_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.HttpAPICaller#addParams(java.lang.String[])
     */
    @Override
    public void addParams(String... params) {
        super.addParams(params);

        super.addParam("MK", params[3]);
        addExpRepeatedParams4Daily(NumberUtils.toInt(params[4]));
    }

    private void addExpRepeatedParams4Daily(int dayOffset) {
        super.addParam("RT", "NoRepeat");
        super.addParam("Always", "0");
        Calendar startCal = super.getStartCalendar(dayOffset);
        super.setStartCalendar(startCal);

        String originalStartTime = ISO_DATETIME_FORMAT.format(startCal);
        super.addParam("OriginalStartTime", originalStartTime);
        super.addParam("IsSingleOccurrence", 1);
    }

    /**
     * schedule killers meeting.
     *
     * @param userName webex user name.
     * @param userPlainPassword user plain password.
     * @param meetingTopic meeting topic.
     * @return
     */
    public static String disableOneKillers(String seriesMeetingKey, int dayOffset, String userName, String userPlainPassword, String meetingTopic) {
        ScheduleExceptionCaller caller = new ScheduleExceptionCaller();

        caller.addParam("MT", Constants.MEETING_TYPE_ID_PRO_DEFAULT);
        caller.addParam("OA", "190");

        Document scheduleResponse = caller.post4Document(userName, userPlainPassword, meetingTopic, seriesMeetingKey, String.valueOf(dayOffset));
        String status = scheduleResponse.selectSingleNode("//OutlookScheduleMeeting/Status").getText();
        Assert.assertEquals("SUCCESS", status);
        String strMeetingKey = scheduleResponse.selectSingleNode("//OutlookScheduleMeeting/MK").getText();

        return strMeetingKey;
    }

    public static String updateOneTopic4Killers(String seriesMeetingKey, int dayOffset, String userName, String userPlainPassword, String meetingTopic) {
        ScheduleExceptionCaller caller = new ScheduleExceptionCaller();

        caller.addParam("MT", Constants.MEETING_TYPE_ID_TP_PLUS);
        caller.addParam("OA", "158");

        Document scheduleResponse = caller.post4Document(userName, userPlainPassword, meetingTopic, seriesMeetingKey, String.valueOf(dayOffset));
        String status = scheduleResponse.selectSingleNode("//OutlookScheduleMeeting/Status").getText();
        Assert.assertEquals("SUCCESS", status);
        String strMeetingKey = scheduleResponse.selectSingleNode("//OutlookScheduleMeeting/MK").getText();

        return strMeetingKey;
    }

    public static void updateMeetingTime4Killers(String userName, String userPlainPassword, long meetingKey,
            Date startTime) {
        
    }

    public static void updateMeetingType(String userName, String userPlainPassword, long meetingKey,
            long meetingTypeID) {
    }

    public static void updateMeetingAudio4Killers(String userName, String userPlainPassword, long meetingKey,
            int audioType) {
        
    }

}

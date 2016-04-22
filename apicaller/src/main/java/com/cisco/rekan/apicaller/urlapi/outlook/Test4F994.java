/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * Building D1, Innovation Park, 800 Wangjiang Xi Road, High-tech Zone, Hefei City,
 * Anhui Province, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.outlook;

import java.util.Calendar;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;
import org.junit.Assert;
import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.DateUtils;

/**
 * <code>Test4F994</code>
 *
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since apicaller Mar 31, 2016
 *
 */
public class Test4F994 {

    private static final String USER_NAME = "dats01";
    private static final String USER_PASSWORD = "P@ss1234";

    /**
     * CSCuy90944: HF(T31r2):Change TP SessionType->Non-TP SessionType not delete TP param
     */
    @Test
    public void test4CSCuy90944() {

        // 1. schedule a TP series meeting.
        String meetingTopic = "TestReaptedTPMeeting002";
        String strMeetingKey = ScheduleCaller.scheduleKillersSeries4PC(USER_NAME, USER_PASSWORD, meetingTopic);

        ExceptionMeetingsParam params = new ExceptionMeetingsParam();
        // 2.1. schedule a exception meeting from the series meeting, update meeting topic.
        int dayOffset1 = 1;
        String strExpMK1 = ScheduleExceptionCaller.updateOneTopic4Killers(strMeetingKey, dayOffset1, USER_NAME,
                USER_PASSWORD, meetingTopic + "-Updated");
        ExceptionMeetingParam param1 = getException(strExpMK1, dayOffset1);
        params.add(param1);

        // 2.2. schedule a exception meeting from the series meeting, disable TP on the exception meeting.
        int dayOffset2 = 2;
        String strExpMK2 = ScheduleExceptionCaller.disableOneKillers(strMeetingKey, dayOffset2, USER_NAME,
                USER_PASSWORD, meetingTopic);
        ExceptionMeetingParam param2 = getException(strExpMK2, dayOffset2);
        params.add(param2);

        // 3. update the series meeting, disable TP. Check the response.
        Document responseDoc = UpdateCaller.disableKillers4Series(strMeetingKey, USER_NAME, USER_PASSWORD, meetingTopic,
                params);
        List<Node> nodes = responseDoc.selectNodes("//OutlookScheduleMeeting/ExceptionMeetingList/ExceptionMeeting/Status");
        Assert.assertEquals("SUCCESS", nodes.get(0).getText());
        Assert.assertNotEquals("SUCCESS", nodes.get(1).getText());

        // 4. delete the TP series meeting.
//        DMCaller.deleteMeeting(USER_NAME, USER_PASSWORD, strMeetingKey);
    }

    private ExceptionMeetingParam getException(String expMeetingKey, int dayOffset) {
        Calendar expCal = ScheduleCaller.getStartCalendar(dayOffset);
        String originalStartTime = DateUtils.ISO_DATETIME_FORMAT.format(expCal);
        ExceptionMeetingParam param = new ExceptionMeetingParam("EM", expMeetingKey, originalStartTime);

        return param;
    }
}

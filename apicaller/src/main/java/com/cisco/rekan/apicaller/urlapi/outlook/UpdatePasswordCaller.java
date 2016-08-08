/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.outlook;

import java.util.Calendar;

import org.dom4j.Document;
import org.junit.Assert;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPICaller;
import com.cisco.rekan.apicaller.urlapi.Constants;

/**
 * <code>Uncompleted</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 25, 2014
 *
 *.
 *
 */
public class UpdatePasswordCaller extends AbstractURLAPICaller {

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.urlapi.AbstractURLAPITest#getPhpName()
     */
    @Override
    public String getPhpName() {
        return "outlook.php";
    }

    protected void addFixedParams() {
        super.addParam("AT", "SM");

        // normal info
//        super.addParam("ExcludePW", "0");
//        super.addParam("SupportPKI", "1");
//        super.addParam("SupportAES", "1");
        super.addParam("NeedCheckPassword", "1");
//        super.addParam("PPW", "P@ssword1234");
//        super.addParam("ST", "1"); // service type.
        super.addParam("AP", "0"); // if allow Auto Play. 1/0 
//        super.addParam("LF", "0"); // List flag, 1-list meeting,  0-unlisted meeting
//        super.addParam("AJB", "1"); // Allow join before meeting start. 1-allow, 0-not allow 
//        super.addParam("JB", "5"); // Minutes that allow attendee join before meeting start.
//        super.addParam("AR", "0"); // Require attendee registration. 1-Require, 0-not Require.
//        super.addParam("AQ", "0"); // Auto acccept attendee registration. 1-auto accept,  0-do not auto accept
//
//        super.addParam("TP", "Default Information Tab"); // template name
//        super.addParam("MTName", "MC: Meeting Center Default"); // Client points out the name of the meeting template that is currently used
//        super.addParam("MTKey", "G;MC;en_US;7.1;0;Meeting Center Default;D; ;"); // Client points out the key of the meeting template that is currently used 
//
//        // audio info
//        super.addParam("TC", "2"); // Value:0-None, 1-Call in, 2-Call-back, 6 Other telephony, 8 Personal Conference Number, 9 3rd-TSP
//        super.addParam("TF", "1"); // 0/1  1-Toll-free call-in meeting.
//        super.addParam("IP", "1"); // 1-Internet phone, default is 0 
//        super.addParam("ILCI", "1"); // International local call-in
//        super.addParam("JBHT", "1"); // 1/0  join before host for telephony
//        super.addParam("EETone", "0"); // 0/1/2  0-Beep 1-Announce Name 2-No Tone
//
        // email template
        super.addParam("EmailTemplateType", "HTML");
        super.addParam("EmailTemplate", "1");
//
//        // others
//        super.addParam("isUTF8", "1");
//        super.addParam("clientversion", "31.0.0.926");
//        super.addParam("VER", "T21");
//
//        // params from mac PT client.
//        super.addParam("PT", 1); // is from PT client.
//        super.addParam("OS", "mac");
    }

    /* (non-Javadoc)
     * @see com.cisco.rekan.apitest.HttpAPICaller#addParams(java.lang.String[])
     */
    @Override
    public void addParams(String... params) {
        addFixedParams();

        super.addParam("WID", params[0]);
        super.addParam("PWPW", params[1]);
        super.addParam("MN", params[2]);
    }

    public static Calendar getStartCalendar(int dayOffset) {
        final int firstDayOffset = 2; // schedule a meeting after 2 days.

        Calendar startCal = Calendar.getInstance();
        startCal.add(Calendar.DAY_OF_MONTH, firstDayOffset + dayOffset);
        startCal.set(Calendar.HOUR_OF_DAY, 12);
        startCal.set(Calendar.MINUTE, 0);
        startCal.set(Calendar.SECOND, 0);

        return startCal;
    }

    protected void setStartCalendar(Calendar startCal) {

        super.addParam("YE", startCal.get(Calendar.YEAR));
        super.addParam("MO", startCal.get(Calendar.MONTH) + 1);
        super.addParam("DA", startCal.get(Calendar.DAY_OF_MONTH));
        super.addParam("HO", startCal.get(Calendar.HOUR_OF_DAY));
        super.addParam("MI", startCal.get(Calendar.MINUTE));

        return;
    }


    protected void addRepeatedParams4Daily() {
        int meetingDuration = 30;

        // repeated info
        super.addParam("RT", "Daily");
        super.addParam("DU", meetingDuration);
        super.addParam("JodaTimezone", "Asia_Shanghai");

        Calendar startCal = getStartCalendar(0);
        setStartCalendar(startCal);
    }

    /**
     * schedule killers meeting.
     *
     * @param userName webex user name.
     * @param userPlainPassword user plain password.
     * @param meetingTopic meeting topic.
     * @return
     */
    public static String scheduleKillersSeries4PC(String userName, String userPlainPassword, String meetingTopic) {
        UpdatePasswordCaller caller = new UpdatePasswordCaller();

        caller.addParam("MT", String.valueOf(Constants.MEETING_TYPE_ID_TP_PLUS));
        caller.addParam("OA", "158"); // site and user priviledge(bit) 
        caller.addParam("UsePSTN", "1");
        caller.addRepeatedParams4Daily();

        Document scheduleResponse = caller.post4Document(userName, userPlainPassword, meetingTopic);
        String status = scheduleResponse.selectSingleNode("//OutlookScheduleMeeting/Status").getText();
        Assert.assertEquals("SUCCESS", status);
        String strMeetingKey = scheduleResponse.selectSingleNode("//OutlookScheduleMeeting/MK").getText();

        return strMeetingKey;
    }

}

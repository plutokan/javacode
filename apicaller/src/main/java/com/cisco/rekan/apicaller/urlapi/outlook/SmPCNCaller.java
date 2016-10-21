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
import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.AbstractURLAPICaller;

/**
 * <code>SmPCNCaller</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since 7/6/2016
 *
 */
public class SmPCNCaller extends AbstractURLAPICaller {

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
        super.addParam("ExcludePW", "0");
        super.addParam("SupportPKI", "1");
        super.addParam("SupportAES", "1");
        super.addParam("NeedCheckPassword", "1");
        super.addParam("PPW", "44251080");
        super.addParam("ST", "1"); // service type.
        super.addParam("AP", "0"); // if allow Auto Play. 1/0 
        super.addParam("LF", "1"); // List flag, 1-list meeting,  0-unlisted meeting
        super.addParam("AJB", "0"); // Allow join before meeting start. 1-allow, 0-not allow 
        super.addParam("JB", "0"); // Minutes that allow attendee join before meeting start.
        super.addParam("AR", "0"); // Require attendee registration. 1-Require, 0-not Require.
        super.addParam("AQ", "0"); // Auto acccept attendee registration. 1-auto accept,  0-do not auto accept

        super.addParam("TP", "Default Information Tab"); // template name

        // audio info
        super.addParam("TC", "8"); // Value:0-None, 1-Call in, 2-Call-back, 6 Other telephony, 8 Personal Conference Number, 9 3rd-TSP
        super.addParam("TF", "1"); // 0/1  1-Toll-free call-in meeting.
        super.addParam("IP", "1"); // 1-Internet phone, default is 0 
        super.addParam("JBHT", "0"); // 1/0  join before host for telephony
        super.addParam("EETone", "1"); // 0/1/2  0-Beep 1-Announce Name 2-No Tone

        // email template
        super.addParam("EmailTemplateType", "HTML");
        super.addParam("EmailTemplate", "1");

        // others
        super.addParam("isUTF8", "1");
        super.addParam("clientversion", "31.6.0.473");

        // params for PCN
        super.addParam("AAH", "0");
        super.addParam("TA", "1");
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
        final int firstDayOffset = 1; // schedule a meeting after 2 days.

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
        super.addParam("RT", "NoRepeat");
//        super.addParam("DayInterval", "1");
//        super.addParam("Always", "0");
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
     * @return document response.
     */
    public static Document schedulePCN(String userName, String userPlainPassword, String meetingTopic) {
        SmPCNCaller caller = new SmPCNCaller();

        caller.addParam("MT", 16); // PCN
        caller.addParam("OA", "56"); // site and user priviledge(bit) 
        caller.addParam("UsePSTN", "1");
        caller.addRepeatedParams4Daily();

        Document scheduleResponse = caller.post4Document(userName, userPlainPassword, meetingTopic);
        String status = scheduleResponse.selectSingleNode("//OutlookScheduleMeeting/Status").getText();
        Assert.assertEquals("SUCCESS", status);
        logger.debug(getMeetingKey(scheduleResponse));
        logger.debug(getPhoneNumber(scheduleResponse));
        logger.debug(getPhoneNumber4Atd(scheduleResponse));

        return scheduleResponse;
    }

    public static String updatePCN(String userName, String userPlainPassword, String meetingTopic,
            String strMeetingKey, String password) {
        SmPCNCaller caller = new SmPCNCaller();

        caller.addParam("MK", strMeetingKey);
        caller.addParam("MT", 16); // PCN
        caller.addParam("OA", "56"); // site and user priviledge(bit) 
        caller.addParam("UsePSTN", "1");
        caller.addRepeatedParams4Daily();
        caller.addParam("PPW", password);

        Document scheduleResponse = caller.post4Document(userName, userPlainPassword, meetingTopic);
        String status = scheduleResponse.selectSingleNode("//OutlookScheduleMeeting/Status").getText();
        Assert.assertEquals("SUCCESS", status);
        strMeetingKey = getMeetingKey(scheduleResponse);
        System.out.println(getPhoneNumber(scheduleResponse));
        System.out.println(getPhoneNumber4Atd(scheduleResponse));

        return strMeetingKey;
    }

    @Test
    public void test() {
        String userName = "lyn";
        String userPlainPassword = "P@ss123";
        Document doc = SmPCNCaller.schedulePCN(userName, userPlainPassword, "Pluto test PCN 010");
//        String strMeetingKey = getMeetingKey(doc);
//        SmPCNCaller.updatePCN(userName, userPlainPassword, "Pluto test PCN 002-update", strMeetingKey, "23026936");
    }

    private static String getMeetingKey(Document doc) {
        if (null == doc) {
            return null;
        }

        return doc.selectSingleNode("//OutlookScheduleMeeting/MK").getText();
    }

    private static String getPhoneNumber(Document doc) {
        if (null == doc) {
            return null;
        }

        return doc.selectSingleNode("//OutlookScheduleMeeting/PN").getText();
    }

    private static String getPhoneNumber4Atd(Document doc) {
        if (null == doc) {
            return null;
        }

        return doc.selectSingleNode("//OutlookScheduleMeeting/PNA").getText();
    }

}

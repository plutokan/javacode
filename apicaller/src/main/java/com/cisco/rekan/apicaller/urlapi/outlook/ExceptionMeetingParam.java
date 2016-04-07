/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * Building D1, Innovation Park, 800 Wangjiang Xi Road, High-tech Zone, Hefei City,
 * Anhui Province, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.outlook;


/**
 * <code>ExceptionMeetingParam</code>
 *
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since apicaller Apr 7, 2016
 *
 */
public class ExceptionMeetingParam {

    private String actionType;
    private String exceptionMeetingKey;
    private String originalStartTime;

    /**
     * @param actionType
     * @param exceptionMeetingKey
     * @param originalStartTime
     */
    public ExceptionMeetingParam(String actionType, String exceptionMeetingKey, String originalStartTime) {
        super();
        this.actionType = actionType;
        this.exceptionMeetingKey = exceptionMeetingKey;
        this.originalStartTime = originalStartTime;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("<ExceptionMeeting>");
        sb.append("<AT>");
        sb.append(this.actionType);
        sb.append("</AT>");
        sb.append("<MK>");
        sb.append(this.exceptionMeetingKey);
        sb.append("</MK>");
        sb.append("<OriginalStartTime>");
        sb.append(this.originalStartTime);
        sb.append("</OriginalStartTime>");
        sb.append("</ExceptionMeeting>");

        return sb.toString();
    }

}

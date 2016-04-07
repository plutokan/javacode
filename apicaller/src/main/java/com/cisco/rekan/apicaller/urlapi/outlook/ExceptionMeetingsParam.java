/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * Building D1, Innovation Park, 800 Wangjiang Xi Road, High-tech Zone, Hefei City,
 * Anhui Province, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.outlook;

import java.util.ArrayList;
import java.util.List;

/**
 * <code>ExceptionMeetingsParam</code>
 *
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since apicaller Apr 7, 2016
 *
 */
public class ExceptionMeetingsParam {

    List<ExceptionMeetingParam> exceptionMeetings = new ArrayList<ExceptionMeetingParam>();

    public void add(ExceptionMeetingParam param) {
        this.exceptionMeetings.add(param);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("<ExceptionMeetingList>");
        sb.append("<Count>");
        sb.append(this.exceptionMeetings.size());
        sb.append("</Count>");

        for (ExceptionMeetingParam param : exceptionMeetings) {
            sb.append(param.toString());
        }

        sb.append("</ExceptionMeetingList>");
        return sb.toString();
    }

}

/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * Building D1, Innovation Park, 800 Wangjiang Xi Road, High-tech Zone, Hefei City,
 * Anhui Province, China All rights reserved.
 */
package com.cisco.rekan.calendar;

import static java.lang.System.out;

import java.util.Calendar;

/**
 * <code>TempTest</code>
 *
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since learning Apr 7, 2016
 *
 */
public class TempTest {

    public static void main(String[] args) {
        long currentTimes = System.currentTimeMillis();
        out.println();

        Calendar currentCal = Calendar.getInstance();
        out.println(currentCal.get(Calendar.MONTH));
        out.println(currentCal.get(Calendar.DATE));

    }

}

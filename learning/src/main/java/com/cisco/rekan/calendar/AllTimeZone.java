package com.cisco.rekan.calendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class AllTimeZone {
    public static void main(String[] avgs) {
        File f = new File("D:\\WebEx\\GW-WM\\CWNMS_1_2\\AllTimeZones_1315.txt");
        try {
            f.createNewFile();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        String[] tzArray = TimeZone.getAvailableIDs();
        /*String[] tzArray = {"Etc/GMT+12", "Pacific/Samoa", "Pacific/Honolulu", "America/Anchorage",
                        "US/Arizona", "America/Los_Angeles", "America/Denver", "Canada/Saskatchewan",
                        "America/Bogota", "America/Chicago", "US/East-Indiana", "America/Mexico_City",
                        "America/La_Paz", "America/New_York", "America/Sao_Paulo", "America/Buenos_Aires",
                        "America/Halifax", "Canada/Newfoundland", "Atlantic/South_Georgia", "Atlantic/Azores",
                        "Africa/Casablanca", "Europe/London", "Europe/Amsterdam", "Europe/Berlin",
                        "Africa/Cairo", "Europe/Prague", "Africa/Johannesburg", "Israel",
                        "Europe/Athens", "Europe/Helsinki", "Africa/Nairobi", "Asia/Riyadh",
                        "Asia/Tehran", "Asia/Muscat", "Europe/Moscow", "Asia/Kabul",
                        "Asia/Baku", "Asia/Yekaterinburg", "Asia/Karachi", "Asia/Calcutta",
                        "Asia/Colombo", "Asia/Almaty", "Asia/Bangkok", "Asia/Hong_Kong",
                        "Australia/Perth", "Asia/Singapore", "Asia/Taipei", "Asia/Seoul",
                        "Asia/Tokyo", "Asia/Yakutsk", "Australia/Adelaide", "Australia/Darwin",
                        "Australia/Brisbane", "Pacific/Guam", "Australia/Hobart", "Australia/Sydney",
                        "Etc/GMT-11", "Asia/Vladivostok", "Pacific/Fiji", "NZ",
                        "Europe/Paris", "Europe/Bucharest", "Europe/Oslo", "Europe/Stockholm",
                        "America/Tijuana", "America/Chihuahua", "America/Caracas", "Asia/Kuala_Lumpur"};
                        */
        for (String tzID : tzArray) {
            TimeZone tz = TimeZone.getTimeZone(tzID);
            //byte[] b = (tz.toString() + "\n").getBytes();
            long currentTime = System.currentTimeMillis();
            int offset = tz.getOffset(currentTime);
            //String name1 = tz.getDisplayName(Locale.US) + "\n";
            //String name2 = tz.getDisplayName(true, TimeZone.SHORT, Locale.US) + "GMT" + offset + "\n";
            //String name3 = tz.getDisplayName(true, TimeZone.LONG, Locale.US) + "GMT" + offset + "\n";
            //String name4 = tz.getDisplayName(false, TimeZone.SHORT, Locale.US) + "GMT" + offset + "\n";
            //String name5 = tz.getDisplayName(false, TimeZone.LONG, Locale.US) + "GMT" + offset + "\n";
            //byte[] b = (name1 + name2 + name3 + name4 + name5 + "\n").getBytes();
            boolean isDaylightTime = tz.inDaylightTime(new Date(currentTime));
            byte[] b = (tzID + "(" + tz.getDisplayName(isDaylightTime, TimeZone.SHORT, Locale.US)
                            + ", " + tz.getDisplayName(isDaylightTime, TimeZone.LONG, Locale.US)
                            + ", GMT" + offset + ")\n").getBytes();
            try {
                fos.write(b);
                fos.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

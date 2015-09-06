/*
 * Created on 2004-6-20
 * 
 * ������ԣ����main�̻߳��׳��쳣
 *
 */
package com.cisco.rekan.calendar;

import java.text.SimpleDateFormat;
import java.util.*;
/**
 * @author rhkan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CalendarExample1 {
	public static SimpleDateFormat fmDateTimes=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US);
	
	public static void main(String[] args) {
		/*for (int i=0; i<12; i++) {
			//GregorianCalendar cal = new GregorianCalendar(2006, i, 1);
			GregorianCalendar cal = new GregorianCalendar();
			cal.set(GregorianCalendar.YEAR, 2006);
			cal.set(GregorianCalendar.MONTH, i);
			System.out.println(cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
		}*/
		//cal.set(Calendar.MONTH, 2);
		//System.out.println(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		//cal.setTimeInMillis(System.currentTimeMillis());
		//System.out.println(cal);
		
	    System.out.println(fmDateTimes.format(null));
	    
		try {
			Date d = fmDateTimes.parse("11/29/2006 14:50:00");
			System.out.println(compareAttendeeJoinTime(d));
			d = fmDateTimes.parse("11/29/2006 14:40:00");
			System.out.println(compareAttendeeJoinTime(d));
		} catch (Exception e) {
		}
	}


	public static boolean compareAttendeeJoinTime(Date joinTime) {
		if (joinTime==null) {
			return false;
		}
		
		Calendar calJoinTime = Calendar.getInstance();
		calJoinTime.setTime(joinTime);
		System.out.println(calJoinTime);
		Calendar calNowTime = Calendar.getInstance();
		calNowTime.add(Calendar.MINUTE, -20);
		System.out.println(calNowTime);
		if (calJoinTime.compareTo(calNowTime) > 0) {
			return true;
		} else {
			return false;
		}
	}
}


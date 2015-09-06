package com.cisco.rekan.calendar;

import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class DateTime {
    private String year;
    private String month;
    private String day;
    private String hour;
    private String minute;
    private String am_pm;

    public static SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddKKmma", Locale.US);
    
    public DateTime() {
    }

    public DateTime(String year, String month, String day, String hour, String minute, String am_pm) {
    	this.year = year;
    	this.month = month;
    	this.day = day;
    	this.hour = hour;
    	this.minute = minute;
    	this.am_pm = am_pm;
    }
    
    public DateTime(Date date) {
    	String sDate = dateFormat.format(date);
    	this.year = sDate.substring(0, 4);
    	this.month = sDate.substring(4, 6);
    	this.day = sDate.substring(6, 8);
    	this.hour = sDate.substring(8, 10);
    	this.minute = sDate.substring(10, 12);
    	this.am_pm = sDate.substring(12, sDate.length());
    }
    
    public String getAm_pm() {
		return am_pm;
	}
	public void setAm_pm(String am_pm) {
		this.am_pm = am_pm;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getMinute() {
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
    
	public Date toDate() {
		try {
			return dateFormat.parse(this.toString());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String toString() {
		return year+month+day+hour+minute+am_pm;
	}
	
	public DateTime clone() {
		return new DateTime(year, month, day, hour, minute, am_pm); 
	}
}

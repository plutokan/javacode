/*
 * @(#)CalendarTest.java 1.0 11/16/07
 * 
 * java.util.Calendar, Locale and Date test Class
 */
package com.cisco.rekan.calendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.System.out;

/**
 * java.util.Locale test Class
 * 
 * @version 1.0 11/16/07
 * @author rhkan
 */
class LocaleTest {

	/** Test the difference of two locale */
	public void test1() {
		Locale localeEN = new Locale("en", "US");
		out.println("Display Name: " + localeEN.getDisplayName());
		out.println("Country: " + localeEN.getCountry());
		out.println("Language: " + localeEN.getLanguage());
		
		Locale localeFR = new Locale("fr", "FR");
		out.println("Display Name: " + localeFR.getDisplayName());
		out.println("Country: " + localeFR.getCountry());
		out.println("Language: " + localeFR.getLanguage());
	}
	
	/** Test 2 */
	public void test2() {
		Date date = new Date();
		Locale localeFR = Locale.FRANCE;
		Locale localeEN = Locale.US;
		DateFormat fullDateFormatFR = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, localeFR);
		DateFormat fullDateFormatUS = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, localeEN);
		
		out.println("Locale: " + localeFR.getDisplayName());
		out.println(fullDateFormatFR.format(date));
		out.println("Locale: " + localeEN.getDisplayName());
		out.println(fullDateFormatUS.format(date));
	}

}

/**
 * java.util.Calendar test Class
 * 
 * @version 1.0 11/16/07
 * @author rhkan
 */
public class CalendarTest {

	/**
	 * ���Ǵ� Calendar.getInstance() �����õ�ʵ�����һ�� "GreogrianCalendar" ����
	 * Ҳ���������ڽ������ʱ���� "��Ԫ������"��(����ͨ�� new GregorianCalendar() ��õĽ��һ��)��
	 */
	public void test1() {
		Calendar calendar = Calendar.getInstance();
		if (calendar instanceof GregorianCalendar) {
			System.out.println("It is an instance of GregorianCalendar");
		}
	}
	
	/** test 2 */
	public void test2() {
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
		
		// Create our Gregorian Calendar. 
		GregorianCalendar cal = new GregorianCalendar(); 
		// Set the date and time of our calendar to the system's date and time 
		cal.setTime(new Date());
		System.out.println("System Date: " + dateFormat.format(cal.getTime())); 
		// Set the day of week to FRIDAY 
		cal.set(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.FRIDAY); 
		System.out.println("After Setting Day of Week to Friday: " + 
		dateFormat.format(cal.getTime()));
		int friday13Counter = 0; 
		while (friday13Counter <= 10) {
			// Go to the next Friday by adding 7 days. 
			cal.add(GregorianCalendar.DAY_OF_MONTH,7); 
			// If the day of month is 13 we have another Friday the 13th. 
			if (cal.get(GregorianCalendar.DAY_OF_MONTH) == 13) { 
				friday13Counter++; 
				System.out.println(dateFormat.format(cal.getTime())); 
			} 
		} 
	}
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
        Calendar c = Calendar.getInstance();
        System.out.println(c.get(Calendar.DATE));System.out.println(c.getTimeInMillis());System.out.println(c.getTime());
        c = Calendar.getInstance(TimeZone.getTimeZone("Asia/Chongqing"));
        System.out.println(c.get(Calendar.DATE));System.out.println(c.getTimeInMillis());System.out.println(c.getTime());
        c = Calendar.getInstance(TimeZone.getTimeZone("US/Pacific"));
        System.out.println(c.get(Calendar.DATE));System.out.println(c.getTimeInMillis());System.out.println(c.getTime());
		
		LocaleTest lt = new LocaleTest();
		lt.test1();
		lt.test2();
		
		CalendarTest ct = new CalendarTest();
		ct.test1();
		ct.test2();
		
		DateFormatTest dft = new DateFormatTest();
		dft.test1();
		dft.test2();
		dft.test3();
		
		// �õ�����ִ�е�ʱ��
		System.out.println("��Ҫ "+(System.currentTimeMillis()-start)+" ΢��");
	}

}

/**
 * java.util.Locale test Class
 * 
 * @version 1.0 11/16/07
 * @author rhkan
 */
class DateFormatTest {

	/** Test 1 */
	public void test1() {
    	Date date = new Date();
    	System.out.println(date);
    	SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy", Locale.US);
    	Calendar c = Calendar.getInstance();
    	c.set(2006, 9, 18);
    	System.out.println(sdf.format(date));
    	sdf.setCalendar(c);
    	System.out.println(sdf.format(date));
    	System.out.println(sdf.toPattern());
    	System.out.println(sdf.toString());
    	
    	SimpleDateFormat fm=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US);
    	fm.setLenient(false);
    	try {
    		System.out.println(fm.parse("2/4/369 3:5:3"));
    	} catch (ParseException e) {
    		System.out.println(e);
    	}
	}
	
	/** test 2 */
	public void test2() {
		  Date date = new Date(); 
		  DateFormat shortDateFormat = 
		  DateFormat.getDateTimeInstance( 
		  DateFormat.SHORT, 
		  DateFormat.SHORT); 
		  DateFormat mediumDateFormat = 
		  DateFormat.getDateTimeInstance( 
		  DateFormat.MEDIUM, 
		  DateFormat.MEDIUM); 

		  DateFormat longDateFormat = 
		  DateFormat.getDateTimeInstance( 
		  DateFormat.LONG, 
		  DateFormat.LONG); 

		  DateFormat fullDateFormat = 
		  DateFormat.getDateTimeInstance( 
		  DateFormat.FULL, 
		  DateFormat.FULL); 
		  System.out.println(shortDateFormat.format(date)); 
		  System.out.println(mediumDateFormat.format(date)); 
		  System.out.println(longDateFormat.format(date)); 
		  System.out.println(fullDateFormat.format(date)); 
	}
	
	/** test 3 */
	public void test3() {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("EEEE-MMMM-dd-yyyy"); 
		Date date = new Date(); 
		System.out.println(bartDateFormat.format(date)); 
	}
}
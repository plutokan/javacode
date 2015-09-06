package com.cisco.rekan.IO.serial;
import java.util.Date;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class UnSerializeDate {
	public UnSerializeDate() throws IOException {
		Date d = null;
		
		FileInputStream f = null;
		ObjectInputStream s = null;
		try {
			f = new FileInputStream("date.ser");
			s = new ObjectInputStream(f);
			d = (Date) s.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (java.lang.ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
		    s.close();
            f.close();
		}
			
		System.out.println("Date: " + d.toString());
	}
	
	public static void main(String[] args) throws IOException {
		new UnSerializeDate();
	}
}
package com.cisco.rekan.IO.serial;
import java.util.Date;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class SerializeDate {
	public SerializeDate() {
		Date d = new Date();
		try {
			FileOutputStream f = new FileOutputStream("date.ser");
			ObjectOutputStream s = new ObjectOutputStream(f);
			s.writeObject(d);
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main (String[] args) {
		new SerializeDate();
	}
}
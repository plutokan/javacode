package com.cisco.rekan.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class ProcessTest {
    public static void main(String args[]) throws IOException {
    	for (Map.Entry<String, String> entry: System.getenv().entrySet()) {
	        System.out.println(entry.getKey() + " / " + entry.getValue());
	    }
	  
	    //Process p = new ProcessBuilder("ipconfig").start();
	    Process p = Runtime.getRuntime().exec("ipconfig");
	    InputStream is = p.getInputStream();
	    BufferedReader br = new BufferedReader(new InputStreamReader(is));
	    String line;
	    while ((line = br.readLine()) != null) {
	    	System.out.println(line);
	    }
    
    }
}
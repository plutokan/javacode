/*
 * @(#)PrintServerTest.java 1.0 11/26/2007
 * 
 * Copyright 2007 Cisco-WebEx, Inc. All rights reserved.
 * 
 * Cisco-WebEx (HF) TEO QA Java Reading Party
 *   ( From "The Java Programming Language - Fourth Edition" chapter 14)
 */

package com.cisco.rekan.thread;

import java.util.LinkedList;
import java.util.List;

import static java.lang.System.out;

class PrintJob {
	
}

class PrintQueue {
	List<PrintJob> list = new LinkedList<PrintJob>();
	
	public PrintJob remove() {
		//return list.remove(0);
		return null;
	}
	
	public void add(PrintJob job) {
		list.add(job);
	}
}

class PrintServer implements Runnable {
	private final PrintQueue requests = new PrintQueue();
	
	public PrintServer() {
		new Thread(this).start();
	}
	
	public void print(PrintJob job) {
		requests.add(job);
	}
	
	public void run() {
		for (;;) {
			realPrint(requests.remove());
		}
	}
	
	private void realPrint(PrintJob job) {
		// do the real work of printing
	}
}

class PrintServer2{
	private final PrintQueue requests = new PrintQueue();
	
	public PrintServer2() {
		Runnable service = new Runnable() {
			public void run() {
				for (;;) {
					realPrint(requests.remove());
				}
			}
		};
		new Thread(service).start();
	}
	
	public void print(PrintJob job) {
		requests.add(job);
	}
	
	private void realPrint(PrintJob job) {
		// do the real work of printing
	}
}

/**
 * Exercise 14.2: Modify the first version of PrintServer so that only the thread created in the 
 *                constructor can successfully execute run, using the identity of the thread as suggested.
 * 
 * @author pluto
 */
class PrintServer3 implements Runnable {
	private final PrintQueue requests = new PrintQueue();
	private long thread_ID_;
	
	public PrintServer3() {
		Thread t = new Thread(this);
		thread_ID_ = t.getId();
		t.start();
	}
	
	public void print(PrintJob job) {
		requests.add(job);
	}
	
	public void run() throws IllegalThreadStateException {
		long thread_ID_ = Thread.currentThread().getId();
		if (thread_ID_ == this.thread_ID_) {
			//for (;;) {
				realPrint(requests.remove());
			//}
		} else {
			throw new IllegalThreadStateException();
		}
	}
	
	private void realPrint(PrintJob job) {
		// do the real work of printing
		out.println("Print okay!");
	}
}

public class PrintServerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// test for Exercise 14.2
		PrintServer3 server = new PrintServer3();
		server.run();
		
	}

}

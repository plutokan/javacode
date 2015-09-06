/*
 * @(#)Exercise13.java 1.0 11/16/07
 * 
 * Copyright 2007 Cisco-WebEx, Inc. All rights reserved.
 * 
 * Cisco-WebEx (HF) TEO QA Java Reading Party
 * Exercise 13:
 *   ( From "The Java Programming Language - Fourth Edition" )
 *   Exercise 14.3: Write a class whose objects hold a current value and have a
 *     method that will add to that value, printing the new value. 
 *     Write a program that creates such an object, creates multiple threads, 
 *     and invokes the adding method repeatedly from each thread. 
 *     Write the class so that no addition can be lost.
 *   Exercise 14.4: Modify your code from Exercise 14.3 to use static data and methods.
 *   Exercise 14.5: Modify your code from Exercise 14.4 so that threads can safely 
 *     decrement the value without using a static synchronized method.
 */

package com.cisco.rekan.thread;

/**
 * Exercise 14.3
 * 
 * @author pluto
 *
 */
public class Exercise13 implements Runnable {

	private boolean flag;
	private int count_;
	
	private final int COUNTS_ADD_ = 100;
	private final int MAX_SLEEP_TIME_ = 100;
	
	Exercise13() {
		flag = true;
		count_ = 0;
	}
	
	public synchronized void add() {
		if (count_ == Integer.MAX_VALUE) {
			throw new RuntimeException();
		}
		if (count_ == COUNTS_ADD_ -1) {
			flag = false;
		} else if (count_ > COUNTS_ADD_ - 1) {
			System.out.println(Thread.currentThread().getId() + ": over range!");
			return;
		}
		int i = count_ + 1;
		try {
			Thread.sleep(new java.util.Random().nextInt(MAX_SLEEP_TIME_));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		count_ = i;
		System.out.println(Thread.currentThread().getId() + ": " + count_);
	}
	
	public void run() {
		while (flag) {
			add();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Exercise13 e = new Exercise13();
		new Thread(e).start();
		new Thread(e).start();
		new Thread(e).start();
		
		//new Thread(new Exercise13_2()).start();
		//new Thread(new Exercise13_2()).start();
		//new Thread(new Exercise13_2()).start();
		
		//new Thread(new Exercise13_3()).start();
		//new Thread(new Exercise13_3()).start();
		//new Thread(new Exercise13_3()).start();
	}

}

/**
 * Exercise 14.4
 * 
 * @author pluto
 *
 */
class Exercise13_2 implements Runnable {

	public static int count2_ = 0;
	private final static int MAX_SLEEP_TIME_ = 100;
	
	public synchronized static void add2() {
		if (count2_ == Integer.MAX_VALUE) {
			throw new RuntimeException();
		}
		int i = count2_ + 1;
		try {
			Thread.sleep(new java.util.Random().nextInt(MAX_SLEEP_TIME_));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		count2_ = i;
		System.out.println(Thread.currentThread().getId() + ": " + count2_);
	}
	
	public void run() {
		for (int i=0; i<500; i++) {
			add2();
		}
	}
	
}

/**
 * Exercise 14.5
 * 
 * @author pluto
 *
 */
class Exercise13_3 implements Runnable {

	public static Integer count2_ = 0;
	private final int MAX_SLEEP_TIME_ = 100;
	
	public void add2() {
		//synchronized (count2_) {    // Note: This can not get the synch result.
		synchronized (Exercise13_3.class) {
			if (count2_ == Integer.MAX_VALUE) {
				throw new RuntimeException();
			}
			int i = count2_ + 1;
			try {
				Thread.sleep(new java.util.Random().nextInt(MAX_SLEEP_TIME_));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count2_ = i;
			System.out.println(Thread.currentThread().getId() + ": " + count2_);
		}
	}
	
	public void run() {
		for (int i=0; i<500; i++) {  // Do not use 500 when coding
			add2();
		}
	}
	
}


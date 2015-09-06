/*
 * Copyright (C) Cisco-WebEx (China) Software Co., Ltd. HeFei Branch
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */

package com.cisco.rekan.thread;


/**
 * <code>InterruptTest</code>
 * 
 * This test indicate that "interrupt" method can wait the thread enter "join/wait/sleep" method.
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyJavaCode 2011-9-7
 * 
 */
public class InterruptTest {


class T1 extends Thread {
    
    @Override
    public void run() {
        long count = 0;
        while (count < 1000000000L) {
            count++;
        }
        
        if (Thread.interrupted()) {
            System.out.println("Other thread want interrupt this thread. Ignore the interrupt single!");
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(System.nanoTime() + " InterruptedException count: " + count);
        }
        System.out.println("count: " + count);
    }
    
}
    public static void main(String[] args) {
        InterruptTest test = new InterruptTest();
        Thread thread1 = test.new T1();
        thread1.start();
        
        thread1.interrupt();
        if (thread1.isAlive()) {
            System.out.println(System.nanoTime() + " Cannot interrupt thread 1.");
        }
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
//        System.out.println(thread1.isAlive());
        thread1.start();
        
        System.out.println("Exit now.");
    }
    
}

// : c14:SimpleThread.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Very simple Threading example.

package com.cisco.rekan.thread;

public class SimpleThread extends Thread {

    private int countDown = 5;

    private static int threadCount = 0;

    private int threadNumber = ++threadCount;

    public SimpleThread() {
        System.out.println("Making " + threadNumber);
    }

    public void run() {
        while (true) {
            System.out.println("Thread " + threadNumber + "(" + countDown + ")");
            if (--countDown == 0)
                return;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++)
            new SimpleThread().start();
        System.out.println("All Threads Started");
    }
} // /:~

class Semaphore {

    private int count;

    public Semaphore(int n) {
        this.count = n;
    }

    public synchronized void acquire() {
        while (count == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                // keep trying
            }
        }
        count--;
    }

    public synchronized void release() {
        count++;
        notify(); //alert a thread that's blocking on this semaphore
    }
}

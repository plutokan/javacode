package com.cisco.rekan.collections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Producer extends Thread implements Runnable {
    private final BlockingQueue<Object> queue;
	private int count;
    
    Producer(BlockingQueue<Object> q, int i) {
    	queue = q;
    	count = i;
    }
	
    Producer(BlockingQueue<Object> q) {
    	queue = q;
    	count = 0;
    }
	
	public void run() {
	    try {
	        while(true) {
	        	sleep(5000);
	        	System.out.println("Producer");
	        	queue.put(produce());
	        }
	    } catch (InterruptedException ex) {
	    	ex.printStackTrace();
	    }
	}
	
	Object produce() {
    	count++;
		return "Product" + count;
	}
}

class Consumer implements Runnable {
	private final BlockingQueue queue;
	
	Consumer(BlockingQueue q) {
		queue = q;
	}
	
	public void run() {
	    try {
	        while(true) {
	        	System.out.println("Consumer");
	    	    consume(queue.take());
	        }
	    } catch (InterruptedException ex) {
	    	ex.printStackTrace();
	    }
	}
	
	void consume(Object x) {
		System.out.println(x);
	}
}

public class BlockingQueueTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	     BlockingQueue<Object> q = new LinkedBlockingQueue<Object>(3);
	     Producer p = new Producer(q);
	     Consumer c1 = new Consumer(q);
	     Consumer c2 = new Consumer(q);
	     new Thread(p).start();
	     new Thread(c1).start();
	     new Thread(c2).start();
	}

}

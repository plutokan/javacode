package com.cisco.rekan.collections;

import java.util.*;
import java.util.concurrent.*;
public class Delay {
  /**
   * Delayed implementation that actually delays
   */
  static class NanoDelay implements Delayed { 
    long trigger;
    NanoDelay(long i) { 
      trigger = System.nanoTime() + i;
    }
    public int compareTo(Delayed y) {
      long i = trigger;
      long j = ((NanoDelay)y).trigger;
      if (i < j) return -1;
      if (i > j) return 1;
      return 0;
    }
    public boolean equals(Object other) {
      return ((NanoDelay)other).trigger == trigger;
    }
    public boolean equals(NanoDelay other) {
      return ((NanoDelay)other).trigger == trigger;
    }
    public long getDelay(TimeUnit unit) {
      long n = trigger - System.nanoTime();
      return unit.convert(n, TimeUnit.NANOSECONDS);
    }
    public long getTriggerTime() {
      return trigger;
    }
    public String toString() {
      return String.valueOf(trigger);
    }
  }
  public static void main(String args[]) throws InterruptedException {
    Random random = new Random();
    DelayQueue<NanoDelay> queue = new DelayQueue<NanoDelay>();
    for (int i=0; i < 5; i++) {
      queue.add(new NanoDelay(random.nextInt(1000)));
    }
    long last = 0;
    for (int i=0; i < 5; i++) {
      NanoDelay delay = (NanoDelay)(queue.take());
      long tt = delay.getTriggerTime();
      System.out.println("Trigger time: " + tt);
      if (i != 0) {
        System.out.println("Delta: " + (tt - last));
      }
      if (i == 0)
      last = tt;
    }
    
    Collection<String> c = Collections.checkedCollection(new HashSet<String>(), String.class);
    c.add("a");
  }
}
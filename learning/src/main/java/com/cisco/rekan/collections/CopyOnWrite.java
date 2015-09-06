package com.cisco.rekan.collections;

import java.util.*;
import java.util.concurrent.*;
public class CopyOnWrite {
  @SuppressWarnings("unchecked")
public static void main(String args[]) {
    List list1 = new CopyOnWriteArrayList(Arrays.asList(args));
    List list2 = new ArrayList(Arrays.asList(args));
    Iterator itor1 = list1.iterator();
    Iterator itor2 = list2.iterator();
    list1.add("New");
    list2.add("New");
    try {
      printAll(itor1);
    } catch (ConcurrentModificationException e) {
      System.err.println("Shouldn't get here");
    }
    try {
      printAll(itor2);
    } catch (ConcurrentModificationException e) {
      System.err.println("Will get here.");
    }
  }
  private static void printAll(Iterator itor) {
    while (itor.hasNext()) {
      System.out.println(itor.next());
    }
  }
}

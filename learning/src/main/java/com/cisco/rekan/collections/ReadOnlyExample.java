package com.cisco.rekan.collections;

import java.util.*;

public class ReadOnlyExample {
  public static void main(String args[]) {
    Set<String> set = new HashSet<String>();
    set.add("Bernadine");
    set.add("Elizabeth");
    set.add("Gene");
    set.add("Elizabeth");
    set = Collections.unmodifiableSet(set);
    set.add("Clara");
  }
}
package com.cisco.rekan;


public class TestSplitString {
    public static void main(String[] args) {
    	String s1=";a;b;c;;";
    	String[] sa1 = s1.split(";");
		for (int i=0; i<sa1.length; i++) {
			System.out.println("sa1:" + sa1[i]);
		}
    }
}

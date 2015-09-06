/*
 * @(#)Exercise12.java 1.0 11/15/07
 * 
 * Copyright 2007 Cisco-WebEx, Inc. All rights reserved.
 * 
 * Cisco-WebEx (HF) TEO QA Java Reading Party
 * Exercise 12:
 *   From "The Java Programming Language - Fourth Edition"
 *   Exercise 22.1: Write a method that takes an array of floating-point values
 *     and a number indicating how many columns to use, and prints the array
 *     contents. Try to ensure that the entries in each column line up neatly.
 *     Assume a line is 80 characters wide.
 */

package com.cisco.rekan.format;

import java.util.Formatter;
import java.util.Locale;

public class Exercise12 {

	public static final int LINE_COLUMNS_ = 80;
	public static final int COLUMNS_NUMBER_ = 8;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Float[] array = {123.12343343f, -0.58323385f, 3.14159f, -33535.12349957f,
				3355.82364f, 0.000000012344f, 0.00000000974f, 234.234f,
				23422349.7689f};
		printFloatArray(array, 2);
		System.out.println();
		printFloatArray(array, 4);
		System.out.println();
		printFloatArray(array, 8);
		System.out.println();
	}
	
	public static void printFloatArray(Float[] array, int columns) {
		if (columns > COLUMNS_NUMBER_) {
			System.out.println("Parameter column number is bigger than " + COLUMNS_NUMBER_ + "!");
			return;
		}
		Formatter format = new Formatter(java.lang.System.out, Locale.US);
		int i1 = LINE_COLUMNS_/columns;
		
		int i = 0;
		for (Float f : array) {
			format.format("%+" + i1 + ".3f", f);
			i++;
			if (i % columns == 0) {
				format.format("%n");
			}
		}
	}

}

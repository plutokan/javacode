package com.cisco.rekan.collections;

import java.lang.reflect.Array;

/**
 * @author rhkan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TestArray {

	static int i;
	//private static String a[][] = new String[2][2];
	private static String a[][] = {{"00","01"},{"10","11"}};
	private static String m[] = {"a","b"};

	public static void main(String[] args) {
		
		String b[][] = new String[2][];
		System.arraycopy(a,0,b,0,a.length);
		//b = (String[][]) a.clone();
		//b[1][1] = "oo";
		printArray(a);
		printArray(b);
		
		String n[] = new String[2];
		System.arraycopy(m,0,n,0,m.length);
		//n = (String[]) m.clone();
		//n[1] = "ok";
		printArray(m);
		printArray(n);
		
		printType(a);
		printType(m);
	}
	
	public static void printArray(String[][] array) {
		System.out.println("print begin -------");
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.println(array[i][j]);
			}
		}
		System.out.println("print end -------");
	}
	
	public static void printArray(String[] array) {
		System.out.println("print begin -------");
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
		System.out.println("print end -------");
	}
	private static void printType (Object object) {
	  Class type = object.getClass();
	  if (type.isArray()) {
		Class elementType = type.getComponentType();
		System.out.println("Array of: " + elementType);
		System.out.println(" Length: " + Array.getLength(object));
	  }
	}
}
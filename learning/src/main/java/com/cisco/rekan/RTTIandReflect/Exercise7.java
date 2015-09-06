/*
 * Cisco-WebEx (HF) TEO QA Java Reading Party
 * Exercise7.java
 *     From "The Java Programming Language - Fourth Edition"
 *     Exercise 16.3, 16.5
 */
package com.cisco.rekan.RTTIandReflect;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//import java.lang.reflect.Field;
import java.lang.reflect.Member;
import static java.lang.System.out;

class ClassContents {
	public static void printMembers(Member[] mems) {
		for (Member m : mems) {
			if (m.getDeclaringClass() == Object.class) {
				continue;
			}
			String decl = m.toString();
			out.print(" . ");
			//out.println(strip(decl, "java.lang."));
			out.println(decl);
		}
	}
}

public class Exercise7 {

	private class A {
		private String a1;
		public String a2;
		public String a3;
		
		public String getA1() {
			return a1;
		}
		public void setA1(String a1) {
			this.a1 = a1;
		}
		public String getA2() {
			return a2;
		}
		public void setA2(String a2) {
			this.a2 = a2;
		}
		public String getA3() {
			return a3;
		}
		public void setA3(String a3) {
			this.a3 = a3;
		}
	}
	
	@Deprecated
	@SuppressWarnings(value="unchecked")
	@TODO("Just a test!")
	public class B extends A {
		private String b1;
		public String b2;
		public String b3;
		
		@Override
		public String getA1() {
			return b1;
		}
		@Override
		public void setA1(String b1) {
			this.b1 = b1;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] arg = {"com.webex.pluto.testRTTIandReflect.Exercise7$B"};
		//String[] arg = {"java.util.HashMap"};
		
		/*
		try {
			Class<?> c = Class.forName(arg[0]);
			out.println(c);
			ClassContents.printMembers(c.getFields());
			out.println();
			ClassContents.printMembers(c.getConstructors());
			out.println();
			ClassContents.printMembers(c.getMethods());
		} catch (ClassNotFoundException e) {
			out.println("unknown class: " + arg[0]);
		}
		*/
		/*// 16.3
		try {
			Class<?> c = Class.forName(arg[0]);
			out.println(c);
			Field[] f1 = c.getFields();
			for (Field f : f1) {
				try {
					c.getDeclaredField(f.getName());
				} catch (NoSuchFieldException e) {
					out.println(" . " + f.toString());
					continue;
				}
			}
			out.println();
			ClassContents.printMembers(c.getDeclaredFields());
		} catch (ClassNotFoundException e) {
			out.println("unknown class: " + arg[0]);
		}
		*/
		// 16.5
		try {
			Class<?> c = Class.forName(arg[0]);
			out.println(c);
			Annotation[] a = c.getAnnotations();
			for (Annotation ta : a) {
				out.println(ta.toString());
			}
			out.println();
			ClassContents.printMembers(c.getFields());
			out.println();
			ClassContents.printMembers(c.getConstructors());
			out.println();
			ClassContents.printMembers(c.getMethods());
		} catch (ClassNotFoundException e) {
			out.println("unknown class: " + arg[0]);
		}
		
	}

}

/**
 * Annotation type to indicate a task still needs to be completed
 */
@Target({ElementType.TYPE,
         ElementType.METHOD,
         ElementType.CONSTRUCTOR,
         ElementType.ANNOTATION_TYPE})
@Retention(/*RetentionPolicy.SOURCE, RetentionPolicy.CLASS, */RetentionPolicy.RUNTIME)
@Documented
@interface TODO {
  String value();
}

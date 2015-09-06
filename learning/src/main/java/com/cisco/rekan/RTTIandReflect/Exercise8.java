/*
 * Cisco-WebEx (HF) TEO QA Java Reading Party
 * Exercise8.java
 *     From "The Java Programming Language - Fourth Edition"
 *     Exercise 16.6, 16.8, 16.10
 */
package com.cisco.rekan.RTTIandReflect;

import static java.lang.System.out;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

class C {
	public int i1;
	public int i2;
	
	public C() {
		i1 = 45;
		i2 = 46;
	}
	public C(int i) throws java.util.NoSuchElementException {
		if (i<=0) {
			throw new java.util.NoSuchElementException();
		} else {
			i1 = i;
		}
	}
	public C(int i1, int i2) {
		this.i1 = i1;
		this.i2 = i2;
	}
	
	@Override
	public String toString() {
		return i1 + "|" + i2;
	}
	
	public void printOne() {
		out.println("printOne");
	}
	
	public static <T extends C> T[] createArray(Class<T> type, int len) {
		@SuppressWarnings("unchecked")
		T[] arr = (T[]) Array.newInstance(type, len);
		for (int i=0; i<len; i++) {
			try {
				arr[i] = type.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}
	
	// get a string from an single dimension array
	public static <T> String arrayToString(T[] array) {
		String s = "";
		for (int i=0; i<array.length; i++) {
			s += "(" + i + ")" + array[i].toString();
		}
		return s;
	}
	public int getI2() {
		return i2;
	}
	public void setI2(int i2) {
		this.i2 = i2;
	}
}

public class Exercise8 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 16.6 �������������͵Ķ���ͬʱ�����û���鲢�޸ĸö�����ֶ�
		String cName = "com.webex.pluto.testRTTIandReflect.C";
		try {
			Class<?> c = Class.forName(cName);
			C tempc = (C) c.newInstance();
			
			Field i1 = c.getField("i1");
			Field i2 = c.getField("i2");
			out.println("temp class i1 field is " + i1.get(tempc));
			out.println("temp class i2 field is " + i2.get(tempc));
			
			i1.set(tempc, 34);
			i2.set(tempc, 35);
			out.println("temp class i1 field is " + i1.get(tempc));
			out.println("temp class i2 field is " + i2.get(tempc));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		
		// 16.8 �����κ���Ĺ�����������ʾ�����쳣������ɹ�����ö����ϵķ�����
		String cName2 = "com.webex.pluto.testRTTIandReflect.C";
		try {
			Class<?> class2 = Class.forName(cName2);
			Constructor[] cons = class2.getConstructors();
			for (Constructor con : cons) {
				// Print the exception throw by constructor
				Type[] exceType = con.getGenericExceptionTypes();
				for (Type t : exceType) {
					out.println(t);
				}
				
				// Create the object by construtor, then use it
				Type[] consType = con.getGenericParameterTypes();
				if (consType.length == 0) {
					C c = (C) con.newInstance();
					out.println(c.toString());
					c.printOne();
				} else if (consType.length == 1) {
					out.println(consType[0]);
					if (consType[0] == int.class) {
						C c = (C) con.newInstance(98);
						out.println(c.toString());
						c.printOne();
					}
				} //...
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		// 16.10 �û�����ָ��������������ͺʹ�С���������úͻ�ȡ����Ԫ�ص�ֵ��
		//       ������ָ�������Ԫ���Ϸ����򲢵��÷���
		C[] cArray = C.createArray(C.class, 3);
		out.println(C.arrayToString(cArray));
		
		Object c2 = Array.get(cArray, 1);
		try {
			Field f1 = C.class.getField("i1");
			f1.set(c2, 108);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		out.println(C.arrayToString(cArray));
		
		try {
			Class[] tempclass = null;
			Method m1 = C.class.getMethod("printOne", tempclass);
			m1.invoke(c2);
			Class[] tempclass2 = {int.class};
			Method m2 = C.class.getMethod("setI2", tempclass2);
			m2.invoke(Array.get(cArray, 2), 888);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		out.println(C.arrayToString(cArray));
	}

}

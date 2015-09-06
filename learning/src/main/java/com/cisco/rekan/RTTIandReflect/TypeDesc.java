/**
 * Java Reading Party
 *     The Java Programming Language - Fourth Edition
 * дһ�������������һ������ѭ����ӡ�������νṹ�е������ࡣ
 */
package com.cisco.rekan.RTTIandReflect;

import static java.lang.System.out;
import static java.lang.System.err;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class TypeDesc {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    TypeVariable[] tv = java.util.Map.class.getTypeParameters();
	    System.out.println(tv.length + "|||" + tv[0].getName() + tv[1].getName()); 
		
		String[] args2 = {"java.util.HashMap"};
		TypeDesc desc = new TypeDesc();
		for (String name : args2) {
			try {
				Class<?> startClass = Class.forName(name);
				desc.printType(startClass, 0, basic);
			} catch (ClassNotFoundException e) {
				err.println(e); // report the error
			}
		}
	}
	
	// used in printType() for labeling type names
	private static String[] 
	                      basic = { "class", "interface", "enum", "annotation" },
	                      supercl = {"extends", "implements" },
	                      iFace = {null, "extends"};
	
	private void printType(Type type, int depth, String[] labels) {
		if (type == null) return;
		
		// turn the Type into a Class object
		Class<?> cls = null;
		if (type instanceof Class<?>)
			cls = (Class<?>) type;
		else if (type instanceof ParameterizedType)
			cls = (Class<?>) ((ParameterizedType) type).getRawType();
		else
			throw new Error("Unexpected non-class type");
		
		// ��ϰ 16.1
		//if (cls.equals(java.lang.Object.class)) return;
		if (cls == java.lang.Object.class) return;
		
		// print this type
		for (int i = 0; i<depth; i++) {
			out.print("  ");
		}
		int kind = cls.isAnnotation() ? 3 : cls.isEnum() ? 2: cls.isInterface() ? 1 : 0;
		out.print(labels[kind] + " ");
		out.print(cls.getCanonicalName());
		
		// print generic type parameters if present
		TypeVariable<?>[] params = cls.getTypeParameters();
		if (params.length > 0) {
			out.print('<');
			for (TypeVariable<?> param : params) {
				out.print(param.getName());
				out.print(", ");
			}
			out.println("\b\b>");
		} else {
			out.println();
		}
		
		// print out all interfaces this class implements
		Type[] interfaces = cls.getInterfaces();
		for (Type iface : interfaces) {
			printType(iface, depth + 1, cls.isInterface() ? iFace : supercl);
		}
		
		// recurse on the superclass
		printType(cls.getGenericSuperclass(), depth + 1, supercl);
	}

	public static void printObject(Class c, Object o) {
		java.lang.reflect.Field fields[] = c.getDeclaredFields();
		for (java.lang.reflect.Field field : fields) {
			String value=null;
			try {
				value = field.get(o).toString();
			} catch (IllegalAccessException e1) {
				value = "[Illegal Access!]";
			} catch (IllegalArgumentException e2) {
				value = "[Object has no this field!]";
			} catch (NullPointerException e3) {
				value = "[is null!]";
			} catch (ExceptionInInitializerError e4) {
				value = "[static field init failure!]";
			}
			System.out.println(field.getName() + ":" + value);
		}
	}
	
}

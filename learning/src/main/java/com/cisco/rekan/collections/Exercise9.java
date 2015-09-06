/*
 * @(#)Exercise9.java 1.0 11/12/07
 * 
 * Copyright 2007 Cisco-WebEx, Inc. All rights reserved.
 * 
 * Cisco-WebEx (HF) TEO QA Java Reading Party
 * Exercise9:
 * ʹ�á���ֵ�ԡ���� HashMap ����ӡ���֤���ǰ�ɢ��������ġ�
 * ȡ����ֵ�ԣ��������򣬽�������� LinkedHashMap ��֤������ά��Ԫ�ز����˳��
 * 
 * ʹ�� HashSet �� LinkedHashSet �ظ�ǰ������ӡ�
 */

package com.cisco.rekan.collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static java.lang.System.out;

/**
 * This class test the map content is or not sortable.
 *
 * @author  Pluto Kan
 * @version 1.0, 11/12/07
 */

public class Exercise9 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// ʹ�á���ֵ�ԡ���� HashMap ����ӡ���֤���ǰ�ɢ��������ġ�
		// 1.1 Make map
		Map<Integer, String> map1 = new HashMap<Integer, String>();
		map1.put(new Integer(4), "Four");
		map1.put(new Integer(3), "Three");
		map1.put(new Integer(1), "One");
		map1.put(new Integer(2), "Two");
		map1.put(new Integer(5), "Five");
		
		// 1.2 Print map use iterator
		Iterator<Map.Entry<Integer, String>> it = map1.entrySet().iterator();
		for ( ; it.hasNext(); ) {
			Map.Entry<Integer, String> entry = it.next();
			out.println(entry.getKey() + entry.getValue());
		}
		out.println();
		// 1.3 Print map with new loop method since "Tiger"
		for (Map.Entry<Integer, String> en : map1.entrySet()) {
			out.println(en.getKey() + en.getValue());
		}
		// 1.4 Print map use the new method "toString"
		out.println(map1);
		
		// ȡ����ֵ�ԣ��������򣬽�������� LinkedHashMap ��֤������ά��Ԫ�ز����˳��
		Set<Integer> set1 = map1.keySet();
		Set<Integer> set2 = new TreeSet<Integer>(set1);
		out.println(set1);
		out.println(set2);
		Map<Integer, String> map2 = new LinkedHashMap<Integer, String>();
		for (Integer inte : set2) {
			map2.put(inte, map1.get(inte));
		}
		out.println(map2);
		
		// ʹ�� HashSet �� LinkedHashSet �ظ�ǰ������ӡ�
		Set<Integer> s1 = new HashSet<Integer>();
		s1.add(new Integer(4));
		s1.add(new Integer(3));
		s1.add(new Integer(1));
		s1.add(new Integer(2));
		s1.add(new Integer(5));
		out.println(s1);
		
		Set<Integer> s2 = new LinkedHashSet<Integer>(new TreeSet<Integer>(s1));
		out.println(s2);
	}

}

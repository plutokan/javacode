/**
 * The Java Programming Language - Fourth Edition
 * ��ϰ 2.2����дһ��Entry�࣬ʹ�����һ��Object���͵��ֶΣ�һ���������õ���һ��EntryԪ�ص����ú�һ���������õ���һ��EntryԪ�ص����á�
 *          ��дһ��LinkedListѭ��˫�������ࣨ����ѭ������ĸ���ɲ��������ݽṹ���ϣ���ʹ�����һ��ָ��Entry������header��
 *          һ����ǰ�����С�ļ�����size���ü�������־headerָ������Ĵ�С����
 *          LinkedList���ṩ����append(Object o)��remove()����ѭ�Ƚ��ȳ����ԣ���ÿ��׷�ӻ��ߵ���һ��Object��
 *          �ṩ����toString()�����Դ�ӡ�����нڵ�Ԫ�أ���Entry��Object����
 *          ��main�����н������?���һЩ�򵥵�object������ӡ������
 * ��ϰ 11.1: �ع���ϰ2.2�е�LinkedList�࣬�������±�дΪһ�������ࡣ
 */

package com.cisco.rekan.generic;

class LinkedListSimple {
	private class Entry {
		Object o;
		Entry previous;
		Entry next;
		
		Entry(Object o, Entry previous, Entry next) {
			this.o = o;
			this.previous = previous;
			this.next = next;
		}
	}
	
	Entry header;
	int size;
	
	LinkedListSimple() {
		//header = null;
		//size = 0;
	}
	
	public Entry append(Object o) {
		Entry e = new Entry(o, null, null);
		if (size == 0) {
			header = e;
			e.previous = e;
			e.next = e;
		} else {
			e.next = header;
			e.previous = header.previous;
			header.previous.next = e;
			header.previous = e;
		}
		
		size++;
		
		return e;
	}
	
	public Object remove() {
		if (size == 0) {
			throw new NullPointerException("LinkedListSimple is null");
		} else if (size == 1) {
			Entry e = header;
			header = null;
			size = 0;
			
			return e.o;
		} else {
			Entry e = header;
			header = e.next;
			header.previous = e.previous;
			e.previous.next = header;
			
			size--;
			
			return e.o;
		}
	}
	
	public String toString() {
		if (size == 0) {
			throw new NullPointerException("LinkedListSimple is null"); 
		}
		String s = "";
		Entry current = header;
		for (int i=0; i<size; i++) {
			s += current.o.toString() + "|";
			current = current.next;
		}
		return s;
	}
}

class LinkedListSimple2<E> {
	private class Entry {
		E o;
		Entry previous;
		Entry next;
		
		Entry(E o, Entry previous, Entry next) {
			this.o = o;
			this.previous = previous;
			this.next = next;
		}
	}
	
	Entry header;
	int size;
	
	LinkedListSimple2() {
		//header = null;
		//size = 0;
	}
	
	public Entry append(E o) {
		Entry e = new Entry(o, null, null);
		if (size == 0) {
			header = e;
			e.previous = e;
			e.next = e;
		} else {
			e.next = header;
			e.previous = header.previous;
			header.previous.next = e;
			header.previous = e;
		}
		
		size++;
		
		return e;
	}
	
	public E remove() {
		if (size == 0) {
			throw new NullPointerException("LinkedListSimple is null");
		} else if (size == 1) {
			Entry e = header;
			header = null;
			size = 0;
			
			return e.o;
		} else {
			Entry e = header;
			header = e.next;
			header.previous = e.previous;
			e.previous.next = header;
			
			size--;
			
			return e.o;
		}
	}
	
	public String toString() {
		if (size == 0) {
			throw new NullPointerException("LinkedListSimple is null"); 
		}
		String s = "";
		Entry current = header;
		for (int i=0; i<size; i++) {
			s += current.o.toString() + "|";
			current = current.next;
		}
		return s;
	}
}

public class LinkedListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedListSimple lls = new LinkedListSimple();
		lls.append("Pluto");
		lls.append(1356);
		lls.append(new Double("999999999.5555555"));
		System.out.println(lls.toString());
		
		LinkedListSimple2<String> lls2 = new LinkedListSimple2<String>();
		lls2.append("Pluto");
		lls2.append("Kan");
		System.out.println(lls2.toString());
	}

}

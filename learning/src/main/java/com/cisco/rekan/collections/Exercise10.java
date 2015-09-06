/*
 * @(#)Exercise10.java 1.0 11/12/07
 * 
 * Copyright 2007 Cisco-WebEx, Inc. All rights reserved.
 * 
 * Cisco-WebEx (HF) TEO QA Java Reading Party
 * Exercise 10:
 *     From "The Java Programming Language - Fourth Edition"
 *     Exercise 21.7: ʹ�� ArrayList ʵ��һ����ջ��
 */

package com.cisco.rekan.collections;

import static java.lang.System.out;

/**
 * Complete a Stack use the ArrayList
 *
 * @author  Pluto Kan
 * @version 1.0, 11/12/07
 */
class MyStack<E> {
    protected java.util.ArrayList<E> pool = new java.util.ArrayList<E>();

    public MyStack() {
    }

    public MyStack(int n) {
        pool.ensureCapacity(n);
    }

    public void clear() {
        pool.clear();
    }

    public boolean empty() {
        return pool.isEmpty();
    }

    public int size() {
        return pool.size();
    }

    public Object peek() {
        if (empty())
            throw new java.util.EmptyStackException();
        return pool.get(pool.size() - 1);
    }

    public Object pop() {
        if (empty())
            throw new java.util.EmptyStackException();
        return pool.remove(pool.size() - 1);
    }

    public void push(E el) {
        pool.add(el);
    }

    @java.lang.Override
    public String toString() {
        return pool.toString();
    }

    @SuppressWarnings("unchecked")
	@java.lang.Override
    public boolean equals(Object o) {
    	if (o instanceof MyStack) {
    		// TODO
    		//MyStack<E> ms2 = (MyStack<E>) o;
    		return true;
    	} else {
    		return false;
    	}
    }

    public int search(E el) {
    	int pos = pool.lastIndexOf(el);
    	if (pos == -1) {
    		return pos;
    	} else {
    		return pool.size() - pool.lastIndexOf(el);
    	}
    }
}

/**
 * Test my stack Class
 *
 * @author  Pluto Kan
 * @version 1.0, 11/12/07
 */

public class Exercise10 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyStack<String> ms = new MyStack<String>();
		out.println(ms.empty());
		ms.push("Pluto 1");
		ms.push("Pluto 2");
		ms.push("Pluto 3");
		ms.push("Pluto 4");
		ms.push("Pluto 2");
		ms.push("Pluto 5");
		out.println(ms.toString());
		out.println(ms.empty());
		out.println(ms.size());
		out.println(ms.search("Pluto 5"));
		out.println(ms.search("Pluto 2"));
		out.println(ms.search("Pluto 1"));
		out.println(ms.search("Pluto 6"));
		out.println(ms.pop());
		out.println(ms.peek());
		out.println(ms.pop());
		ms.clear();
		try {
			out.println(ms.peek());
		} catch (java.util.EmptyStackException e) {
			e.printStackTrace();
		}
		try {
			out.println(ms.pop());
		} catch (java.util.EmptyStackException e) {
			e.printStackTrace();
		}
	}

}

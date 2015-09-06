/*
 * @(#)Exercise11.java 1.0 11/12/07
 * 
 * Copyright 2007 Cisco-WebEx, Inc. All rights reserved.
 * 
 * Cisco-WebEx (HF) TEO QA Java Reading Party
 * Exercise 11:
 *     From "The Java Programming Language - Fourth Edition"
 *     Exercise 21.4: ��дһ��ʵ���� ListIterator �� ShortStrings �汾�����ڹ��� ListIterator ����
 *                    �����Ӧ����չ ShortStrings ���룿
 */

package com.cisco.rekan.collections;

import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import static java.lang.System.out;

/**
 * Write from "The Java Programming Language - Fourth Edition" book 21.13 phrase.
 *
 * @author  N/A
 * @version N/A
 * @create  Pluto Kan, 11/12/2007
 */

class ShortStrings implements Iterator<String> {
	private Iterator<String> strings;  // source for strings;
	private String nextShort;          // null if next not known
	private final int maxLen;          // only return strings <=
	
	public ShortStrings(Iterator<String> strings, int maxLen) {
		this.strings = strings;
		this.maxLen = maxLen;
		nextShort = null;
	}
	
	public boolean hasNext() {
		if (nextShort != null)         // found it already
			return true;
		while (strings.hasNext()) {
			nextShort = strings.next();
			if (nextShort.length() <= maxLen)
				return true;
		}
		nextShort = null;              // did not find one
		return false;
	}
	
	public String next() {
		if (nextShort == null && !hasNext())
			throw new NoSuchElementException();
		String n = nextShort;          // remember nextShort
		nextShort = null;              // consume nextShort
		return n;                      // return nextShort
	}
	
	public void remove() {
		throw new UnsupportedOperationException();
	}
}

/**
 * ShortStrings2 Class to implement ListIterator<String>.
 *
 * Can not extend the ShortString Class, because the hasNext() method can not use in ShortStrings2,
 * if extend ShortString, after call hasNext, hasPrevious must be true.
 * 
 * @author  Pluto Kan
 * @version 1.0, 11/12/07
 */

class ShortStrings2 implements ListIterator<String> {
	private ListIterator<String> strings;  // source for strings;
	private final int maxLen;              // only return strings <=
	
	private String lastReturned;
	private int nextIndex;
	private int size;
	private boolean goFlag;
	
	public ShortStrings2(ListIterator<String> strings, int maxLen) {
		this.strings = strings;
		this.maxLen = maxLen;
		this.lastReturned = null;
		goFlag = true;
		
		nextIndex = 0;
		// put the iterator begin of the list 
		while (strings.hasPrevious()) {
			strings.previous();
		}
		if (hasNext()) {
			nextIndex++;
		}
		while (hasNext()) {
			next();
			size++;
		}
		while (strings.hasPrevious()) {
			strings.previous();
		}
	}

    /**
     * Removes from the list the last element that was returned by
     * <tt>next</tt> or <tt>previous</tt> (optional operation).  This call can
     * only be made once per call to <tt>next</tt> or <tt>previous</tt>.  It
     * can be made only if <tt>ListIterator.add</tt> has not been called after
     * the last call to <tt>next</tt> or <tt>previous</tt>.
     *
     * @exception UnsupportedOperationException if the <tt>remove</tt>
     * 		  operation is not supported by this list iterator.
     * @exception IllegalStateException neither <tt>next</tt> nor
     *		  <tt>previous</tt> have been called, or <tt>remove</tt> or
     *		  <tt>add</tt> have been called after the last call to *
     *		  <tt>next</tt> or <tt>previous</tt>.
     */
	public void remove() {
		if (lastReturned == null) {
			throw new IllegalStateException();
		}
		if (goFlag) {
			while (! strings.previous().equals(lastReturned)) {
				;
			}
			strings.next();
			strings.remove();
		} else {
			while (! strings.next().equals(lastReturned)) {
				;
			}
			strings.previous();
			strings.remove();
		}
		lastReturned = null;
		//nextIndex--;
		size--;
	}

    /**
     * Returns <tt>true</tt> if this list iterator has more elements when
     * traversing the list in the reverse direction.  (In other words, returns
     * <tt>true</tt> if <tt>previous</tt> would return an element rather than
     * throwing an exception.)
     *
     * @return <tt>true</tt> if the list iterator has more elements when
     *	       traversing the list in the reverse direction.
     */
	public boolean hasPrevious() {
		boolean flag = false;
		int prevCount = 0;
		String prevString;
		
		while (strings.hasPrevious() && !flag) {
			prevString = strings.previous();
			prevCount++;
			if (prevString.length() <= maxLen) {
				flag = true;
			}
		}
		for (; prevCount > 0; prevCount--) {
			strings.next();
		}
		
		return flag;
	}

    /**
     * Returns <tt>true</tt> if this list iterator has more elements when
     * traversing the list in the forward direction. (In other words, returns
     * <tt>true</tt> if <tt>next</tt> would return an element rather than
     * throwing an exception.)
     *
     * @return <tt>true</tt> if the list iterator has more elements when
     *		traversing the list in the forward direction.
     */
	public boolean hasNext() {
		boolean flag = false;
		int nextCount = 0;
		String nextString;
		
		while (strings.hasNext() && !flag) {
			nextString = strings.next();
			nextCount++;
			if (nextString.length() <= maxLen) {
				flag = true;
			}
		}
		for (; nextCount > 0; nextCount--) {
			strings.previous();
		}
		
		return flag;
	}
	
    /**
     * Returns the index of the element that would be returned by a subsequent
     * call to <tt>next</tt>. (Returns list size if the list iterator is at the
     * end of the list.)
     *
     * @return the index of the element that would be returned by a subsequent
     * 	       call to <tt>next</tt>, or list size if list iterator is at end
     *	       of list. 
     */
	public int nextIndex() {
		if (hasNext()) {
			return nextIndex;
		} else {
			return size;
		}
	}

    /**
     * Returns the index of the element that would be returned by a subsequent
     * call to <tt>previous</tt>. (Returns -1 if the list iterator is at the
     * beginning of the list.)
     *
     * @return the index of the element that would be returned by a subsequent
     * 	       call to <tt>previous</tt>, or -1 if list iterator is at
     *	       beginning of list.
     */ 
	public int previousIndex() {
		if (hasPrevious()) {
			return nextIndex-1;
		} else {
			return -1;
		}
	}

    /**
     * Returns the next element in the list.  This method may be called
     * repeatedly to iterate through the list, or intermixed with calls to
     * <tt>previous</tt> to go back and forth.  (Note that alternating calls
     * to <tt>next</tt> and <tt>previous</tt> will return the same element
     * repeatedly.)
     *
     * @return the next element in the list.
     * @exception NoSuchElementException if the iteration has no next element.
     */
	public String next() {
		String nextString;
		int nextCount = 0;
		
		while (strings.hasNext()) {
			nextString = strings.next();
			nextCount++;
			if (nextString.length() <= maxLen) {
				lastReturned = nextString;
				nextIndex++;
				goFlag = true;
				return lastReturned;
			}
		}
		
		for (; nextCount > 0; nextCount--) {
			strings.previous();
		}
		return strings.next();
	}
	
    /**
     * Returns the previous element in the list.  This method may be called
     * repeatedly to iterate through the list backwards, or intermixed with
     * calls to <tt>next</tt> to go back and forth.  (Note that alternating
     * calls to <tt>next</tt> and <tt>previous</tt> will return the same
     * element repeatedly.)
     *
     * @return the previous element in the list.
     * 
     * @exception NoSuchElementException if the iteration has no previous
     *            element.
     */
	public String previous() {
		String prevString;
		int prevCount = 0;
		
		while (strings.hasPrevious()) {
			prevString = strings.previous();
			prevCount++;
			if (prevString.length() <= maxLen) {
				lastReturned = prevString;
				nextIndex--;
				goFlag = false;
				return lastReturned;
			}
		}
		for (; prevCount > 0; prevCount--) {
			strings.next();
		}
		return strings.previous();
	}

    /**
     * Inserts the specified element into the list (optional operation).  The
     * element is inserted immediately before the next element that would be
     * returned by <tt>next</tt>, if any, and after the next element that
     * would be returned by <tt>previous</tt>, if any.  (If the list contains
     * no elements, the new element becomes the sole element on the list.)
     * The new element is inserted before the implicit cursor: a subsequent
     * call to <tt>next</tt> would be unaffected, and a subsequent call to
     * <tt>previous</tt> would return the new element.  (This call increases
     * by one the value that would be returned by a call to <tt>nextIndex</tt>
     * or <tt>previousIndex</tt>.)
     *
     * @param o the element to insert.
     * @exception UnsupportedOperationException if the <tt>add</tt> method is
     * 		  not supported by this list iterator.
     * 
     * @exception ClassCastException if the class of the specified element
     * 		  prevents it from being added to this list.
     * 
     * @exception IllegalArgumentException if some aspect of this element
     *            prevents it from being added to this list.
     */
	public void add(String o) {
		// TODO
	}

    /**
     * Replaces the last element returned by <tt>next</tt> or
     * <tt>previous</tt> with the specified element (optional operation).
     * This call can be made only if neither <tt>ListIterator.remove</tt> nor
     * <tt>ListIterator.add</tt> have been called after the last call to
     * <tt>next</tt> or <tt>previous</tt>.
     *
     * @param o the element with which to replace the last element returned by
     *          <tt>next</tt> or <tt>previous</tt>.
     * @exception UnsupportedOperationException if the <tt>set</tt> operation
     * 		  is not supported by this list iterator.
     * @exception ClassCastException if the class of the specified element
     * 		  prevents it from being added to this list.
     * @exception IllegalArgumentException if some aspect of the specified
     *		  element prevents it from being added to this list.
     * @exception IllegalStateException if neither <tt>next</tt> nor
     *	          <tt>previous</tt> have been called, or <tt>remove</tt> or
     *		  <tt>add</tt> have been called after the last call to
     * 		  <tt>next</tt> or <tt>previous</tt>.
     */
	public void set(String o) {
		// TODO
	}

}

/**
 * Test the ShortStrings Class.
 *
 * @author  Pluto Kan
 * @version 1.0, 11/12/07
 */

public class Exercise11 {

	public static void printList(List<String> list) {
		out.println();
		for (String s : list) {
			out.println(s);
		}
		out.println();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> list = new LinkedList<String>();
		list.add("00000000");
		list.add("1111111");
		list.add("aaa");
		list.add("bbb");
		list.add("cccccc");
		list.add("dddddd");
		list.add("eeeee");
		list.add("ff");
		list.add("gggggggg");
		//printList(list);
		
		Iterator<String> it = list.iterator();
		ShortStrings ss = new ShortStrings(it, 5);
		while (ss.hasNext()) {
			ss.hasNext();             // call hasNext() twice, for test only
			out.println(ss.next());
		}
		try {
			ss.remove();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace(out);
		}
		
		ListIterator<String> lit = list.listIterator();
		ShortStrings2 ss2 = new ShortStrings2(lit, 5);
		// 1. test hasNext and next methods
		while (ss2.hasNext()) {
			ss2.hasNext();             // call hasNext() twice, for test only
			out.println(ss2.next());
		}
		// 2. test hasPreviou, previou and remove methods
		while (ss2.hasPrevious()) {
			ss2.hasPrevious();         // call hasNext() twice, for test only
			ss2.hasNext();
			out.println(ss2.previous());
			ss2.remove();
		}
		printList(list);
	}

}

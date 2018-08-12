/*
 * LinkedList_ArrayList_test.java
 * 
 * @author Xinmeng Zhang
 */
package edu.northeastern.cs_5004;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

/**
 * This class tests LinkedList and ArrayList.
 * 
 * @author Xinmeng Zhang
 * 
 * @param <T> generic type
 */
public class LinkedList_ArrayList_test {
	/**
	 * Unit tests for LinkedList
	 */
	@Test
	public void test_0010_LinkListTest() {
		LinkedList<String> list = new LinkedList<>();
		assertEquals("list empty", 0, list.size());
		assertTrue("list empty", list.isEmpty());
		
		// verify cannot add null
		boolean addedNull = list.add(0, null);
		assertFalse("added null", addedNull);
		assertEquals("list empty", 0, list.size());
		
		// verify adding to list
		for (int i = 0; i < 5; i++) {
			String value = Integer.toString(i); 
			list.add(0, value);
			assertEquals("added to list", i+1, list.size());
		}

		// verify list values
		for (int i = 0; i < 5; i++) {
			String value = list.get(i);
			assertEquals("read from list", Integer.toString(4-i), value);
		}
		
		// verify find list values
		for (int i = 0; i < 5; i++) {
			String value = Integer.toString(4-i);
			int index = list.indexOf(value);
			assertEquals("index in list", index, i);
		}

		// verify reversing list values by setting values
		for (int i = 0; i < 5; i++) {
			String oldValue = Integer.toString(4-i);
			String newValue = Integer.toString(i);
			String value = list.set(i, newValue);
			assertEquals("new value in index", value, oldValue);
		}
		
		// verify finding first and last occurrence of element
		int idx = list.indexOf("3");
		assertEquals("index of", 3, idx);
		idx = list.lastIndexOf("3");
		assertEquals("last index of", 3, idx);
		idx = list.indexOf("A");
		assertEquals("no indexof", -1, idx);

		// verify removing from list
		for (int i = 0; i < 5; i++) {
			String value = Integer.toString(i);
			String actual = list.remove(0);
			assertEquals("remove from list", value, actual);
			assertEquals("measure list", 4-i, list.size());
		}
		
		// ensure list is now empty
		assertEquals("list empty", 0, list.size());

	}

	/**
	 * Unit tests for LinkedList iterator.
	 */
	@Test
	public void test_0020_LinkListIteratorTest() {
		LinkedList<String> list = new LinkedList<>();
		
		// populate list
		for (int i = 0; i < 5; i++) {
			String value = Integer.toString(i); 
			list.add(value);
		}
		
		LinkedList<String>.LinkedListIterator<String> itr = list.listIterator();

		// verify iterator at start 
		int itrIndex = itr.nextIndex();
		assertEquals("itr next index", 0, itrIndex);
		itrIndex = itr.previousIndex();
		assertEquals("itr previous index", -1, itrIndex);
				
		// verify forward iteration
		for (int i = 0; itr.hasNext(); i++) {
			String value = Integer.toString(i);
			String nextValue = itr.next();
			assertEquals("iterator", nextValue, value);
		}

		// verify iterator at end 
		itrIndex = itr.nextIndex();
		assertEquals("itr next index", 5, itrIndex);
		itrIndex = itr.previousIndex();
		assertEquals("itr previous index", 4, itrIndex);

		
		// verify backward iteration
		for (int i = 0; itr.hasPrevious(); i++) {
			String value = Integer.toString(4-i);
			String nextValue = itr.previous();
			assertEquals("iterator", nextValue, value);
		}
		
		// verify iterator at start 
		itrIndex = itr.nextIndex();
		assertEquals("itr next index", 0, itrIndex);
		itrIndex = itr.previousIndex();
		assertEquals("itr previous index", -1, itrIndex);
		
	}
	
	/**
	 * Unit tests for ArrayList
	 */
	@Test
	public void test_0030_ArrayListTest() {
		ArrayList<String> list = new ArrayList<>();
		assertEquals("list empty", 0, list.size());
		//initial capacity is set to 4
		assertEquals("list empty ", 4, list.getCapacity());
		assertTrue("list empty", list.isEmpty());
		
		// verify cannot add null
		boolean addedNull = list.add(0, null);
		assertFalse("added null", addedNull);
		assertEquals("list empty", 0, list.size());
		
		// verify adding to list
		for (int i = 0; i < 5; i++) {
			String value = Integer.toString(i); 
			list.add(0, value);
			assertEquals("added to list", i+1, list.size());
		}
		
		//capacity should double to 8
		assertEquals("list empty", 8, list.getCapacity());
		

		// verify list values
		for (int i = 0; i < 5; i++) {
			String value = list.get(i);
			assertEquals("read from list", Integer.toString(4-i), value);
		}

		// arraylist of 4 3 2 1 0
		// verify find list values
		for (int i = 0; i < 5; i++) {
			String value = Integer.toString(4-i);
			int index = list.indexOf(value);
			assertEquals("index in list", index, i);
		}

		// verify reversing list values by setting values
		for (int i = 0; i < 5; i++) {
			String oldValue = Integer.toString(4-i);
			String newValue = Integer.toString(i);
			String value = list.set(i, newValue);
			assertEquals("new value in index", value, oldValue);
		}
		
		// verify finding first and last occurrence of element
		int idx = list.indexOf("3");
		assertEquals("index of", 3, idx);
		idx = list.lastIndexOf("3");
		assertEquals("last index of", 3, idx);
		idx = list.indexOf("A");
		assertEquals("no indexof", -1, idx);

		// verify removing from list
		for (int i = 0; i < 5; i++) {
			String value = Integer.toString(i);
			String actual = list.remove(0);
			assertEquals("remove from list", value, actual);
			assertEquals("measure list", 4-i, list.size());
		}
		
		// ensure list is now empty
		assertEquals("list empty", 0, list.size());

	}

	/**
	 * Unit tests for ArrayList iterator.
	 */
	@Test
	public void test_0040_ArrayListIteratorTest() {
		ArrayList<String> list = new ArrayList<>();
		
		// populate list
		for (int i = 0; i < 5; i++) {
			String value = Integer.toString(i); 
			list.add(value);
		}
		
		ArrayList<String>.ArrayListIterator<String> itr = list.listIterator();

		// verify iterator at start 
		int itrIndex = itr.nextIndex();
		assertEquals("itr next index", 0, itrIndex);
		itrIndex = itr.previousIndex();
		assertEquals("itr previous index", -1, itrIndex);
				
		// verify forward iteration
		for (int i = 0; itr.hasNext(); i++) {
			String value = Integer.toString(i);
			String nextValue = itr.next();
			assertEquals("iterator", nextValue, value);
		}

		// verify iterator at end 
		itrIndex = itr.nextIndex();
		assertEquals("itr next index", 5, itrIndex);
		itrIndex = itr.previousIndex();
		assertEquals("itr previous index", 4, itrIndex);

		
		// verify backward iteration
		for (int i = 0; itr.hasPrevious(); i++) {
			String value = Integer.toString(4-i);
			String nextValue = itr.previous();
			assertEquals("iterator138", nextValue, value);
		}
		
		// verify iterator at start 
		itrIndex = itr.nextIndex();
		assertEquals("itr next index", 0, itrIndex);
		itrIndex = itr.previousIndex();
		assertEquals("itr previous index", -1, itrIndex);
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    Result result = JUnitCore.runClasses(LinkedList_ArrayList_test.class);
	    
	    System.out.println("[Unit Test Results]");
	    System.out.println();
	    
	    if (result.getFailureCount() > 0) {
	    	System.out.println("Test failure details:");
		    for (Failure failure : result.getFailures()) {
		       System.out.println(failure.toString());
		    }
		    System.out.println();
	    }
	    
	    int passCount = result.getRunCount()-result.getFailureCount()-result.getIgnoreCount(); 
	    System.out.println("Test summary:");
	    System.out.println("* Total tests = " + result.getRunCount());
	    System.out.println("* Passed tests: " + passCount);
	    System.out.println("* Failed tests = " + result.getFailureCount());
	    System.out.println("* Inactive tests = " + result.getIgnoreCount());
	}
}

/*
 * ArrayList.java
 * 
 * @author Xinmeng Zhang
 */
package edu.northeastern.cs_5004;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * This class implements an Arraylist and iterator.
 * Added ensureCapacity(), getCapacity() functions for testing
 * 
 * @author Xinmeng Zhang
 * 
 * @param <T> generic type
 */
public class ArrayList<T> implements List<T> {
	
	/** array*/
	private T[] items;
	
	/** The size of the array list*/
	private int size;
	
	/** The default capacity if not given one*/
	private static final int DEFAULT_CAPACITY = 4;
	//set a small default capacity, easy to test array expand to size*2
	
	/** The capacity*/
	private int capacity;
	//there is a getter function for capacity at the bottom
	
	/**
	 * This class represents an iterator over array list.
	 */
	@SuppressWarnings("hiding")
	class ArrayListIterator<T> implements ListIterator<T>{
		
		/** Next index of iterator */
		private int nextIndex = 0;
		
		/**
		 * Returns true if this list iterator has more elements when traversing the 
		 * list in the forward direction.
		 * 
		 * @return true  if the list iterator has more elements when traversing the 
		 * list in the forward direction
		 */
		@Override
		public boolean hasNext() {
			return nextIndex < size;
		}
		
		/**
		 * Returns true if this list iterator has more elements when traversing the 
		 * list in the reverse direction.
		 * 
		 * @return true  if the list iterator has more elements when traversing the 
		 * list in the reverse direction
		 */
		@Override
		public boolean hasPrevious() {
			return nextIndex > 0;
		}
		
		/**
		 * Returns the next element in the list and advances the cursor position. 
		 * This method may be called repeatedly to iterate through the list, or 
		 * intermixed with calls to previous() to go back and forth. (Note that 
		 * alternating calls to next and previous will return the same element 
		 * repeatedly.)
		 * 
		 * @return the next element in Arraylist or null if no more elements
		 */
		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			if (!hasNext()) {
				return null;
			}
			return (T) items[nextIndex++];
		}
		
		/**
		 * Returns the index of the element that would be returned by a subsequent call 
		 * to next(). (Returns list size if the list iterator is at the end of the list.)
		 * 
		 * @return the index of the element that would be returned by a subsequent call to 
		 * next, or list size if the list iterator is at the end of the list
		 */
		@Override
		public int nextIndex() {
			return nextIndex;
		}
		
		/**
		 * Returns the next element in the list and advances the cursor position. This 
		 * method may be called repeatedly to iterate through the list, or intermixed
		 * with calls to previous() to go back and forth. (Note that alternating calls 
		 * to next and previous will return the same element repeatedly.)
		 * 
		 * @return the next element in Arraylist or null if no more elements
		 */
		@SuppressWarnings("unchecked")
		@Override
		public T previous() {
			if (!hasPrevious()) {
				return null;
			}
			return (T) items[--nextIndex];
		}
		
		/**
		 * Returns the index of the element that would be returned by a subsequent call to 
		 * previous(). (Returns -1 if the list iterator is at the beginning of the list.)
		 * 
		 * @return the index of the element that would be returned by a subsequent call to 
		 * previous, or -1 if the list iterator is at the beginning of the list
		 */
		@Override
		public int previousIndex() {
			return nextIndex-1;
		}

		/**
		 * (Optional) Removes from the list the last element that was returned by 
		 * next() or previous()
		 */
		@Override
		public void remove() {
			// nothing
			
		}
		
		/**
		 * (Optional) Replaces the last element returned by 
		 * next() or previous() with the specified element
		 * 
		 * @param e the element to be replaced
		 */
		@Override
		public void set(T e) {
			// nothing
			
		}
		
		/**
		 * (Optional) Inserts the specified element into the list
		 * 
		 * @param e the element to be added to the list
		 */
		@Override
		public void add(T e) {
			// nothing		
		}	
	}
	
	/**
	 * Initialize the ArrayList without specified capacity, default capacity is set to 4
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		//create a new array, and initialize size and capacity
		items = (T[]) new Object[DEFAULT_CAPACITY];
		size = 0;
		this.capacity = DEFAULT_CAPACITY;
		clear();
	}
	
	/**
	 * Initialize the ArrayList with specified capacity
	 */
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		//create a new array, and initialize size and capacity
		items = (T[]) new Object[capacity];
		size = 0;
		this.capacity = capacity;
		clear();
	}
	
	/**
	 * Ensure that arraylist has the capacity for an additional element
	 * If not, expand size to size*2
	 */
	@SuppressWarnings("unchecked")
	public void ensureCapacity() {
		//if no more place for an additional element, expand and copy
		if (size == capacity) {
			T[] newItems = (T[]) new Object[size*2];
			for (int i = 0; i<size; i++) {
				newItems[i] = items[i];
			}
			items = newItems;
			//also need to update capacity information
			capacity = capacity * 2;
		}
	}
	
	/**
	 * Appends the specified element to the end of this list.
	 * 
	 * @param element the element to add
	 * @return true if the element was added
	 */
	@Override
	public boolean add(T element) {
		if (element == null) return false;
		//make sure there is place to add
		ensureCapacity();
		items[size++] = element;
		return true;
	}
	
	/**
	 * Inserts the specified element at the specified position in this list.
	 * 
	 * @param index the index
	 * @param element the  element to add
	 * @return true if element was added
	 */

	@Override
	public boolean add(int index, T element) {
		if (element == null) return false;
		ensureCapacity();
		//find the place to insert and move the rest elements backward
		//avoid covering other elements, move from the end
		for (int i = size; i > index; i--) {
			items[i] = items[i-1];
		}
		//now insert
		items[index] = element;
		size++;
		//return status
		return true;
	}
	
	/**
	 * Removes all of the elements from this list.
	 */
	@Override
	public void clear() {
		//make sure the array will be empty
		for(int i=0; i<size; i++) {
			items[i] = null;
		}
		//reset size to 0
		size = 0;		
	}
		
	/**
	 * Returns the element at the specified position in this list.
	 * 
	 * @param index the index
	 * @return the element at the position in the ist or null if index out of bounds
	 */
	@Override
	public T get(int index) {
		//avoid indexoutofbound exception, make sure index we are looking at is avaliable
		if (index >= size) {
			return null;
		}
		return items[index];
	}
	
	/**
	 * Returns the first index of the first occurrence of the specified element 
	 * in this list, or -1 if this list does not contain the element.
	 * 
	 * @param element the element to find
	 * @return the position in the list or -1 if not found
	 */
	@Override
	public int indexOf(T element) {
		for (int i = 0; i < size; i++) {
			if (items[i].equals(element)) { 
				//compare objects T not primitive types so we cant use ==
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Returns true if this list contains no elements.
	 * 
	 * @return true if list is empty
	 */
	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * Returns the index of the last occurrence of the specified element 
	 * in this list, or -1 if this list does not contain the element.
	 * 
	 * @param element the element to find
	 * @return the position in the list or -1 if not found
	 */
	@Override
	public int lastIndexOf(T element) {
		for (int i = size-1; i >= 0; i--) {
			if (items[i].equals(element)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Returns a list iterator over the elements in this list in sequence.
	 * 
	 * @return a list iterator over the elements in this list in sequence
	 */
	@Override
	public ArrayListIterator<T> listIterator() {
		return new ArrayListIterator<>();
	}
	
	/**
	 * Removes the element at the specified position in this list
	 * 
	 * @param index the index
	 * @return the element removed or null if index is out of bounds
	 */
	@Override
	public T remove(int index) {
		//empty arraylist or indexoutofbounds exception
		if (size <= 0 || index >= size) {
			return null;
		}
		T temp = items[index];
		//move forward
		for (int i=index; i<size-1; i++) {
			items[i] = items[i + 1];
		}
		size--;
		return temp;	
	}
	
	/**
	 * Replaces the element at the specified position in this list 
	 * with the specified element.
	 * 
	 * @param index the index
	 * @return the previous value or null if index is out of bounds
	 */
	@Override
	public T set(int index, T element) {
		T temp = items[index];
		items[index] = element;
		return temp;
	}
	
	/**
	 * Returns the size of the list.
	 * 
	 * @return the size of the list
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Return the capacity of the list
	 * 
	 * @return the capacity of the list
	 */
	//default is package visible
	int getCapacity() {
		return capacity;
	}

	/**
	 * Returns a list iterator over the elements in this list in sequence.
	 * 
	 * @return a list iterator over the elements in this list in sequence
	 */
	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator<>();
	}
	
}


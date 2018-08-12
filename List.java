/*
 * List.java
 *
 *  @author Xinmeng Zhang
 */
package edu.northeastern.cs_5004;

import java.util.ListIterator;

/**
 * The generic List interface that extends the java.lang.Iterable
 * @author Xinmeng Zhang
 *
 * @param <T> generic type
 */
public interface List<T> extends Iterable<T> {
	/**
	 * Appends the specified element to the end of this list.
	 * 
	 * @param element the element to add
	 * @return true if the element was added
	 */
	public boolean add(T element);
	
	/**
	 * Inserts the specified element at the specified position in this list.
	 * 
	 * @param index the index
	 * @param element the element to add
	 * @return true if element was added
	 */
	public boolean add(int index, T element);
	
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear();
	
	/**
	 * Returns the element at the specified position in this list.
	 * 
	 * @param index the index
	 * @return the element at the position in the ist or null if index out of bounds
	 */
	public T get(int index);
	
	/**
	 * Returns the first index of the first occurrence of the specified element 
	 * in this list, or -1 if this list does not contain the element.
	 * 
	 * @param element the element to find
	 * @return the position in the list or -1 if not found
	 */
	public int indexOf(T element);
	
	/**
	 * Returns true if this list contains no elements.
	 * 
	 * @return true if list is empty
	 */
	public boolean isEmpty();
	
	/**
	 * Returns the index of the last occurrence of the specified element 
	 * in this list, or -1 if this list does not contain the element.
	 * 
	 * @param element the element to find
	 * @return the position in the list or -1 if not found
	 */
	public int lastIndexOf(T element);
	
	/**
	 * Returns a list iterator over the elements in this list in sequence.
	 * 
	 * @return a list iterator over the elements in this list in sequence
	 */
	public ListIterator<T> listIterator();
	
	
	/**
	 * Removes the element at the specified position in this list
	 * 
	 * @param index the index
	 * @return the element removed or null if index is out of bounds
	 */
	public T remove(int index);
	
	/**
	 * Replaces the element at the specified position in this list 
	 * with the specified element.
	 * 
	 * @param index the index
	 * @param element the new element
	 * @return the previous value or null if index is out of bounds
	 */
	public T set(int index, T element);
	
	/**
	 * Returns the size of the list.
	 * 
	 * @return the size of the list
	 */
	public int size();
}
	
	
	
	
	
	
	
	
	
	
	
	
	
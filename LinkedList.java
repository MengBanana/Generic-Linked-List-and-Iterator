/*
 * LinkedList.java
 * 
 * @author Xinmeng Zhang
 */
package edu.northeastern.cs_5004;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * This class implements a linked list and iterator.
 * 
 * @author Xinmeng Zhang
 * 
 * @param <T> generic type
 */
public class LinkedList<T> implements List<T> {
	/**
	 * This class represents a node in a doubly-linked ist
	 */
	static class LinkedListNode<T> {
		/** The list data */
		public T data;
		
		/** The next node in the list */
		public LinkedListNode<T> prevNode;
		
		/** The previous node in the list */
		public LinkedListNode<T> nextNode;
	}
	
	/** The head of the linked list */
	private LinkedListNode<T> headNode;
	
	/** The size of the linked list, not counting head node */
	private int size;
	
	/**
	 * This class represents an iterator over the nodes in this linked list.
	 */
	@SuppressWarnings("hiding")
	class LinkedListIterator<T> implements ListIterator<T>{
		/** The head node in the linked list */
		@SuppressWarnings("unchecked")
		private LinkedListNode<T> nextNode = (LinkedListNode<T>) headNode; 
		
		/** Next index of iterator */
		private int nextIndex = 0;
		
		/**
		 * Returns true if this list iterator has more elements when traversing the 
		 * list in the forward direction.
		 * 
		 * @return true if the list iterator has more elements when traversing the 
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
		 * @return the next node in the linked list or null if no more nodes
		 */
		@Override
		public T next() {
			if (!hasNext()) {
				return null;
			}
			nextNode = nextNode.nextNode;
			nextIndex++;
			return (T) nextNode.data;
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
		 * @return the next node in the linked list or null if no more nodes
		 */
		@Override
		public T previous() {
			if (!hasPrevious()) {
				return null;
			}
			T data = (T) nextNode.data;
			nextNode = nextNode.prevNode;
			nextIndex--;
			return data;
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
	 * Initialize the linked list.
	 */
	public LinkedList() {
		// circularly link the list
		headNode = new LinkedListNode<T>();
		clear();
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
		
		add(size, element);
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
		
		LinkedListNode<T> afterNode = headNode.prevNode;
		if (index != size) {
			// find node to odd after for index
			LinkedListNode<T> node = getNode(index);
			if (node == null) {
				return false;
			}
			afterNode = node.prevNode;
		}

		LinkedListNode<T> node = new LinkedListNode<>();
		node.data = element;

		// link node in after afterNode
		node.nextNode = afterNode.nextNode;
		node.prevNode = afterNode;
		afterNode.nextNode.prevNode = node;
		afterNode.nextNode = node;
		
		// add new node to count
		size++;
		
		//return status
		return true;
	}
	
	/**
	 * Removes all of the elements from this list.
	 */
	@Override
	public void clear() {
		headNode.nextNode = headNode;
		headNode.prevNode = headNode;
		size = 0;		
	}
	
	/**
	 * This utility function finds the node at a givenIndex.
	 *  
	 * @param index the index
	 * @return the linked list node or null if index out of bounds
	 */
	private LinkedListNode<T> getNode(int index) {
		// return null if index out of bounds 
		if (index < 0 || index >= size) {
			return null;
		}
		
		// find the node in the list
		LinkedListNode<T> node = headNode.nextNode;
		for (int i = 0; i < index; i++) {
			node = node.nextNode; 
		}
		return node;
	}
	
	/**
	 * Returns the element at the specified position in this list.
	 * 
	 * @param index the index
	 * @return the element at the position in the ist or null if index out of bounds
	 */
	@Override
	public T get(int index) {
		LinkedListNode<T> node = getNode(index);
		return (node != null) ? node.data : null;
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
		// find the node in the list
		LinkedListNode<T> node = headNode.nextNode;
		for (int i = 0; i < size; i++) {
			if (node.data.equals(element)) {
				return i;
			}
			node = node.nextNode;
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
		// find the node in the list
		LinkedListNode<T> node = headNode.prevNode;
		for (int i = size-1; i >= 0; i--) {
			if (node.data.equals(element)) {
				return i;
			}
			node = node.prevNode;
		}
		return -1;
	}
	
	/**
	 * Returns a list iterator over the elements in this list in sequence.
	 * 
	 * @return a list iterator over the elements in this list in sequence
	 */
	@Override
	public LinkedListIterator<T> listIterator() {
		return new LinkedListIterator<>();
	}
	
	/**
	 * Removes the element at the specified position in this list
	 * 
	 * @param index the index
	 * @return the element removed or null if index is out of bounds
	 */
	@Override
	public T remove(int index) {
		// cannot remove if index out of bounds
		LinkedListNode<T> node = getNode(index);
		if (node == null) {
			return null;
		}
		
		// link around this node
		node.prevNode.nextNode = node.nextNode;
		node.nextNode.prevNode = node.prevNode;

		// decrease the size of the list
		size--;
		
		// return the node data
		return (T) node.data;
	}
	
	/**
	 * Replaces the element at the specified position in this list 
	 * with the specified element.
	 * 
	 * @param index the index
	 * @param element the new element
	 * @return the previous value or null if index is out of bounds
	 */
	@Override
	public T set(int index, T element) {
		// do not insert null element
		if (element == null) {
			return null;
		}
		
		// get node in list
		LinkedListNode<T> node = getNode(index);
		if (node == null) {
			return null;
		}
		
		// replace node data
		T oldData = node.data;
		node.data = element;
		return oldData;
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
	 * Returns a list iterator over the elements in this list in sequence.
	 * 
	 * @return a list iterator over the elements in this list in sequence
	 */
	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator<>();
	}
	
}


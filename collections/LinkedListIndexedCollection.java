package hr.fer.zemris.java.custom.collections;

/**
 * Linked list backed array implementation of Collection class. Accepts duplicate elements, but not null elements.
 * @author Marko Benacic
 *
 */


public class LinkedListIndexedCollection extends Collection{
	
	/**
	 * number of elements in our list.
	 */
	private int size;
	
	/**
	 * first element in the list.
	 */
	private ListNode first;
	
	/**
	 * last element in the list.
	 */
	private ListNode last;
	
	
	/**
	 * private static class representing a node in our linked list implementation
	 */
	private static class ListNode{
		ListNode previous;
		ListNode next;
		Object value;
	}
	
	/**
	 * Default constructor, creates empty collection.
	 */
	public LinkedListIndexedCollection() {
		this.size = 0;
		this.first = null;
		this.last = null;
		
	}
	
	/**
	 * Creates a new collection and copies elements of the other collection into the new one.
	 * @param other collection which elements will be copied into new collection.
	 */
	public LinkedListIndexedCollection(Collection other) {
		this.size = 0;
		this.addAll(other);
	}
	
	
	/**
	 * Adds the given object at the end of collection. Complexity O(1).
	 * @throws NullPointerException if given value is <code>null</code>.
	 */
	@Override
	public void add(Object value) {
		if(value == null) {
			throw new NullPointerException("given value can not be null");
		}
		
		insert(value, size);
	}
	
	/**
	 * retrieves object from collection at the given index. Complexity O(n/2 + 1).
	 * @param index index from which the element will be retrieved;
	 * @return Object at given index in the collection.
	 */
	public Object get(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("given index must be within range [0,size-1]");
		}
		
		if((size - index) >= index ) {
			ListNode current = first;
			for(int i = 0; i < index; i++) {
				current = current.next;
			}
			return current.value;
		}else {
			ListNode current = last;
			for(int i = size - 1; i > index; i--) {
				current = current.previous;
			}
			return current.value;
		}
	}
	
	/**
	 * Removes all elements from the collection.
	 */
	@Override
	public void clear() {
		first = last = null;
		size = 0;
	}
	
	/**
	 * Insert value at given position in the collection. Average complexity O(n/2 + 1).
	 * @param value element to be inserted
	 * @param position position in collection at which the element is inserted.
	 * @throws NullPointerException if given value is <code>null</code>.
	 * @throws IndexOutOfBoundsException if position is not within range [0, size].
	 */
	public void insert(Object value, int position) {
		if(value == null) {
			throw new IllegalArgumentException("Can not insert value as null");
		}
		if(position < 0 || position > size) {
			throw new IndexOutOfBoundsException("Position must be within range [0,size]");
		}
		if(size == 0) {
			ListNode newNode = new ListNode();
			newNode.value = value;
			first = newNode;
			last = newNode;
			
		}else if(position == size) {
			ListNode newNode= new ListNode();
			newNode.value = value;
			newNode.previous = last;
			newNode.next = null;
			last.next = newNode;
			last = newNode;
			
		}else if(position == 0) {
			ListNode newNode = new ListNode();
			newNode.value = value;
			newNode.previous = null;
			newNode.next = first;
			first.previous = newNode;
			first = newNode;
		}else {
			if((size - position) >= position ) {
				ListNode current = first;
				for(int i = 0; i < position; i++) {
					current = current.next;
				}
				ListNode newNode = new ListNode();
				newNode.value = value;
				newNode.previous = current.previous;
				newNode.next = current;
				current.previous.next = newNode;
				current.previous = newNode;
			}else {								
				ListNode current = last;
				for(int i = size - 1; i > position; i--) {
					current = current.previous;
				}
				ListNode newNode = new ListNode();
				newNode.value = value;
				newNode.previous = current.previous;
				newNode.next = current;
				current.previous.next = newNode;
				current.previous = newNode;				// shvacam da se malo ponavlja kod, 
														//al ovako mi je ljepse nego da šaljem u novu metodu.
			}
		}
		size++;
		
	}
	
	/**
	 * returns index within the collection of given value. Search is based on equals method. Complexity O(n).
	 * @param value element that we are searching for in collection.
	 * @return index of given element as int. If element is not faound returns -1.
	 * @throws IllegalArgumentException if given value is <code>null</code>.
	 */
	public int indexOf(Object value) {
		if(value == null) {
			throw new IllegalArgumentException("given value can not be null!");
		}
		
		ListNode current = first;
		for(int i = 0; i < size; i++) {
			if(current.value.equals(value)) {
				return i;
			}
			current = current.next;
		}
		return -1;
	}
	
	/**
	 * removes element from collection at the given index.
	 * @param index position from which the element is removed.
	 * @throws IndexOutOfBoundsException if given index is not within range [0, size - 1].
	 */
	public void remove(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index must be within range [0, size - 1]");
		}
		if(index == 0) {
			first = first.next;
			first.previous = null;
			size--;
			return;
		}
		if(index == size - 1) {
			last = last.previous;
			last.next = null;
			size--;
			return;
		}
		ListNode current = first.next;
		for(int i = 1; i < index - 1; i++) {
			current = current.next;
		}
		current.previous.next = current.next;
		current.next.previous = current.previous;
		
		size--;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Returns the number of elements in the collection.
	 */
	@Override
	public int size() {
		return size;
	}
	
	
	/**
	 * Checks whether the collections contains the given element. Can check for null <code>null</code> values.
	 * @param value Object value to search for in the collection.
	 * @return <code>true</code> if given element is found, <code>false</code> otherwise.
	 */
	@Override
	public boolean contains(Object value) {
		ListNode current = first;
		for(int i = 0; i < size; i++) {
			if(current.value.equals(value)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}
	
	/**
	 * Checks if the collection has a given element determined by equals method.
	 * If it does, removes only one recurrence of it. 
	 */
	@Override
	public boolean remove(Object value) {
		int index = indexOf(value);
		if(index == -1) {
			return false;
		}
		remove(index);
		return true;
	}
	
	/**
	 * Allocates a new array filled with this collections content, and returns it.
	 */
	@Override
	public Object[] toArray() {
		
		ArrayIndexedCollection array = new ArrayIndexedCollection(this, size);
		return array.toArray();
	}
	
	@Override
	public void forEach(Processor processor) {
		ListNode current = first;
		
		for(int i = 0; i < size; i++) {
			processor.process(current.value);
			current = current.next;
		}
	}
	
	
}

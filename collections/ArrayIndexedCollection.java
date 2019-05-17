package hr.fer.zemris.java.custom.collections;

import java.util.Arrays;

/**
 * This is a resizable array-backed collection of objects 
 * that implements the Collection class. Implements all Collection operations. 
 * Permits duplicate elements but not <code>null</code> references.
 * @author Marko Benačić
 *
 */

// NULL REFERENCES NOT ALLOWED! Duplicates allowed.

public class ArrayIndexedCollection extends Collection{
	
	/**
	 * default size of the underlying array. Will be used if initial capacity is not given.
	 */
	private static int DEFAULT_CAPACITY = 16;
	
	
	/**
	 * Number of elements in the array.
	 */
	private int size;
	
	/**
	 * Array containing elements of the collection.
	 */
	private Object[] elements;
	
	/**
	 * Default constructor. Creates an empty collection with default capacity (which is 16).
	 */
	public ArrayIndexedCollection() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * Creates an empty collection with given initial capacity.
	 * @param initialCapacity specified capacity of the collection.
	 * @throws IllegalArgumentException if capacity is less than 1.
	 */
	public ArrayIndexedCollection(int initialCapacity) {	
		if(initialCapacity < 1) {
			throw new IllegalArgumentException("initialCapacity can't be less than 1");
		}
		
		this.size = 0;
		this.elements = new Object[initialCapacity];
	}
	
	/**
	 * creates a collection which is a copy of given collection. Initial capacity is 16 unless given collection
	 * is larger than 16 elements, in that case initial capacity will be the size of given collection.
	 * @param other collection to be copied.
	 */
	public ArrayIndexedCollection(Collection other) {
		this(other, DEFAULT_CAPACITY);  // mozda this(other, 16); ???
	}
	
	/**
	 * Creates a collection which is a copy of given collection, with given initial capacity.
	 * If given initial capacity is less than number of elements in given collection, new collection is the size 
	 * of the given collection.
	 * @param other collection to be copied
	 * @param initialCapacity initial capacity for the new collection.
	 * @throws NullPointerException if given collection is null.
	 * @throws IllegalArgumentException if initial capacity is less than 1.
	 */
	public ArrayIndexedCollection(Collection other, int initialCapacity) {
		if(other == null) {
			throw new NullPointerException("Given collection can't be null");
		}
		if(initialCapacity < 1) {
			throw new IllegalArgumentException("Initial capacity can't be less than 1");
		}
		
		if(initialCapacity < other.size()) {
		//	this.size = other.size();	ne treba ovo jer ce metoda add povecavati size
			this.size = 0;
			this.elements = new Object[size];
			this.addAll(other);
			// Arrays.copyOf() ???
		}else {
		//	this.size = other.size();
			this.size = 0;
			this.elements = new Object[initialCapacity];
			this.addAll(other);
		}
	}
	
	/**
	 * Adds given object into the collection at the first available slot. Complexity O(1).
	 * @param value object to be added to the collection.
	 * @throws NullPointerException if given value is null.
	 */
	@Override
	public void add(Object value) {
		if(value == null) {
			throw new NullPointerException("Value can not be null!");
		}
		
		insert(value, size);
	
	//	if(size == elements.length) {
	//		elements = Arrays.copyOf(elements, size*2);
	//		elements[size] = value;
	//		size++;
	//	}else {
	//		elements[size] = value;
	//		size++;
	//	}
	}
	
	/**
	 * Returns object at the index position in the collection. Complexity O(1).
	 * @param index position in the collection from which the object will be retrieved.
	 * @return object at indexed position
	 * @throws IndexOutOfBoundsException if index < 0 or index is not within [0,size-1].
	 */
	public Object get(int index) {
		if(index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException("index must be within [0, size - 1]");
		}
		
		return elements[index];
	}
	
	/**
	 * Removes all elements from the collection. Capacity remains the same.
	 */
	@Override
	public void clear() {
		Arrays.fill(elements,null);
		size = 0;
	}
	
	
	/**
	 * insert the given value at the given position in the collection.
	 * Average complexity O(n).
	 * @param value Object to be inserted in the collection
	 * @param position position at which the value will be inserted, must be within [0,size] (inclusive)
	 * @throws IndexOutOfBoundsException if index is not within [0,size] (inclusive)
	 */
	public void insert(Object value,int position) {
		if(position < 0 || position > size ) {
			throw new IndexOutOfBoundsException("Index must be within [0,size]");
		}
		
		// reallocate if elements has no free space OR required position is at the end of elements that has no free space
		if((size == elements.length) || ((position == size) && (size == elements.length))) {
			elements = Arrays.copyOf(elements, size*2);
		}
		
		if(position < size) {
			for(int i = size - 1; i >= position; i--) {
				elements[i+1] = elements[i];
			}
			elements[position] = value;
		}else {
			elements[position] = value;
		}
		
		size++;
	}
	
	
	/**
	 * Removes element from specified index from collection.
	 * @param index position from which the element must be removed.
	 * @throws IndexOutOfBoundsException if index is not within [0, size-1]
	 */
	public void remove(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index must be within [0,size-1]");
		}
		
		if(index == size-1) {
			elements[index] = null;
		}else {
			for(int i = index; i < size - 1; i++) {
				elements[i] = elements[i + 1];
			}
			elements[size - 1] = null;   // had to do it this way in case array was full.
		}
		size--;
	}
	
	/**
	 * Returns <code>true</code> if has no elements, <code>false</code> otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return size==0;
	}
	
	/**
	 * returns number of elements in the collection.
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Returns <code>true</code> if collection contains given value, <code>false</code> otherwise.
	 * @param value object which we check whether it is in the collection.
	 */
	@Override
	public boolean contains(Object value) {
		
		for(int i = 0; i < size; i++) {
			if(elements[i].equals(value)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if the collection has a given element determined by equals method.
	 * If it does, removes only one recurrence of it.
	 * @param value object to remove from collection
	 * @return boolean value whether element was found and removed.
	 */
	@Override
	public boolean remove(Object value) {
		for(int i = 0; i < size; i++) {
			if(elements[i].equals(value)) {
				remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Allocates a new array filled with this collections content, and returns it.
	 */
	@Override
	public Object[] toArray() {
		return Arrays.copyOf(elements, size);
	}
	
	
	@Override
	public void forEach(Processor processor) {
		
		for(int i = 0; i < size; i++) {
			processor.process(elements[i]);
		}
		
	}
	
	
}

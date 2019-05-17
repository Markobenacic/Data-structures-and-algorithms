package hr.fer.zemris.java.custom.collections;

/**
 * This class represents an implementation of a stack that works with Objects.
 * @author Marko Benačić
 *
 */
public class ObjectStack {
	
	/**
	 * Underlying data structure of our stack. Has all method needed to make a stack.
	 */
	private ArrayIndexedCollection collection;
	
	
	
	/**
	 * Default constructor. Creates an empty stack.
	 */
	public ObjectStack() {
		this.collection = new ArrayIndexedCollection();
	}
	
	/**
	 * 
	 * @return <code>true</code> if stack has no elements, <code>false</code> otherwise
	 */
	public boolean isEmpty() {
		return collection.isEmpty();
	}
	
	/**
	 * 
	 * @return number of elements on the stack.
	 */
	public int size() {
		return collection.size();
	}
	
	/**
	 * adds value on top of the stack.
	 * @param value value to be pushed on the stack.
	 * @throws IllegalArgumentException if given value is <code>null</code>.
	 */
	public void push(Object value) {
		if(value == null) {
			throw new IllegalArgumentException("Can not push null on stack");
		}
		collection.add(value);
	}
	
	/**
	 * Pops the object from stack(retrieves and removes from stack).
	 * @return popped object
	 * @throws EmptyStackException if stack is empty.
	 */
	public Object pop() {
		if(isEmpty()) {
			throw new EmptyStackException("Can not pop from empty stack.");
		}
		Object popped = collection.get(size() - 1);
		collection.remove(size() - 1);
		
		return popped;
	}
	
	/**
	 * peeks the object from stack (retrieves, but does not remove from stack).
	 * @return peeked object.
	 * @throws EmptyStackException if stack is empty.
	 */
	public Object peek() {
		if(isEmpty()) {
			throw new EmptyStackException("Can not pop from empty stack.");
		}
		
		return collection.get(size() - 1);
	}
	
	/**
	 * Removes all elements from stack.
	 */
	public void clear() {
		collection.clear();
	}
}

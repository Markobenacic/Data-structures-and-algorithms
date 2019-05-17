package hr.fer.zemris.java.custom.collections;
/**
 * Abstract model of a class that represents some general collection of objects 
 * (it is not an abstract class because of the constraints of assigned homework, 
 * but will be referred as such for the sake of convenience).
 * Because it is just an abstract model, 
 * it should not be used, but it needs to be extended and implemented and its implementations should be used.
 * 
 * @author Marko Benacic
 *
 */
public class Collection {
	
	/**
	 * Default constructor, should not be called by anyone except classes that extend this class.
	 */
	protected Collection() {
		
	}
	
	/**
	 * Method that checks whether the collection has no elements.
	 * 
	 * @return <code>true</code> if has no elements, <code>false</code> othervise.
	 */
	public boolean isEmpty() {
		if(size() != 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * Method that returns the number of elements in the collection.
	 * In this specific class, method returns 0. Needs to be implemented.
	 * @return number of elements of collection, if the collection is empty, returns 0;
	 */
	public int size() {
		return 0;
	}
	
	/**
	 * Adds the given object into the collection.
	 * In this specific class, method does nothing. Needs to be implemented.
	 * @param value value to be added in the collection.
	 */
	public void add(Object value) {
	}
	
	/**
	 * Checks whether the collections contains the given element.
	 * In this specific class, method returns false. Needs to be implemented.
	 * @param value Object value to search for in the collection.
	 * @return <code>true</code> if given element is found, <code>false</code> otherwise.
	 */
	public boolean contains(Object value) {
		return false;
	}
	
	/**
	 * Checks if the collection has a given element determined by equals method. 
	 * If it does, removes only one recurrence of it.
	 * In this specific class, method returns false. Needs to be implemented.
	 * @param value Object value to be removed from collection.
	 * @return <code>true</code> if the object is found and removed, <code>false</code> otherwise.
	 */
	public boolean remove(Object value) {
		return false;
	}
	
	/**
	 * Allocates a new array filled with this collections content, and returns it. 
	 * Never returns null.In this specific class, method throws UnsupportedOperationException. Needs to be implemented.
	 * @return elements of the given collection in an Array.
	 */
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Calls Processor.process for each element of the collection. 
	 * @param processor Processor of which the process method will be used over each element of the collection. 
	 * Specific implementation of the Processor class must be given.
	 * 
	 */
	public void forEach(Processor processor) {
	}
	
	/**
	 * Adds into the current collection all elements from the given collection.
	 * @param other collection from which the elements will be copied.
	 */
	public void addAll(Collection other) {
		
		class AddProcessor extends Processor{
			@Override
			public void process(Object value) {
				add(value);
			}
		}
		
		other.forEach(new AddProcessor());
	}
	
	/**
	 * Removes all elements from this collection. In this specific class, method does nothing. Needs to be implemented.
	 */
	public void clear() {
	}
	
}

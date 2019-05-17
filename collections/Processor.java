package hr.fer.zemris.java.custom.collections;
/**
 * This is an abstract model of an object capable of performing some operation on the passed object.
 * Because it is an abstract model, it should not be used, only classes that extend Processor and implement 
 * the public method <code>process</code> should be used.
 * Contains only one method; <code>process</code>, which should be implemented and overridden by another class.
 * 
 * @author Marko Benacic
 *
 */
public class Processor {
	
	/**
	 * Method that performs some operation with the passed object. Needs to be overridden and implemented.
	 * @param value Object value that over which the implemented operation will be made.
	 */
	public void process(Object value) {
	}

}

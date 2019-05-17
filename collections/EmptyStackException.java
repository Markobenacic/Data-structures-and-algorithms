package hr.fer.zemris.java.custom.collections;

/**
 * Simple exception for handling operations with empty stack.
 * @author Marko Benačić
 *
 */
public class EmptyStackException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EmptyStackException() {
	}

	public EmptyStackException(String message) {
		super(message);
	}

	public EmptyStackException(Throwable cause) {
		super(cause);
	}

	public EmptyStackException(String message, Throwable cause) {
		super(message,cause);
	}
}

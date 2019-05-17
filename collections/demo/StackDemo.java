package hr.fer.zemris.java.custom.collections.demo;

import hr.fer.zemris.java.custom.collections.ObjectStack;

/**
 * Program that accepts one line argument: basic math expression that we wish to calculate, using a basic stack implementation. 
 * Expression must be given in form of postfix representation. Program accepts ONLY ONE argument, which should be a string
 * containing numbers and basic math symbols ( '+', '-', '*', '/' and '%'). Symbols and numbers must be seperated by white space.
 * 
 * @author Marko Benačić
 *
 */
public class StackDemo {
	
	/**
	 * Main method that starts the program.
	 * @param args only ONE argument must be given eg. "-1 8 2 / +".
	 */
	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Wrong number of arguments, number of arguments required: 1");
			System.out.println("Closing...");
			return;
		}
		
		String [] elements = args[0].split("\\s+");
		ObjectStack stack = new ObjectStack();
		
		
		for(int i = 0; i < elements.length; i++) {
			try {
				
				calculatePostfix(elements[i], stack);
				
			}catch(ArithmeticException ae) {
				System.out.println("you can't divide by zero! Closing...");
				return;
			}catch(IllegalArgumentException iae) {
				System.out.println("illegal expression. Closing...");
				return;
			}catch(Exception e) {
				// ovaj catch je za svaki slucaj, da ne izbaci korisniku citav stacktrace.
				System.out.println("Something went horribly wrong! Expression is invalid. Closing...");
				return;
			}
		}
		
		if(stack.size()!=1) {
			System.err.println("Something went wrong, stack size is not 1");
		}else {
			System.out.println("Expression evaluates to " + stack.pop());
		}
		
	}
	
	/**
	 * Calculates one iteration of our postfix stack algorithm. 
	 * @param element number or symbol to be calculated.
	 * @param stack stack that calculates the expression.
	 * @throws NumberFormatException if given expression is invalid.
	 */
	public static void calculatePostfix(String element, ObjectStack stack) {
		int number;
		
		try {
			number = Integer.parseInt(element);
		}catch(NumberFormatException nfe) {	// slucaj kada je u elementu matematicka operacija(jer parse baca NumberFormatEx)
			try {
				int number1 = Integer.parseInt((String)stack.pop());
				int number2 = Integer.parseInt((String)stack.pop());
				
				if(element.equals("+")) {
					stack.push(Integer.toString(number2 + number1));
				}else if (element.equals("-")) {
					stack.push(Integer.toString(number2 - number1));
				}else if(element.equals("/")) {
					stack.push(Integer.toString(number2 / number1));
				}else if(element.equals("*")) {
					stack.push(Integer.toString(number2 * number1));
				}else if(element.equals("%")) {
					stack.push(Integer.toString(number2 % number1));
				}else {
					throw new IllegalArgumentException("Can't calculate that");
				}
				return;
			}catch(NumberFormatException nfe2) {	// ako neka od ova dva parseInt baci iznimku, expression je invalid.
				throw new NumberFormatException("invalid expression, should have been integers here"); //bacam u main da on moze terminirat program.
			}
		}
		
		//Ako parse ne baci NumberFormatException, onda je to broj, stavi ga na stog. 
		stack.push(Integer.toString(number));
		return;
	}
	
}

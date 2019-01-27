// FactorialExplained.java
//
// A detailed look at the execution of the factorial function. A series of print statements
// demonstrates the flow of execution.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class FactorialExplained {

	int recursiveFactorial(int num){
		
		System.out.println("Starting the factorial(" + num + ") invocation");
		
		if(num == 1){
			System.out.println("Hit the terminating condition - returning 1\n");
			return 1;
		}
		else {
			System.out.println("We have to calculate factorial(" + (num - 1) + ") first...\n");
			int temp = recursiveFactorial(num - 1);
			System.out.println("...This is the factorial(" + num + ") invocation again");
			System.out.println( (num - 1)  + "! has been calculated as " + temp);
			System.out.println("Now returning " + num + " * " + (num - 1) + "!\n");
			return num * temp;
		}
	}
	
	
	public static void main(String[] args) throws IOException {

		String inputString; 
		int num;
		String more = "y";
		
		FactorialExplained calc = new FactorialExplained();
		
		//	Reading from standard input
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		
		do{
			System.out.print("\nEnter a Number: ");
			inputString = stdin.readLine();
			num = Integer.parseInt(inputString);
		
			System.out.println("Recursive calculation of " + num + "! = " + calc.recursiveFactorial(num));
			
			System.out.print("\nContinue (y/n)? ");
			more = stdin.readLine();
		}
		while(more.equals("y"));
		
	}
	
	
}

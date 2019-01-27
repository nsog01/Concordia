// Factorial.java
//
// The "classic" recursive demo. The Factorial function can be described as 
// n! = n * (n - 1) * (n - 2)... * 2 * 1
//
// We can also describe it with the following recursive definition
// n! = n * (n - 1)!, where n! = 1 for n = 1.
//
// This little program simply calculates n! with a recursive method. It also provides a
// non-recrusive solution for comparison purposes.

import java.io.*;


public class Factorial {

	// The recursive form. Note the terminating condition and the recursive call.
	int recursiveFactorial(int num){
		
		if(num == 1)
			return 1;
		else 
			return num * recursiveFactorial(num - 1);
	}
	
	// the non-recrusive form
	int nonRecursiveFactorial(int num){
		
		int result = 1;
		for(int i = 2; i <= num; i++)
			result *= i;
		
		return result;
	}
	
	public static void main(String[] args) throws IOException {

		String inputString; 
		int num;
		String more = "y";
		
		Factorial calc = new Factorial();
		
		//	Reading from standard input
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		
		do{
			System.out.print("\nEnter a Number: ");
			inputString = stdin.readLine();
			num = Integer.parseInt(inputString);
		
			System.out.println("Recursive calculation of " + num + "! = " + calc.recursiveFactorial(num));
			System.out.println("Non recursive calculation of " + num + "! = " + calc.nonRecursiveFactorial(num));
			
			System.out.print("\nContinue (y/n)? ");
			more = stdin.readLine();
		}
		while(more.equals("y"));
		
	}
}

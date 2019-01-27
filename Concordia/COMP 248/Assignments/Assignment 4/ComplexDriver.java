//**************************************************************************************
//Assignment #4
//Written by: Jordan Hubscher, ID: 7019696
//For Comp 248 Section EE -Fall 2013
//**************************************************************************************

//This program will create two objects from the Complex class and initialize them using a no-argument constructor and
//an argument based constructor. It will then add, multiply and compare both objects, displaying their results on screen
//as well as copying the values of the instance variables of the second object, to the first. 
//Then, it will re-evaluate them once more.

import java.util.Scanner;

public class ComplexDriver 
{

	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		
		Complex c1 = new Complex();
		System.out.println(c1);
		
		System.out.println("Please enter a value for each instance variable (a, b) for  object c2: ");
		System.out.print("a: "); 
		double c2a = input.nextDouble();//gathering user input for instance variable a of object c2.
		System.out.print("\nb: ");
		double c2b = input.nextDouble();//gathering user input for instance variable b of object c2.
		System.out.println("");
		Complex c2 = new Complex (c2a, c2b);//creating and initializing object c2 with argument based constructor.
		
		System.out.println("Complex number c1 is: " + c1);//displaying formatted version of c1.
		System.out.println("Complex number c2 is: " + c2);//displaying formatted version of c2.
		
		System.out.println(c1.addComplex(c2));//adding first and second complex number appropriately.
		System.out.println(c1.productComplex(c2));//multiplying first and second complex number appropriately.
		System.out.println("Comparison of both objects for equality is: " + c1.equals( c2));
		//displaying truth value of the equality comparison between the two complex numbers.
		
		c1.seta(c2.geta());
		c1.setb(c2.getb());
		System.out.println("New value of c1: " + c1.geta() + " + " + c1.getb() + "i");//copied values from c2 to c1.
		
		System.out.println("-------------------------------------------------------");
		System.out.println(c1.addComplex(c2));//adding once more.
		System.out.println(c1.productComplex(c2));//multiplying once more.
		System.out.println("Comparison of both objects for equality is: " + c1.equals(c2));//comparing once more.
		
		input.close();
	}

}

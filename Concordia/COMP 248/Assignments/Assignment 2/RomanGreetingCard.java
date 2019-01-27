//***********************************************************************************
//Assignment #2
//Written By: Jordan Hubscher, ID: 7019696
//For Comp 248 Section EE -Fall 2013
//***********************************************************************************

/* This program will convert any integer between 1 and 3999 inclusively to Roman numeral form. */

import java.util.*;

public class RomanGreetingCard 

{


	public static void main(String[] args) 
	
	{	 
		System.out.println("Enter a year between 1 and 3999: ");
		
		Scanner input=new Scanner(System.in);
		int user=input.nextInt();
		
		if (user >= 1 && user <= 3999 )//checking to see if input is valid for program.
		{
			if (user >= 2000)
			{ 
				System.out.print("MM");
				user-=2000;
			}
			if (user >= 1000)
			{
				System.out.print("M");
				user-=1000;
			}
			if (user >=900)//new if statement introducing next maximum digit i.e. 900.
			{
				System.out.print("CM");
				user-=900;
			}
			else if (user >= 500)//500 will create a remainder of either 300, 200, 100.
			{
				System.out.print("D");
				user-=500;
			}
			else if (user >= 400)
			{
				System.out.print("CD");
				user-=400;
			}
			if (user >= 200)//new if statement to add to remainder after 500.
			{
				System.out.print("CC");
				user-=200;
			}
			if (user >= 100)//new if statement will add to remainder of 200, if the input was 300.
			{
				System.out.print("C");
				user-=100;
			}
			if (user >= 90)//new if statement introducing next maxmimum digit, i.e. 90.
			{
				System.out.print("XC");
				user-=90;
			}
			else if (user >=50)//50 will create remainder for 30, 20, 10.
			{
				System.out.print("L");
				user-=50;
			}
			else if (user >= 40)
			{
				System.out.print("XL");
				user-=40;
			}
			if (user >= 20)//new if statement will add to the remainder of 50.
			{
				System.out.print("XX");
				user-=20;
			}
			if (user >= 10)//new if statement will add to remainder of 20 if the input was 30.
			{
				System.out.print("X");;
				user-=10;
			}
			if (user >= 9)//new if statement introducing next maximum variable, i.e. 9.
			{
				System.out.print("IX");
				user-=9;
			}
			if (user >= 5)//5 will create remainder of either 3, 2, 1.
			{
				System.out.print("V");
				user-=5;
			}
			else if (user >= 4)
			{
				System.out.print("IV");
				user-=4;
			}
			if (user >= 2)//new if statement will add to the remainder of 5.
			{
				System.out.print("II");
				user-=2;
			}
			if (user >= 1)//new if statement will add to the remainder of 2 if the input was 3.
			{
				System.out.print("I");
				user-=1;
			}
		}
		else 
			{
			System.out.print("Invalid input, try again please.");
			System.exit(0);
			}
		
		System.out.println("\n\nThank you for using this Roman Numeral Converter.\nBYE BYE :D");
		
		input.close();
	}
		

}



//********************************************************************
//  Age.java       Author: Lewis/Loftus modified by L. Kosseim
//
//  Demonstrates the use of an if statement.
//********************************************************************

import java.util.Scanner;

public class Age
{
   //-----------------------------------------------------------------
   //  Reads the user's age and prints comments accordingly.
   //-----------------------------------------------------------------
   public static void main (String[] args)
   {
      Scanner myKeyboard = new Scanner(System.in);
      final int MINOR = 21;

      System.out.print ("Enter your age: ");
      int age = myKeyboard.nextInt();

      System.out.println ("You entered: " + age);

      if (age < MINOR)
         System.out.println ("Youth is a wonderful thing. Enjoy.");

      System.out.println ("Age is a state of mind.");
   }
}

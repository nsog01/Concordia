//********************************************************************
//  StringWhile.java       Author: L. Kosseim
//
//  Demonstrates the use of a nested while loops with Strings
//********************************************************************

import java.util.Scanner;

public class StringWhile
{
   //-----------------------------------------------------------------
   //  can you figure out what this code does?
   //-----------------------------------------------------------------
   public static void main (String[] args)
   {
        Scanner keyboard = new Scanner(System.in);
      
        String str, another = "y";
        int left, right;

        do
        {
            System.out.println ("Enter a string:");
            str = keyboard.next();

            left = 0;
            right = str.length() - 1;

            while (str.charAt(left) == str.charAt(right) && left < right) {
                left++;
                right--;
            }

            System.out.println ((left < right)? "NO": "YES");

            System.out.print ("Test another string (y/n)? ");
            another = keyboard.next();
        }
        while (another.equalsIgnoreCase("y"));
  
   }
}

//********************************************************************
//  Wages.java       Author: Lewis/Loftus  Modified by L. Kosseim
//
//  Demonstrates the use of an if-else statement.
//********************************************************************

import java.text.NumberFormat;
import java.util.Scanner;

public class Wages
{
   //-----------------------------------------------------------------
   //  Reads the number of hours worked and calculates wages.
   //-----------------------------------------------------------------
   public static void main (String[] args)
   {
      final double RATE = 8.25;  // regular pay rate
      final int STANDARD = 40;   // standard hours in a work week
      final double OVERTIME_RATE = 1.5;   // rate of pay for overtime hours
      
      double pay = 0.0;
      
      Scanner in = new Scanner(System.in);

      System.out.print ("Enter the number of hours worked: ");
      int hours = in.nextInt();

      System.out.println ();

      // Pay overtime at "time and a half"
      if (hours > STANDARD)
         pay = STANDARD * RATE + (hours-STANDARD) * (RATE * OVERTIME_RATE);
      else
         pay = hours * RATE;

      NumberFormat fmt = NumberFormat.getCurrencyInstance();
      System.out.println ("Gross earnings: " + fmt.format(pay));
   }
}

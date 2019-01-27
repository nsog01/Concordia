//********************************************************************
//  Prime.java       Author: L. Kosseim
//
//  Demonstrates the use of a nested for loops
//********************************************************************


public class Prime
{
   //-----------------------------------------------------------------
   // displays prime numbers from 10 to 50 
   //-----------------------------------------------------------------
   public static void main (String[] args)
   {
        boolean divisible = false; // is the number divisible by the candiate
        final int UP = 50; // upper bound
        final int LOW = 10; // lower bound

        for (int number = LOW; number <= UP; number++)
        {
            for (int candidate = 2; candidate < number; candidate++)
            {
                divisible = (number % candidate) == 0;
                if (divisible)
                    break; 
            }
            if (!divisible) 
                System.out.print(number + " ");
        }
        System.out.println();
   }
}

//********************************************************************
// Author: W. Savitch (modified by L. Kosseim)
//
//  This program demonstrates how to use an cascade of if statements
//********************************************************************

import java.util.Scanner;

public class IncomeTax
{
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        double netIncome, tax, fivePercentTax, tenPercentTax;
        
        final double LOW_TAX_RATE = 0.05; // 5%
        final double HI_TAX_RATE = 0.1; // 10%
        
        final double LOW_TAX_LIMIT = 15000; // you pay 5% if income is between $15,000 and $30,000.
        final double HI_TAX_LIMIT = 30000; // you pay 5% if income is > $30,000.
        
        System.out.println("Enter net income.\n"
                      + "Do not include a dollar sign or any commas.");
        netIncome = keyboard.nextDouble( );

        if (netIncome <= LOW_TAX_LIMIT)
            tax = 0;
        else if ((netIncome > LOW_TAX_LIMIT) && (netIncome <= HI_TAX_LIMIT))
            //tax = 5% of amount over $15,000
            tax = (LOW_TAX_RATE*(netIncome - LOW_TAX_LIMIT));
        else //netIncome > $30,000
        {
            //fivePercentTax = 5% of income from $15,000 to $30,000.
            fivePercentTax = LOW_TAX_RATE*LOW_TAX_LIMIT;
            //tenPercentTax = 10% of income over $30,000.
            tenPercentTax = HI_TAX_RATE*(netIncome - HI_TAX_LIMIT);
            tax = (fivePercentTax + tenPercentTax);
        }

        System.out.printf("Tax due = $%.2f%n", tax);
    }
}






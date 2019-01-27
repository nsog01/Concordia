//**************************************************************************************
//Assignment #1
//Written by: Jordan Hubscher, ID: 7019696
//For Comp 248 Section EE -Fall 2013
//**************************************************************************************

/* This program will convert the given amount of kilometers into it's respective quantities
   of nautical miles, statute miles, and furlongs. 
*/

public class Converter {

	public static void main(String[] args) {
	
		
		double km=2.5; //km is will be the distance in kilometers needed to be converted.
		double CNM=(0.54); //CNM is the conversion rate from kilometers to nautical miles.
		double CSM= (0.62154); //CSM is the conversion rate from kilometers to statute miles.
		double CF=(4.97232); //CF is the conversion rate from kilometers to furlongs.

		System.out.println("Conversion of Linear Distances.");
		System.out.println("-------------------------------");
		System.out.println("Linear distance in kilometers(km) to convert: " + km);

		System.out.println("The Distance in NM is: " + km*CNM);
		/* multiplies the distance in kilometers
		 * by the nautical miles conversion rate and outputs it to the screen.*/

		System.out.println("The Distance in SM is: " + km*CSM);
		/* multiplies the distance in kilometers by the statute miles 
		 * conversion rate and outputs it the screen.*/

		System.out.println("The Distance in Furlong is: " + km*CF);
		/* multiplies the distance in kilometers by the furlongs 
		 * conversion rate and outputs it to the screen.*/
		
		System.out.println("\n*End of linear converter program.*");
	}

}

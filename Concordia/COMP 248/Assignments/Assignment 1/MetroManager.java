//**************************************************************************************
//Assignment #1
//Written by: Jordan Hubscher, ID: 7019696
//For Comp 248 Section EE -Fall 2013
//**************************************************************************************

/*This program will post current status or a metro train based on the specific
  data given. This is the first version of the program.
*/

public class MetroManager {

	public static void main(String[] args) {
		
	
	    int station_NB=7, metro_NB=123, passengers_IN=110, passengers_OFF=16, passengers_ON=59;
		/* these int variables list, from left to right; the station number the metro is currently at,
		 * the metro train number, the number of passengers currently INside the metro, the number of
		 * passengers getting OFF the metro, and the number of passengers getting ON the metro.*/

		System.out.println("Metro Manager (Version 1)");
		System.out.println("-------------------------");
		System.out.println("Metro train currently in the station is " + metro_NB);
		//Displaying which metro train is at station_NB.
		System.out.println("Metro "+ metro_NB + " is currently at station " + station_NB);
		//Displaying which station metro_NB train is at.
		System.out.println("Number of pasengers curently in Metro " + metro_NB + ": " + passengers_IN);
		//Displaying the number of passengers cuurently in metro_NB.
		System.out.println("Number of passengers getting off the Metro: " + passengers_OFF);
		//Displaying the number of passengers getting off metro_NB.
		System.out.println("Number of passengers getting on the Metro: " + passengers_ON);
		//Displaying the number of passengers getting on metro_NB
		
		System.out.println("\nCurent Status of Metro train " + metro_NB);
		System.out.println("-------------------------------");
		System.out.println("At its arrival at station " + station_NB + ":");
		//Displaying its arrival at station_NB, where the colon symbolizes further data listing.
		System.out.println("\tThere were " + passengers_IN + " passenger(s) in the Metro.");
		//Re-stating how many passengers were in metro_NB.
		System.out.println("\t" + passengers_OFF + " passenger(s) left the metro.");
		//Re-stating how many passengers got off metro_NB.
		System.out.println("\t" + passengers_ON + " passenger(s) got on the Metro.");
		//Re-stating how many passengers got on metro_NB.
		
		System.out.print("Departing from station " + station_NB);
		station_NB=station_NB+1; /* Re-defining station_NB to implement the successive station 
		*the metro will travel to.*/
		System.out.print(", bound for station " + station_NB);
		System.out.print("\n\tThere are now " + ((passengers_IN-passengers_OFF)+passengers_ON) + " passengers(s)");
		System.out.print(" in the Metro."); 
		/*Displaying how many passengers remain in metro_NB after subtracting passengers_OFF from
		 * passengers_IN, and then adding passengers_ON.*/
		
		System.out.println("\n\n*Thank you for using VERSION 1 of the Metro application.");
		System.out.println("Keep an eye out for new releases of this application.*");
		 

	}

}

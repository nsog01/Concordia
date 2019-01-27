//********************************************************************
//TowersOfHanoi.java       Author: Lewis/Loftus
//
//Represents the classic Towers of Hanoi puzzle.
//********************************************************************
//
// THe classic Towers of Hanoi problem. Note how simple the core "MoveTower"
// method is. COmpare this to the non recrusive solution ot the Towers Of
// Hanoi.


import java.io.*;


public class TowersOfHanoi {
   private int totalDisks;
   
   //  Sets up the puzzle with the specified number of disks.
   public TowersOfHanoi (int disks){
      totalDisks = disks;
   }

   //  Performs the initial call to moveTower to solve the puzzle.
   //  Moves the disks from tower 1 to tower 3 using tower 2.
   public void solve () {
      moveTower (totalDisks, 1, 3, 2);
   }
   
   //  Moves the specified number of disks from one tower to another
   //  by moving a subtower of n-1 disks out of the way, moving one
   //  disk, then moving the subtower back. Base case of 1 disk.
   private void moveTower (int numDisks, int start, int end, int temp){
      if (numDisks == 1)
         moveOneDisk (start, end);
      else{
         moveTower (numDisks-1, start, temp, end);
         moveOneDisk (start, end);
         moveTower (numDisks-1, temp, end, start);
      }
   }

   
   //  Prints instructions to move one disk from the specified start
   //  tower to the specified end tower.
   private void moveOneDisk (int start, int end){
      System.out.println ("Move one disk from " + start + " to " +
                          end);
   }
   
   
   public static void main (String[] args) throws IOException{
      
   		
      
      	String inputString; 
		int num;
		String more = "y";
		
		TowersOfHanoi towers;
		
		//	Reading from standard input
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		
		do{
			System.out.print("\nEnter the number of plates: ");
			inputString = stdin.readLine();
			num = Integer.parseInt(inputString);
		
			System.out.println("Solving towers of Hanoi for a stack of " + num + " plates...");
			towers = new TowersOfHanoi(num);
			towers.solve();
			
			System.out.print("\nContinue (y/n)? ");
			more = stdin.readLine();
		}
		while(more.equals("y"));
      
   }
   
}

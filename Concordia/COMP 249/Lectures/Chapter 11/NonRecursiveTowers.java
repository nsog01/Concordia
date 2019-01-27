// NonRecursiveTowers.java
//
// This is a non-recrusive version of the Towers of Hanoi. 
//
// You are not required in any way to understand this program. It is simply provided so that
// you can see how a non-recursive solution is sometimes much more complex than a 
// recursive one.
//
//
// Author   : Apostolos Syropoulos
// Program  : Non-recursive Towers of hanoi
// Date     : November 16, 1997
//
import java.io.*;

public class NonRecursiveTowers
{
  static int moves=0; //number of moves so far

	   static int getInt()
	   {
		   String line;
		   BufferedReader in = 
		   new BufferedReader(new InputStreamReader(System.in)); 
		   try
		   {
			  line = in.readLine();
			  int i = Integer.valueOf(line).intValue();
			  return i;
		   }
		   catch (Exception e)
		   {
			  return 1;
		   }
	   }

	   static void hanoi(int   height,
						 char  fromPole,
						 char  toPole,
						 char  withPole)
	   {
		  int[] HeightStack = new int[height]; 
		  char[] fromStack  = new char[height], 
				 toStack    = new char[height], 
				 withStack  = new char[height]; 
		  int[]  ReturnAddr = new int[height]; 
		  int SP  = -1; // Stack Pointer, initially empty stack
		  int SUB = height - 1; //Stack Upper Bound
		  int flag=1; 
		  boolean done=false;
		  char tmp;

		  do
	  {
			 switch (flag)
		 {
			case 1: while (height>0)
				{
						   SP++;
						   if (SP > SUB)
			   {
							  System.out.println();
							  System.out.println("*** Error: Stack Overflow");
							  System.exit(1);
						   }
						   HeightStack[SP] = height;
						   fromStack[SP] = fromPole;
						   toStack[SP] = toPole;
						   withStack[SP] = withPole;
						   ReturnAddr[SP] = 2;
						   height--;
						   tmp = toPole;      // swap
						   toPole = withPole; // values
						   withPole = tmp;    //
			}
						flag=3;
						break;
			case 2: moveDisk(fromPole, toPole);
						SP++;
						if (SP > SUB)
			{
						   System.out.println();
						   System.out.println("*** Error: Stack Overflow");
						   System.exit(1);
						}
						HeightStack[SP] = height;
						fromStack[SP] = fromPole;
						toStack[SP] = toPole;
						withStack[SP] = withPole;
						ReturnAddr[SP] = 3;
						height--;
						tmp = fromPole;      // swap 
						fromPole = withPole; // values
						withPole = tmp;     //
						flag=1;
						break;
			case 3: if (SP >= 0) //stack not empty
				{
						   while (SP >= 0 && flag == 3)
			   {
							  height = HeightStack[SP];
							  fromPole = fromStack[SP];
							  toPole = toStack[SP];
							  withPole = withStack[SP];
							  flag = ReturnAddr[SP];
							  SP--;
			   }
			}
						else
			{
						   done = !done;
			}
						break;
		 }
	  }
		  while (!done);
	   }

	   static void moveDisk(char fromPole, char toPole)
	   {
			moves++;
			System.out.print(fromPole);
			System.out.print(toPole);
			System.out.print(((moves % 20)==0) ? '\n' : ' ');
	   }

	   public static void main(String[] args)
	   {
			int TowerHeight;
			long time1, time2;
			char FromPole='A', ToPole='B', WithPole='C';

			System.out.print("Enter Tower height? ");

			TowerHeight = getInt();
			time1 = System.currentTimeMillis();
			hanoi(TowerHeight, FromPole, ToPole, WithPole);
			time2 = System.currentTimeMillis();
			System.out.println();
			System.out.print(time2-time1);
			System.out.println(" msec execution time");
	   }
}
// over.java
//
// This example demonstrates the difference between method over-loading
// and method over-riding.


// Here we simply provide two versions of the stuff() method.
// One takes an integer argument, the other takes a double
// argument. This is method overloading - Java will figure
// out which one to invoke base upon the parameter list

class OverLoad{

	public int xyz = 9;

	public void stuff(int x){
		System.out.println("x is an int in overLoad");
	}
	
	// overloading
	public void stuff(double x){
		System.out.println("x is a double in overLoad");
	}
		
}

// in the child class, we will over-load stuff() AND
// over-ride stuff()

class OverRide extends OverLoad{
	
	// bad idea - don't do this! You can over-ride a variable
	// but this is a great source of confusion.
	public int xyz = 99;
	
	// overloading in derived class - this time we will take
	// a char argument
	public void stuff(char x){
		System.out.println("x is a char in overRide");	
	}
	
	// over-riding in derived class. Here, we are defining a new 
	// way of processing stuff() when an interger argument is used.
	// we also call the parent version with super() just to show that
	// the two methods are actually different (you don't normally do this)
	public void stuff(int x){
		System.out.println("x is an int in overRide");
		System.out.println("calling parent stuff(int x) from overRide...");	
		super.stuff(x);
	}
	
}


// the test class shows the effect of over-riding and overloading. Run the
// program and make sure you understand the output.
public class Over {

	public static void main(String[] args) {
		
		OverLoad testA = new OverLoad();
		testA.stuff(4);
		testA.stuff(4.0);
		System.out.println("xyz in parent = " + testA.xyz);
		
		System.out.println();
		
		OverRide testB = new OverRide();
		testB.stuff('4');
		testB.stuff(4.0);
		testB.stuff(4);
		System.out.println("xyz in child = " + testB.xyz);

	}
}

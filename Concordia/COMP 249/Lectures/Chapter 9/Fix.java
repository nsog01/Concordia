// Fix.java
//
// In many case, we can actually recover from an exceptional condition (and sometimes
// we can't). In this example, we look at a simple way that you might recover from
// an exception.

// Three simple exceptions. They don't do much themselves - it's just important that 
// they get thrown and caught.
//
// If you run the program, you'll see how the exceptions are processed in the loop. Note
// that some of the methods get processed multiple times - this probably isn't what you
// want in practice.

class myExceptionA extends Exception{ }

class myExceptionB extends Exception{ }

class myExceptionC extends Exception{ }


// the Test class. Basically, it contains three boolean variables - 
// foo, moo, and noo - and three methods for setting these values. It
// then has three methods for checking each value. If the associated
// value is false, an exception is thrown.

class Test3{
	
	boolean foo = false;
	boolean moo = false;
	boolean noo = false;
	
	
	public void foo() throws myExceptionA{
		System.out.println("I'm executing the foo() method...");
		if (foo == false)
			throw new myExceptionA();	
	}
	
	public void moo() throws myExceptionB{
		System.out.println("I'm executing the moo() method...");
		if (moo == false)
			throw new myExceptionB();	
	}
		
	public void noo() throws myExceptionC{
		System.out.println("I'm executing the noo() method...");
		if (noo == false)
			throw new myExceptionC();	
	}
		
	public void setFoo(boolean state){
		foo = state;
	}
	
	public void setMoo(boolean state){
		moo = state;
	}
		
	public void setNoo(boolean state){
		noo = state;
	}
	
	
	// The bar method. The try block sits inside of a loop that only exists once 
	// all of the statements inside the block get executed properly. The try block
	// tries to run the foo(), moo(), and 
	// noo() methods. As exceptions are thrown, they are caught (one at a time!),
	// the problem is fixed, and the try block gets run again.
	
	public void bar(){
		
		boolean valid = false;
		
		System.out.println("Calling the foo, moo, and noo methods...");
		
		while(!valid){
			try{
				foo();
				moo();
				noo();
				valid = true;
				System.out.println("I've made it through the try block successfully");
			}
			catch (myExceptionA e) {
				System.out.println("I caught myExceptionA and I'm fixing it");
				setFoo(true);
			}
			catch (myExceptionB e) {
				System.out.println("I caught myExceptionB and I'm fixing it");
				setMoo(true);
			}
			catch (myExceptionC e) {
				System.out.println("I caught myExceptionC and I'm fixing it");
				setNoo(true);
			}
		}	
	}
}



public class Fix {

	public static void main(String[] args) {
		
		// create the test object and run its bar() method
		Test3 myTest = new Test3();
		myTest.bar();
		
	}
}

// Uncaught.java
//
// In java, the exception handling mechanism will  keep working backwards to try
// to find an exception handler for a thrown exception. Here, we have four classes,
// each calling methods in one another. When an exception is triggered, Java will backtrack
// asll the way back to the first class to find a catch block. (If it doesn't find one at
// all, the program will end with a stack trace.


class myExceptionX extends Exception{ }

class TestX{
	
	TestX2 myTester = new TestX2();
	
	public void alpha() { // no throw clause because we handle excpetion internally
		try{
			System.out.println("Calling the beta method from alpha...");
			myTester.beta();
		}
		catch(myExceptionX e){
			System.out.println("I caught myExceptionX in alpha");	
		}
	}
}


class TestX2{
	
	TestX3 tester = new TestX3();
	
	public void  beta() throws myExceptionX{
		System.out.println("Calling the bar method from beta...");
		tester.bar();
		System.out.println("Finished with bar method in beta...");
	}
}


class TestX3{
	
	TestX4 tester = new TestX4();
	public void bar() throws myExceptionX{
		System.out.println("Calling the foo method from bar...");
		tester.foo();
		System.out.println("Finished with foo method in bar...");
	}
}

// the class that actually throws the exception
class TestX4{
	public void foo() throws myExceptionX{
		throw new myExceptionX();	
	}
}



public class UnCaught {

	public static void main(String[] args) {
		
		// create the test class and start calling methods - 
		// alpha -> beta -> bar -> foo. The method will actually be caught back
		// in alpha.
		TestX myTest = new TestX();
		myTest.alpha();
		
	}
}

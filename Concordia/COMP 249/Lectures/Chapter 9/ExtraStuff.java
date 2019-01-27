// ExtraStuff.java
//
// It's also possible to put additonal variables and methods in your exception.
// YOu can do anything you do with regualr objects. In this case, we added an
// integer value to the exception. This value is provided to the constructor when the
// exception object is created.



// exception class with three different constructors
class MyExceptionExtra extends Exception {
  	private int value;
  
  	public MyExceptionExtra() {}
  
  	public MyExceptionExtra(String msg) { 
  		super(msg); 
  	}
  	
  	public MyExceptionExtra(String msg, int value) {
		super(msg);
		this.value = value;
  	}
  	
  	public int getValue() { 
  		return value; 
  	}
  	
  	public String getMessage() {
		return "Detail Message: " + value + " " + super.getMessage();
  	}
}


class ExtraTest{
	
	public void f() throws MyExceptionExtra {
		System.err.println("Throwing MyExceptionExtra from f()");
		throw new MyExceptionExtra();
	}
	
	public void g() throws MyExceptionExtra {
		System.err.println("Throwing MyExceptionExtra from g()");
		throw new MyExceptionExtra("Originated in g()");
	}
	
	public void h() throws MyExceptionExtra {
		System.err.println("Throwing MyExceptionExtra from h()");
		throw new MyExceptionExtra("Originated in h()", 47);
	}
}




public class ExtraStuff {
  
  	public static void main(String[] args) {
  		
  		ExtraTest myTest = new ExtraTest();
  		
		try {
	  		myTest.f();
		} 
		catch(MyExceptionExtra e) {
	  		System.err.println("Caught exception for f()\n");
		}
	
		try {
	  		myTest.g();
		} 
		catch(MyExceptionExtra e) {
			System.err.println("Caught exception for g()");
			System.err.println(e.getMessage());
			System.err.println();
		}
		
		try {
	  		myTest.h();
		} 
		catch(MyExceptionExtra e) {
			System.err.println("Caught exception for h()n");
			e.getMessage();
			System.err.println("Printing stack trace");
	  		e.printStackTrace();
	  		System.err.println("e.getValue() = " + e.getValue());
		}
  }
}
// Messages.java
//
// You can also add messages to your exception classes. You can do this simply
// by provided a string argument to the constructor and calling super. 
//
// You can see the error message by using the exception getMessage() method or
// by clling the getStackTrace method - which also prints out a list of all
// "ancestor" methods.


class MyException extends Exception {
  public MyException() {}
  public MyException(String msg) { 
    super(msg); 
  }
}



class MessageTest{
	
	public void f() throws MyException {
		System.out.println("Throwing MyException from f()");
		throw new MyException();
	}
	  
	public void g() throws MyException {
		System.out.println("Throwing MyException from g()");
		throw new MyException("Originated in g()");
	}	
}



public class Messages {
  
  public static void main(String[] args) {
  	
  	MessageTest myTest = new MessageTest();
  	
	try {
	  	myTest.f();
	} 
	catch(MyException e) {
	  	e.printStackTrace();
	}
	
	
	try {
	  	myTest.g();
	} 
	catch(MyException e) {
		System.err.println("The error message is: " + e.getMessage());
	  	e.printStackTrace();
	}
	
  }
} 

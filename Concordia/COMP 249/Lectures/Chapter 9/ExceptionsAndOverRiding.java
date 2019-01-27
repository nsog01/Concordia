// ExceptionsAndOverRiding.java
//
// This program shows how methods that throws exceptions (ie pass the buck)
// should be overriden.


 class Parent {
	public void someMethod() throws Exception 
	{} 
}
 class Child extends Parent {
    public void someMethod() throws java.io.IOException 
    {}
}
 class Grandchild extends Child {
    public void someMethod() 
	{}
}
 class BadChild extends Child {
    public void someMethod() throws Exception 
	{}
}

public class ExceptionsAndOverRiding {
    public static void main(String[] arg) {
        Parent p = new Parent();
    }
}

public class StackTrace {
  public static void main (String[] args) { 
	someMethod();
  }
  
  private static void someMethod() {
  	someOtherMethod();
  }
  
  private static void someOtherMethod() {
  	divideByZero();
  }

  private static void divideByZero() {
    int numerator = 10; 
    int denominator = 0; 
    System.out.println(numerator / denominator); 
    System.out.println("This will not be printed"); 
  }
} 
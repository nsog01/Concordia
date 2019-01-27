public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException () {
	    super ("Invalid action - check available funds first");
    }

    public InsufficientFundsException (String message) {
	    super (message);
	    System.out.println ("in the constructor for insufficient funds exception");
    }
} 

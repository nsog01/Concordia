public class UnavailableTransactionException extends Exception {
    public UnavailableTransactionException () {
	    super ("Transaction not available");
    }

    public UnavailableTransactionException (String message) { 
	    super (message);
    }
}

public class MultipleExceptions {
  public static void main (String[] args)
  {
  	try {
		String a = "0";
		int r2 = Integer.parseInt(a) / Integer.parseInt(a);
	}
	catch (Exception e) {
		System.out.println("General Exception");
	}catch (ArithmeticException e) {
		System.out.println("Calculation Error");
	}

	finally {
		System.out.println("Finally");
	}
	System.out.println("Finished");
  }
}
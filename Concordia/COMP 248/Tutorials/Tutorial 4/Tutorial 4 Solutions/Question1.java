
public class Question1 {

	public static void main(String[] args) {
		
		/*
		 * What is the value of the following expressions?
		 */
		System.out.println((1+2 > 4-2 && 12 < 23)); //true
		System.out.println((12 < 23 || 1+2 > 4-2)); //true
		System.out.println((1+2 > 4-2 && 12 > 23)); //false
		System.out.println((1+2 > 4-2 || 12 > 23)); //true
	}

}

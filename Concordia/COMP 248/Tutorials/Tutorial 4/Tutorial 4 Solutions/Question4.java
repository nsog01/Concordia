import java.util.Scanner;


public class Question4 {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		String msg = in.next();
		in.close();
		
		int x = 0;
		int y = 10;
		int z = 100;
		switch(msg.charAt(0))
		{
		case 'a' :
		case 'b' :
			System.out.println("case 1");
			x = (msg.equals("abc") ? (5 + y++) : (--y + z--));
			break;
		case 'c' :
			System.out.println("case 2");
			y /= 5;
		default:
			System.out.println("default");
		}
		System.out.println(x + " " + y + " " + z);
		
		/*
		 * Input: abc
		 * case 1
		 * 15 11 100
		 */
		
		/*
		 * Input: aBC
		 * case 1
		 * 109 9 99
		 */
		
		/*
		 * Input: ccc
		 * 0 2 100
		 */
	}
}

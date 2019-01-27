/*
 * Created on Feb 23, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Recurse {

	static int secret(int a, int b) {
		return (b == 0) ? a : secret(b, a%b);
	}

	public static void main(String[] args) {
		System.out.println(secret(10, 5));
	}
}



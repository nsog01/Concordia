
public class Question2 {

	public static void main(String[] args) {

		int x = 1;
		boolean isFree = false;
		char initial = 'L';
		char code = 'Y';
		String english = "hi";
		String italian = "ciao";
		boolean q = (5 == 6);
		
		System.out.println((true && (5>6))); //false
		System.out.println(((x!=0) || (x%2 ==1))); //true
		System.out.println((isFree | (x<0))); //false
		System.out.println(initial == code); //false
		System.out.println((!!q)); //false
		//System.out.println((0 <= x <= 10)); //error
		//System.out.println((english > italian));//error
		System.out.println(((isFree) ? 4 : 10)); //10
		System.out.println(initial = code); //Y
		System.out.println(("italian".equals(italian)));//false
	}

}

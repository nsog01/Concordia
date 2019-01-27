import java.util.Scanner;


public class Question3 {

	public static void main(String[] args) {

		final int LOW = 116;
		final int MIDDLE = 130;
		final int HIGH = 200;
		
		Scanner in = new Scanner(System.in);
		System.out.print("Enter your weight: ");
		int weight = in.nextInt();
		in.close();
		
		if(weight < LOW)
			System.out.println("Eat 5 banana splits!");
		else if(weight >= LOW && weight <= MIDDLE)
			System.out.println("Eat a banana split!");
		else if(weight > MIDDLE && weight <= HIGH)
			System.out.println("Perfect!");
		else
			System.out.println("Plent of banana splits have been consumed!");
		
	}

}

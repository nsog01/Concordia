import java.util.Scanner;

public class Question5 {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.print("Enter your code (R, C or I): ");
		String code = in.next();
		System.out.print("Enter your monthly consumption: ");
		int consumption = in.nextInt();
		in.close();
		
		char charCode = code.charAt(0);
		double bill = 0.0;
		switch (charCode) {
		case 'R':
			bill = 5.0 +  consumption * 0.05;
			break;
		case 'C':
			if(consumption < 4000000)
				bill = 1000;
			else
				bill = 1000 + (consumption - 4000000) * 0.00025; 
			break;
		case 'I':
			if(consumption < 4000000)
				bill = 1000;
			else if(consumption >= 4000000 && consumption < 10000000)
				bill = 2000;
			else
				bill = 3000;
			break;
		default:
			System.out.println("You've entered an invalid code. Please try again.");
			System.exit(0);
			break;
		}
		System.out.printf("Amount owed: $%.2f",bill);
		System.exit(0);
	}

}

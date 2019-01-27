import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class Shopaholic {
	
	public static void main(String [] args) throws IOException{
		
		Shopaholic sh = new Shopaholic();
		int outfitCounter = 0;
		
		Outfit[] listOutfits = new Outfit[30];
		//Initialize the array with empty Outfits
		for(int i=0;i<listOutfits.length;i++){
			Outfit defaultOutfit = new Outfit();
			listOutfits[i] = defaultOutfit;
		}
		
		outfitCounter = sh.readFromFile(listOutfits); //Get All Outfits stored in the Out.ser File.
	 	
		//Initialize a Scanner	
		Scanner keyboard = new Scanner(System.in);

		System.out.println("*****Welcome to Shopaholic System***** \n");
		System.out.println("- Menu: ");
		System.out.println("1- Add a New Outfit.");
		System.out.println("2- View Amount Spent per Month.");
		System.out.println("0- Exit.");	
		System.out.println("What would you like to do?");
		
		String choice = keyboard.next();
		while(!(choice.equals("0"))){
			
			if(choice.equals("1")){
			
			System.out.println("Please enter month of purchase");
			int month = keyboard.nextInt();
			
			System.out.println("Please enter Type of outfit purchased");
			String type = keyboard.next();
			
			System.out.println("Please enter Price of outfit purchased");
			int price = keyboard.nextInt();
					
			Outfit newOutfit = new Outfit(month,type,price);
			listOutfits[outfitCounter]= newOutfit;		
			outfitCounter ++;
			}
			if(choice.equals("2")){
				
				System.out.println("Please enter month of purchase");
				int month = keyboard.nextInt();
				
				System.out.println("The amount of money spent this month is "+sh.getMonthlyPurchase(month,listOutfits));				
			}
			
			System.out.println("- Menu: ");
			System.out.println("1- Add a New Outfit.");
			System.out.println("2- View Amount Spent per Month.");
			System.out.println("0- Exit.");
			
			System.out.println("What would you like to do?");
			 choice = keyboard.next();
		}
		System.out.println("Thank you for using Shopaholic System!");	
		//Store ListofOutfits in Serialization File
		sh.writeToFile(listOutfits,outfitCounter);
	}
	
	
	public int readFromFile(Outfit[] listOutfit){
		
		int outfitCounter = 0;
		
		FileInputStream fin = null;
		ObjectInputStream in = null;
		try {
		 fin = new FileInputStream("Outfits.ser");
		 in = new ObjectInputStream(fin);
		 
		 outfitCounter = (Integer) in.readObject(); 	
		 int readCount = 0;
		 while(readCount < outfitCounter ){
		listOutfit[readCount] = (Outfit) in.readObject();
				 readCount ++;
		 }
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("File Empty");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outfitCounter;
	}
	
	public void writeToFile(Outfit[] listOutfits, int outfitCounter){
		 FileOutputStream fout = null;
		 ObjectOutputStream out = null;
		 try {
		 fout = new FileOutputStream("Outfits.ser");
		 out = new ObjectOutputStream(fout);
		 out.writeObject((Integer)outfitCounter); // Write Counter
		 for(int i=0; i< outfitCounter ;i++){
			 out.writeObject(listOutfits[i]);
		 }
		 } catch (IOException e) {
		 }
		 finally{
		 try {
		 fout.close();
		 out.close();
		 } catch (IOException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
		 }
	}
	
	public int getMonthlyPurchase(int month,Outfit[] listOutfits){
		
		int amount = 0;
		
		for(int i=0;i<listOutfits.length;i++){
			
			Outfit out = listOutfits[i];
			if(out.getMonth() == month){
				amount += out.getPrice();
			}
		}
		return amount;
	}
	
}

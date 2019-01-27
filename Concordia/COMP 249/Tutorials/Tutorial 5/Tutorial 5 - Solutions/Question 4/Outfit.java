import java.io.Serializable;


public class Outfit implements Serializable {
	
	private int month;
	private String outfitType;
	private int price;
	
	public Outfit(){
		this.month = 0;
		this.outfitType = "";
		this.price = 0;
	}
	
	public Outfit(int month,String type, int price) {
		this.month = month;
		this.outfitType = type;
		this.price = price;
	}
	
	public int getMonth(){
		return month;
	}
	
	public void setMonth(int value){
		 month = value;
	}
	
	public String getOutfitType(){
		return outfitType;
	}
	
	public void setOutfitType(String value){
		 outfitType = value;
	}	
	
	public int getPrice(){
		return price;
	}
	
	public void setPrice(int value){
		 price = value;
	}
}


class InventoryItem implements Comparable {
	private String name;
	private int uniqueItemID;
	public InventoryItem( String name, int uniqueItemID ) {
		this.name = name;
		this.uniqueItemID = uniqueItemID;
	}
	
	public void setName( String name ) {
		this.name = name;
	}
	
	public void setUniqueItemID( int uniqueItemID ) {
		this.uniqueItemID = uniqueItemID;
	}
	
	public String getName() { 
		return name; 
	}
	
	public int getUniqueItemID() { 
		return uniqueItemID; 
	}
	
	public String toString() {
		return "Name: " + name + ", Number: " + uniqueItemID;
	}
	
	public int compareTo( Object object) {
		if ( object instanceof InventoryItem ) {
			return (this.uniqueItemID - ((InventoryItem) object).uniqueItemID) ;
		}
		System.err.print(" ERROR: An inventory object should be passed");
		return 0;
	}
}

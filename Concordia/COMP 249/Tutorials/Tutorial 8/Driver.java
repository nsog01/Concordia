package ex3;

public class Driver {
	public static void main(String[] args){
		InventoryItem []inventoryItems = new InventoryItem[5];

		inventoryItems[0] = new InventoryItem("Book",18);
		inventoryItems[1] = new InventoryItem("Computer",77);
		inventoryItems[2] = new InventoryItem("NoteBook",4);
		inventoryItems[3] = new InventoryItem("Calculator",12);
		inventoryItems[4] = new InventoryItem("Pen",9);

		sort( inventoryItems ); // method sould be defined below

		for ( int i = 0; i < inventoryItems.length; i++ )
			System.out.println( inventoryItems[i] );
	}

	// write a method to sort comparable objets

	}
}

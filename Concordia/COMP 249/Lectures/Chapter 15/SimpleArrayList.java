// SimpleArrayList.java
//
// One way to build a list is to use an array. If you do this, however, the array must
// be able to grwo as the number of lements increarses. We sometimes call this a
// dynamic array. In the code below we provide a simple implementation of a dynamic
// array that works more or less like the ArrayList in java.


// The SimpleArrayList class. Note that it implements the SimpleList interface.
public class SimpleArrayList implements SimpleList{

	Object[] baseArray;
	int lastOccupiedIndex;


	// declare default array of 100 object references
	public SimpleArrayList(){
		baseArray = new Object[100];
	}

	// user defined initial size
	public SimpleArrayList(int size){
		baseArray = new Object[size];
	}

	// actual elements in array
	public int getSize(){
		return lastOccupiedIndex;
	}

	// size of the underlying array
	public int getCapacity(){
		return baseArray.length;
	}

	// get object at specific array index
	public Object getObjectAt(int index){
		if(index > lastOccupiedIndex)
			throw (new SimpleListIndexOutOfBoundsException() );

		return baseArray[index];
	}

	// set object at specific array index
	public void setObjectAt(int index, Object thing){

		if (index > lastOccupiedIndex)
			throw (new SimpleListIndexOutOfBoundsException() );

		baseArray[index] = thing;
	}

	// add a new node at specified position. The reamining nodes must all be shifted
	// to the right. Re-sizing of the underlying array may also be necessary
	public void insertObjectAt(int index, Object thing){

		if(index > lastOccupiedIndex)
			throw (new SimpleListIndexOutOfBoundsException() );

		if(lastOccupiedIndex == (baseArray.length - 1) )
			re_size();

		for(int i = lastOccupiedIndex; i >= index; i--){
			baseArray[i + 1] = baseArray[i];
		}
		baseArray[index] = thing;

		lastOccupiedIndex += 1;
	}


	// delete a new node at specified position. The reamining nodes must all be shifted
	// to the left.
	public void deleteObjectAt(int index){

		if(index > lastOccupiedIndex)
			throw (new SimpleListIndexOutOfBoundsException() );

		for(int i = index + 1; i < lastOccupiedIndex; i++){
			baseArray[i - 1] = baseArray[i];
		}
		lastOccupiedIndex -= 1;
	}


	// add a node to the end of the array. re-sizing may be necessary
	public void append(Object thing){

		if(lastOccupiedIndex == (baseArray.length - 1) )
			re_size();

		baseArray[lastOccupiedIndex++] = thing;

	}


	// re-size the array. Create a new array that is twice as big as the
	// current one and copy its contents from the current array
	protected void re_size( ){
		Object[] biggerArray = new Object[baseArray.length * 2];

		for(int i = 0; i < baseArray.length; i++)
			biggerArray[i] = baseArray[i];

		baseArray = biggerArray;

	}


	// test program
	public static void main(String[] args) {

		Integer myInt;
		SimpleArrayList myList = new SimpleArrayList(2);

		System.out.println("Appending 20 and 23 to a list of size 2...");
		myList.append(new Integer(20));
		myList.append(new Integer(23));

		System.out.println("Show the list");
		for(int i = 0; i < myList.getSize(); i++){
			myInt = (Integer)myList.getObjectAt(i);
			System.out.println(myInt.intValue());
		}
		System.out.println("Size = " + myList.getSize());

		System.out.println("\nAppending 14 and 36 to the same list...");
		myList.append(new Integer(14));
		myList.append(new Integer(36));

		System.out.println("Show the list");
		for(int i = 0; i < myList.getSize(); i++){
			myInt = (Integer)myList.getObjectAt(i);
			System.out.println(myInt.intValue());
		}
		System.out.println("Size = " + myList.getSize());

		System.out.println("\nSetting the value at position 3 to 99 and deleting object at position 1");
		myList.setObjectAt(3, (new Integer(99)));
		myList.deleteObjectAt(1);
		System.out.println("Show the list");
		for(int i = 0; i < myList.getSize(); i++){
			myInt = (Integer)myList.getObjectAt(i);
			System.out.println(myInt.intValue());
		}
		System.out.println("Size = " + myList.getSize());

		System.out.println("\nInserting the numbers 88, 87, 86, 85, 84 at position 1...");
		myList.insertObjectAt(1, (new Integer(88)));
		myList.insertObjectAt(1, (new Integer(87)));
		myList.insertObjectAt(1, (new Integer(86)));
		myList.insertObjectAt(1, (new Integer(85)));
		myList.insertObjectAt(1, (new Integer(84)));
		System.out.println("Show the list");
		for(int i = 0; i < myList.getSize(); i++){
			myInt = (Integer)myList.getObjectAt(i);
			System.out.println(myInt.intValue());
		}
		System.out.println("Size = " + myList.getSize());



		System.out.println("\nCreating a new list of size 2 and appending four animals...");
		SimpleArrayList myList2 = new SimpleArrayList(2);
		myList2.append("dog");
		myList2.append("cat");
		myList2.append("bird");
		myList2.append("racoon");
		System.out.println("Show the list");
		for(int i = 0; i < myList2.getSize(); i++){
			System.out.println( (String)myList2.getObjectAt(i) );
		}
		System.out.println("Size = " + myList2.getSize());


		//	myList.setObjectAt(16, (new Integer(99)));

	}
}

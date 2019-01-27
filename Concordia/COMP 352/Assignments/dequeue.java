public class dequeue
{
	public static int head;
	public static int tail;
	char flag = 'd';
	element[] array;
	
	
	// Constructor
	public dequeue()
	{
		head = 10/2;
		tail = head;
		array= new element[10]; //default size of 10
	}
	
	public dequeue(int size)
	{
		
		head = size/2;
		tail = head;
		array = new element[size];
	}
	
	
	public void addFirst(element toAdd)
	{ //each time when you're adding an element, check whether array is 90% full
		if(size() + 1 > 0.9*array.length)
		{
			expand();
			addFirst(toAdd);
		}else
		{
			if(head == 0)
			{
				array[array.length-1] = toAdd;
				head = array.length-1;
			}else
			{
				array[head-1] = toAdd;
				head = head - 1;
			}
		}
	}
	
	public void addLast(element toAdd)
	{
		if(size() + 1 > 0.9*array.length)
		{
			expand();
			addLast(toAdd);
		}else
		{
			if(tail == array.length-1)
			{
				array[array.length-1] = toAdd;
				tail = 0;
			}else
			{
				array[tail] = toAdd;
				tail = tail + 1;
			}
		}
	}
	
	public int size() //nb of elements in the array
	{
		return ((array.length - head) + tail) % array.length;
	}
	
	
	public element removeFirst() //remove first element in the array
	{
		if(isEmpty())
		{
			System.out.println("Cannot remove. The DQ is empty.");
			return null;
		}else
		{
			element temp = peekFirst();
			array[head] = null;
			if(head == array.length-1){
				head = 0;
			}else
			{
				head = head + 1;
			}
			return temp;
		}
	}
	
	public element removeLast() //removing last element in the array
	{
		if(isEmpty())
		{
			System.out.println("Cannot remove. The DQ is empty.");
			return null;
		}else
		{
			element temp = peekLast();
			if(tail == 0)
			{
				array[array.length-1] = null;
				tail = array.length-1;
			}else
			{
				array[tail-1] = null;
				tail = tail - 1;
			}
			return temp;
		}
	}
	

	public boolean isEmpty() //if array is empty
	{
		return (head == tail);
	}
	

	public element peekFirst() //returns and doesnt remove element at head of array
	{
		return array[head];
	}
	
	public element peekLast()//returns and doesnt remove element at tail of array
	{
		if(tail == 0){
			return array[array.length - 1];	
		}else
		{
			return array[tail - 1];
		}
	}
	
	public void truncate() //to save space
	{
		element[] oldArray = array;
		element[] newArray = new element[size()+1];
		
		// Filling up the new array starting at index 0
		for(int i = 0; i < size(); i++)
		{
			newArray[i] = oldArray[(head+i) % oldArray.length];
		}
		
		tail = size();
		head = 0;
		array = newArray;
	}
	
	public void setExpansionRule(char flag)
	{
		this.flag = flag;
	}
	

	public void expand() //expansion of the array
	{
		element[] oldArray = array;
		element[] newArray;
		
		if(flag == 'c')
		{
			newArray = new element[oldArray.length + 10];
		}else
		{
			newArray = new element[oldArray.length * 2];
		}
		
	
		for(int i = 0; i < size(); i++)
		{
			newArray[i] = oldArray[(head+i) % oldArray.length];
		}
	
		tail = size();
		head = 0;
		array = newArray;
	}
	
	public String toString()
	{
		String toReturn = "";
		
		for(int i = 0; i < array.length; i++)
		{
			if(array[i] == null)
				toReturn += "null ";
			else			
				toReturn += array[i].value + " ";
		}
		
		return toReturn;
		
	}
}

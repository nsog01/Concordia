package assignment2;


/**
 * Class BlockStack
 * Implements character block stack and operations upon it.
 *
 * $Revision: 1.4 $
 * $Last Revision Date: 2015/02/01 $
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca;
 * Inspired by an earlier code by Prof. D. Probst

 */
class BlockStack
{
	/**
	 * # of letters in the English alphabet + 2
	 */
	public static final int MAX_SIZE = 28;

	/**
	 * Default stack size
	 */
	public static final int DEFAULT_SIZE = 6;

	/**
	 * Current size of the stack
	 */
	private int iSize = DEFAULT_SIZE;

	/**
	 * Current top of the stack
	 */
	private int iTop  = 3;

	/**
	 * stack[0:5] with four defined values
	 */
	private char acStack[] = new char[] {'a', 'b', 'c', 'd', '$', '$'};

	/**
	 * TASK 1
	 * Counter for the every time the stack is accessed whether it be
	 * via the push(), pop(), pick(), or getAt() methods, the counter will be increased by 1
	 */
	public int stack_Access_Counter = 0;
	/**
	 * Default constructor
	 */
	public BlockStack()
	{
	}

	/**
	 * Supplied size
	 */
	public BlockStack(final int piSize)
	{
		if(piSize != DEFAULT_SIZE)
		{
			this.acStack = new char[piSize];

			// Fill in with letters of the alphabet and keep
			// 2 free blocks
			for(int i = 0; i < piSize - 2; i++)
				this.acStack[i] = (char)('a' + i);

			this.acStack[piSize - 2] = this.acStack[piSize - 1] = '$';

			this.iTop = piSize - 3;
			this.setSize(piSize);
		}
	}

	/**
	 * Picks a value from the top without modifying the stack
	 * @return top element of the stack, char
	 */
	public char pick()
	{
		stack_Access_Counter++;//TASK 1: increasing stack access counter
		return this.acStack[this.iTop];
	}

	/**
	 * Returns arbitrary value from the stack array
	 * @return the element, char
	 */
	public char getAt(final int piPosition)
	{
		stack_Access_Counter++;//TASK 1: increasing stack access counter
		return this.acStack[piPosition];
	}

	/**
	 * Standard push operation
	 */
	public void push(char pcBlock)
	{
		stack_Access_Counter++;//TASK 1: increasing stack access counter
		try {
			if(this.isEmpty() ) 
				pcBlock =  'a';	
			if(this.iTop == iSize -1)
				throw new OverPushException();
			else
				this.acStack[++this.iTop] = pcBlock;
		} catch(OverPushException e) {
			System.err.print("OverPushException: Attempted to push on a stack that has reached its maximum size.");
		}
		
	}

	/**
	 * Standard pop operation
	 * @return ex-top element of the stack, char
	 */
	public char pop()
	{
		char cBlock = ' ';
		stack_Access_Counter++;//TASK 1: increasing stack access counter
		
		try {
			cBlock = this.acStack[this.iTop];
			if(this.isEmpty())
				throw new OverPopException();
			else {
				this.acStack[this.iTop--] = '$'; // Leave prev. value undefined
			}
		} catch(OverPopException e) {
			System.err.print("OverPopException: Cannot pop on an empty stack.");
		}

		return cBlock;
	}

	public int getSize() {
		return iSize;
	}

	public void setSize(int iSize) {
		this.iSize = iSize;
	}
	
	public int getTop(){
		return iTop;
	}
	
	public void setTop(int x){
		this.iTop = x;
	}
	
	public char[] getacStack(){
		return acStack;
	}
	
	public void setacStack(char[] arr){
		this.acStack = arr;
	}
	
	public int getAccessCounter(){
		return stack_Access_Counter;
	}
	
	public boolean isEmpty(){
         return (this.iTop == -1);
    }
}

// EOF

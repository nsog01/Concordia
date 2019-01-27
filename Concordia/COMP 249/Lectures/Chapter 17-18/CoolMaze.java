// CoolMaze.java
//
// This is an adaptation of the maze program found in the text book. Its purpose
// is to allow students to visualize the recursive traversal of the maze. The 
// traversal algorithm itself is unchanged. However, additonal code has been
// added to draw the traversal pattern one square at a time during execution. As
// noted in class, animation is usually done with threads. This allows the user to 
// interact with the GUI (e.g., push buttons) while the animation is running. 
// To keep things simple, I have not written the code with threads. As a result, 
// any buttons pressed while the animation is running will be ignored.

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



// the main method
public class CoolMaze {
	   

	 //Create the GUI and show it (Sun style).  
	 private static void createAndShowGUI() {
	           
	   	//Make sure we have nice window decorations.
	    JFrame.setDefaultLookAndFeelDecorated(true);

	    //Create and set up the window.
	    JFrame frame = new JFrame("Maze Master");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    
	    Canvas drawingCanvas = new Canvas();
	    Controller controller = new Controller(drawingCanvas);
	    
	    
	    //	  Build and add the main panel
	    JPanel primary  = new JPanel();
	    primary.setLayout (new BoxLayout (primary, BoxLayout.X_AXIS));
	    
	   	primary.add(drawingCanvas);
	   	primary.add(controller);
	   
	   	frame.getContentPane().add(primary);

	    //Display the window.
	    frame.pack();
	    frame.setVisible(true);
	 }

	 
	 public static void main(String[] args) {
	 	//Schedule a job for the event-dispatching thread:
	 	javax.swing.SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	           createAndShowGUI();
	      }
	 	});
	 }
	      
}

// the basic control panel
class Controller extends JPanel{
	
	Canvas drawingCanvas ; // a reference to the drawing panel
	ButtonGroup paceButtons; // the button group
	
	public Controller(Canvas drawingCanvas){
		
		this.drawingCanvas = drawingCanvas;
		setPreferredSize( new Dimension(100,200));
		setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
		
		//	create the radio buttons for the Shape type
		JRadioButton fastPace = new JRadioButton("Fast", true);
		fastPace.setActionCommand("Fast");
		JRadioButton slowPace = new JRadioButton("Slow");
		slowPace.setActionCommand("Slow");
			
		paceButtons = new ButtonGroup();
		paceButtons.add(fastPace);
		paceButtons.add(slowPace);
		
		JPanel pacePanel = new JPanel();
		pacePanel.setBorder(BorderFactory.createTitledBorder("Speed"));
		pacePanel.setLayout (new BoxLayout (pacePanel, BoxLayout.Y_AXIS));
		pacePanel.add(fastPace);
		pacePanel.add(slowPace);
		
		// add the interactive buttons
		JButton exploreButton = new JButton("Explore");
		exploreButton.addActionListener(new exploreListener() );
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new resetListener() );
		
		JPanel primaryPanel = new JPanel();
		primaryPanel.add(pacePanel);
		primaryPanel.add(exploreButton);
		primaryPanel.add(resetButton);
		primaryPanel.add(Box.createVerticalGlue());
		
		add(primaryPanel);
		
		
	}
	
	
	// start the recrusive traversal of the maze
	private class exploreListener implements ActionListener{
		
		public void actionPerformed(ActionEvent event){
			
			drawingCanvas.setSpeed(paceButtons.getSelection().getActionCommand());
			Graphics page = drawingCanvas.getGraphics();
			drawingCanvas.traverse(page, 0,0);
			drawingCanvas.repaint();
		}
	}
	
	
	// reset the maze back to its original state
	private class resetListener implements ActionListener{
		
		public void actionPerformed(ActionEvent event){
			drawingCanvas.reset();
			drawingCanvas.repaint();
		}
	}
	
}




// the maze drawing class. 
class Canvas extends JPanel {
	
	private static final int COLUMNS = 13;
	private static final int ROWS = 8; 
	private static final int START_X = 10;
	private static final int START_Y = 10;
	private static final int SIZE = 20;
	
	private static final int CLOSED = 0;
	private static final int OPEN = 1;
	private static final int CONSIDER = 3;
	private static final int TRIED = 5;
	private static final int PATH = 6;
	
	private static final int SLOW = 0;
	private static final int FAST = 1;
	
	private static final int sleepTime = 100;
	int speed = FAST;
	
	// the basic layout of the maze
	int[][] mazeLayout =  
			{{1,1,1,0,1,1,0,0,0,1,1,1,1},
            {1,0,1,1,1,0,1,1,1,1,0,0,1},
            {0,0,0,0,1,0,1,0,1,0,1,0,0},
            {1,1,1,0,1,1,1,0,1,0,1,1,1},
            {1,0,1,0,0,0,0,1,1,1,0,0,1},
            {1,0,1,1,1,1,1,1,0,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,1} }; 
      
	// this is a second maze that you can try
	/* int[][] mazeLayout =  
				{{1,1,1,0,1,0,1,1,1,1,1,1,1},
				{0,0,1,1,1,0,0,1,0,1,0,0,1},
				{0,1,1,0,0,0,1,1,1,0,1,0,1},
				{0,1,1,0,0,0,1,1,1,0,1,0,0},
				{1,1,1,0,0,1,1,0,0,0,0,0,1},
				{1,1,0,1,1,0,1,1,0,0,0,1,1},
				{1,0,1,1,1,0,0,1,1,1,0,0,0},
				{1,1,1,1,0,0,1,1,1,1,1,1,1} }; */
	
	
	// the "coloured" maze that show traversal patterns
	int[][] maze = new int[ROWS][COLUMNS];
	
	
	
	public Canvas() {
		setPreferredSize( new Dimension(300,200));
		setBackground(Color.white);
		reset();
	}
	
	
	// set the speed. Fast = immediate execution, SLOW = demo mode
	void setSpeed(String speedString){
		if(speedString == "Fast")
			this.speed = FAST;
		else
			this.speed = SLOW;
	}
	
	
	// set the maze back to its original form
	void reset(){
		for(int i = 0; i < ROWS; i ++){
			for(int j = 0; j < COLUMNS; j++){
				maze[i][j] = mazeLayout[i][j];
			}
		}
	}
	
	
	// The recursive traversal of the maze. This method looks a little more 
	// complicated than it actually is. This is simply due to the insertion of
	// the sleep and paint methods for the demo mode. Otherwise, it's the same code 
	// as found in the simple maze program.
	public boolean traverse (Graphics page, int row, int column){
	      boolean done = false;
			
	      if (valid (page, row, column)){
	         maze[row][column] = TRIED;  // this cell has been tried
	         
	         if(speed == SLOW){
	         	try {
	         		// ask currently executing Thread to sleep
	         		Thread.sleep(sleepTime); 
	         	}
	         	catch(InterruptedException e)   {      
	         		System.out.println("Sleep interrupted:"+e);      
	         	}
	         	drawSquareNow(page, row, column);
	         }
	         
	         if (row == maze.length-1 && column == maze[0].length-1)
	            done = true;  // the maze is solved
	         else{
	            done = traverse (page, row+1, column);     // down
	            if (!done)
	               done = traverse (page, row, column+1);  // right
	            if (!done)
	               done = traverse (page, row-1, column);  // up
	            if (!done)
	               done = traverse (page, row, column-1);  // left
	         }

	         if (done)  {// this location is part of the final path
	            maze[row][column] = PATH;
	            
	            if(speed == SLOW){
	            	try {
	            		// ask currently executing Thread to sleep
	            		Thread.sleep(sleepTime); 
	            	}
	            	catch(InterruptedException e)   {      
	            		System.out.println("Sleep interrupted:"+e);      
	            	}
	            	drawSquareNow(page, row, column);
	            }
	         }
	      }
	      
	      
	      return done;
	   }
	   
	
	// Determines if a specific location is valid. The cell must be inside the grid,
	// not blocked (i.e., black), and not previously visisted
	private boolean valid (Graphics page, int row, int column){
	      boolean result = false;
	 
	      // check if cell is in the bounds of the matrix
	      if (row >= 0 && row < maze.length && column >= 0 && column < maze[row].length){

	      		if(speed == SLOW){
	      			int temp = maze[row][column];
		      
	      			// draw the red square that show the next position under consideration
	      			maze[row][column] = CONSIDER;
	      			drawSquareNow(page, row, column);
	     	  
	      			try {
	      				// ask currently executing Thread to sleep
	      				Thread.sleep(sleepTime); 
	      			}
	      			catch(InterruptedException e)   {      
	      				System.err.println("Sleep interrupted:" + e);      
	      			}
	      			maze[row][column] = temp;
	      	
	      			drawSquareNow(page, row, column);
	      		}
	      		
	      		//  check if cell is not blocked and not previously tried
	      		if (maze[row][column] == 1)
	      			result = true;
	      }
	         
	      return result;
	}
	
	
	// paint a single coloured square to the display. This is used by paintCOmponent to draw the
	// original maze
	void drawSquare (Graphics page, int row, int column){
		int colorCode = maze[row][column];
		Color squareColour = getColor(colorCode);
		
		page.setColor(squareColour);
		page.fillRect(START_X + (column * SIZE), START_Y + (row * SIZE), SIZE, SIZE);
	}
	
	
	// use the drawImmediately method to paint the square to the screen. We must use this
	// technique while the algorithm is running since normal paintComponent requests are 
	// placed into the event Q and would not
	// get executed until after the traverse method completes.
	void drawSquareNow(Graphics page, int row, int column){
		int colorCode = maze[row][column];
		Color squareColour = getColor(colorCode);
			
		page.setColor(squareColour);
		page.fillRect(START_X + (column * SIZE), START_Y + (row * SIZE), SIZE, SIZE);
		paintImmediately(START_X + (column * SIZE), START_Y + (row * SIZE), SIZE, SIZE);	
	}
	
	
	
	// returns a Color object based upon the value found in a given square of the maze
	Color getColor(int colorCode){
		
		Color squareColour = null;
		
		switch(colorCode){
			case OPEN:
				squareColour = Color.white;
				break;
			case CLOSED:
				squareColour = Color.black;
				break;
			case TRIED:
				squareColour = Color.yellow;
				break;
			case CONSIDER:
				squareColour = Color.red;
				break;
			case PATH:
				squareColour = Color.green;
		}
		
		return squareColour;
		
	}
	
	
	// draw a black border around every square to form a grid. It would probably be faster
	// to draw this with lines.
	void drawGrid(Graphics page, int row, int column){
		page.setColor(Color.black);
		page.drawRect(START_X + (column * SIZE), START_Y + (row * SIZE), SIZE, SIZE);
	}
	
	
	
	public void paintComponent(Graphics page){
		super.paintComponent(page);
		
		// draw the coloured blocks
		for(int i = 0; i < ROWS; i ++){
			for(int j = 0; j < COLUMNS; j++){
				drawSquare(page, i, j);
			}
		}
		
		// draw the grid lines
		for(int i = 0; i < ROWS; i ++){
			for(int j = 0; j < COLUMNS; j++){
				drawGrid(page, i, j);
			}
		}
	}
	
}

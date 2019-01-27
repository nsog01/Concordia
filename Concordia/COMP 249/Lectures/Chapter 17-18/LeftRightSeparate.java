//********************************************************************
// LeftRight.java       
//
// Demonstrates the use of one listener for multiple buttons. Specifically,
// the listener can use the event object to determine the source of the event.
// The appropriate action can then be taken. Contrast this with 
// LeftRightCombined.java
//
//********************************************************************

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class LeftRightSeparate{
	//-----------------------------------------------------------------
	//  Creates the main program frame.
	//-----------------------------------------------------------------
	public static void main (String[] args){
		JFrame frame = new JFrame ("Left Right");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(new LeftRightSeparatePanel());

		frame.pack();
		frame.setVisible(true);
	}
}



class LeftRightSeparatePanel extends JPanel {
	private JButton left, right;
	private JLabel label;
	private JPanel buttonPanel;

	//-----------------------------------------------------------------
	//  Constructor: Sets up the GUI.
	//-----------------------------------------------------------------
	public LeftRightSeparatePanel (){
		left = new JButton ("Left");
		right = new JButton ("Right");

		// create listeners for both buttons
	    left.addActionListener (new LeftButtonListener() );
	    right.addActionListener (new RightButtonListener() );

		label = new JLabel ("Push a button");

		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize (new Dimension(200, 40));
		buttonPanel.setBackground (Color.blue);
		buttonPanel.add (left);
		buttonPanel.add (right);

		setPreferredSize (new Dimension(200, 80));
		setBackground (Color.cyan);
		add (label);
		add (buttonPanel);
	}



	
//  A listener for the left button.
	   private class LeftButtonListener implements ActionListener{
	      
	      public void actionPerformed (ActionEvent event){
	      	label.setText("Left");
	      }
	   }
	   
	   // A listener for the left button.
	   private class RightButtonListener implements ActionListener{
	      
	      public void actionPerformed (ActionEvent event){
	      	label.setText("Right");
	      }
	   }
	
	
	
}

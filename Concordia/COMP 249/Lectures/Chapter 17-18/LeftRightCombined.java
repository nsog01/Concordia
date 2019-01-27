//********************************************************************
//  LeftRightCombined.java       
//
//  Simple example showing separate listeners for two different buttons.
//  Contrast this with LeftRightSeparate.java
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



public class LeftRightCombined{
   //-----------------------------------------------------------------
   //  Creates the main program frame.
   //-----------------------------------------------------------------
   public static void main (String[] args){
      JFrame frame = new JFrame ("Left Right");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      frame.add(new LeftRightCombinedPanel());

      frame.pack();
      frame.setVisible(true);
   }
}



class LeftRightCombinedPanel extends JPanel {
   private JButton left, right;
   private JLabel label;
   private JPanel buttonPanel;

   //-----------------------------------------------------------------
   //  Constructor: Sets up the GUI.
   //-----------------------------------------------------------------
   public LeftRightCombinedPanel (){
      left = new JButton ("Left");
      right = new JButton ("Right");

      // create a listener and then register is with both buttons
	  ButtonListener listener = new ButtonListener();
	  left.addActionListener (listener);
	  right.addActionListener (listener);
      
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

   
	//*****************************************************************
	//  Represents a listener for both buttons.
	//*****************************************************************
	private class ButtonListener implements ActionListener{
		//--------------------------------------------------------------
		//  Determines which button was pressed and sets the label
		//  text accordingly. Remember, the equality test below determines
		//  whether the object on the left side of the equal sign is actually
		//  the same object that appears on the right side. 
		//--------------------------------------------------------------
		public void actionPerformed (ActionEvent event){
			if (event.getSource() == left)
				label.setText("Left");
			else
				label.setText("Right");
		}
	}
   
}

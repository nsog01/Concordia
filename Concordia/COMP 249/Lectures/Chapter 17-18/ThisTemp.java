//********************************************************************
//	Fahrenheit.java
//
// Yet another example of implementing a GUI listener. In this case, the panel
// object itself implements the listener, rather than a separate listener class.
// To do this, the panel class adds the "implements" keyword and then includes an
// actionPerformed method. This technique can be useful if the panel class isn't
// very big but should be avoided otherwise since it can be confusing.

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ThisTemp{
   //  Creates and displays the temperature converter GUI.
   public static void main (String[] args){
	  JFrame frame = new JFrame ("Fahrenheit");
	  frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

	  ThisTempPanel panel = new ThisTempPanel();

	  frame.getContentPane().add(panel);
	  frame.pack();
	  frame.setVisible(true);
   }
}


// the class implements the ActionListener interface
class ThisTempPanel extends JPanel implements ActionListener{

   private JLabel inputLabel, outputLabel, resultLabel;
   private JTextField fahrenheit;

   //  Constructor: Sets up the main GUI components.
   public ThisTempPanel(){
	  inputLabel = new JLabel ("Enter Fahrenheit temperature:");
	  outputLabel = new JLabel ("Temperature in Celsius: ");
	  resultLabel = new JLabel ("---");

	  fahrenheit = new JTextField (5);
	  fahrenheit.addActionListener (this);

	  add (inputLabel);
	  add (fahrenheit);
	  add (outputLabel);
	  add (resultLabel);

	  setPreferredSize (new Dimension(300, 75));
	  setBackground (Color.yellow);
   }

   // add the actionPerformed method to the panel class
   public void actionPerformed (ActionEvent event){
		int fahrenheitTemp, celsiusTemp;
		String text = fahrenheit.getText();
		fahrenheitTemp = Integer.parseInt (text);
		celsiusTemp = (fahrenheitTemp-32) * 5/9;
		resultLabel.setText (Integer.toString (celsiusTemp));
   }
}

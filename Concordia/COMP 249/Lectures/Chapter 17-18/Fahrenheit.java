//********************************************************************
//  Fahrenheit.java       Author: Lewis/Loftus
//
//  Demonstrates the use of text fields. Similar to the PushCounter example
//  except that a textfield is being used.
//********************************************************************

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Fahrenheit{
   //  Creates and displays the temperature converter GUI.
   public static void main (String[] args){
      JFrame frame = new JFrame ("Fahrenheit");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      FahrenheitPanel panel = new FahrenheitPanel();

      frame.getContentPane().add(panel);
      frame.pack();
      frame.setVisible(true);
   }
}



class FahrenheitPanel extends JPanel{
   private JLabel inputLabel, outputLabel, resultLabel;
   private JTextField fahrenheit;

   //  Constructor: Sets up the main GUI components.
   public FahrenheitPanel(){
	  inputLabel = new JLabel ("Enter Fahrenheit temperature:");
	  outputLabel = new JLabel ("Temperature in Celsius: ");
	  resultLabel = new JLabel ("---");

	  fahrenheit = new JTextField (5);
	  fahrenheit.addActionListener (new TempListener());

	  add (inputLabel);
	  add (fahrenheit);
	  add (outputLabel);
	  add (resultLabel);

	  setPreferredSize (new Dimension(300, 75));
	  setBackground (Color.yellow);
   }

   //*****************************************************************
   //  Represents an action listener for the temperature input field.
   //*****************************************************************
   private class TempListener implements ActionListener{
	  //  Performs the conversion when the enter key is pressed in the text field.
	  public void actionPerformed (ActionEvent event){
		 int fahrenheitTemp, celsiusTemp;

		 String text = fahrenheit.getText();

		 fahrenheitTemp = Integer.parseInt (text);
		 celsiusTemp = (fahrenheitTemp-32) * 5/9;

		 resultLabel.setText (Integer.toString (celsiusTemp));
	  }
   }
}

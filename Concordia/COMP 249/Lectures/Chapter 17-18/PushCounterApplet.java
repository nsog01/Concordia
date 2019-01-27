//********************************************************************
//  PushCounterGood.java       Author: L. Kosseim June 2005
//
//  Demonstrates the use of push buttons. 
//  This class has a listener registered for the button.  
//  If the user clicks on the button, the counter is incremented.
//  Notice how the listener is implemented as a inner class allowing it 
//  direct access to the internal data of the applet.
// *****************************************************************

import javax.swing.*; 
import java.awt.*;  
import java.awt.event.*; // needed for listeners

public class PushCounterApplet extends JApplet {
   private int nbPushes;
   private JLabel myLabel;   
   private JButton myButton;
   private JPanel myPanel;

   public void init() {
 	  nbPushes = 0;
 	  myButton = new JButton("Push Me!");
 	  
 	  // register a listener to the button
      myButton.addActionListener(new ButtonListener()); 
      
      myLabel = new JLabel("Pushes: " + Integer.toString(nbPushes));
 
      myPanel = new JPanel();
      add(myPanel);
      	  
      myPanel.add(myButton);
      myPanel.add(myLabel);
   }
   // here, the listener is an inner class
   private class ButtonListener implements ActionListener {
      public void actionPerformed (ActionEvent event) {
         nbPushes++;
         myLabel.setText("Pushes: " + Integer.toString(nbPushes));
      }
   }
}

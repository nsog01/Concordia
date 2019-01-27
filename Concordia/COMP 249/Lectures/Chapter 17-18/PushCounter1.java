//********************************************************************
//  PushCounter1       Author: L. Kosseim 
//
//  Demonstrates the use of push buttons. 
//  This class has no listener, so it only displays the widget.  
//  If the user clicks on the button, nothing will happen.
// *****************************************************************

import javax.swing.*; 
import java.awt.*;  

public class PushCounter1 {
    public static void main(String[] arg) {
    
        JFrame theWindow = new JFrame("Push Counter that does not seem to count much...");
        theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        int nbPushes = 0;
        JButton myButton = new JButton("Push Me!");
        JLabel myLabel = new JLabel("Pushes: " + Integer.toString(nbPushes));
      
        JPanel myPanel = new JPanel();
       
        theWindow.add(myPanel);
        myPanel.add(myLabel);
        myPanel.add(myButton);
        theWindow.pack();
        theWindow.setVisible(true);
    }
}


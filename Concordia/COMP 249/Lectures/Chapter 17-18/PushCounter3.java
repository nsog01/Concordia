//********************************************************************
//  PushCounterWithListener.java       Author: L. Kosseim 
//
//  Demonstrates the use of push buttons. 
//  This class has a listener registered for the button.  
//  If the user clicks on the button, the counter is incremented.
//  Notice how the listener is implemented as a inner class allowing it 
//  direct access to the internal data of the outer class.
// *****************************************************************

import javax.swing.*; 
import java.awt.*;  
import java.awt.event.*; // needed for listeners

public class PushCounter3 {
    public static void main(String[] arg) {
        PushGUI myGui = new PushGUI();
    }
}

 class PushGUI extends JFrame{
    private JFrame theWindow;
    private int nbPushes;
    private JButton myButton;
    private JLabel myLabel;
    private JPanel myPanel;
 
    PushGUI(){
        theWindow = new JFrame("Push Counter that counts!");
        theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        nbPushes = 0;
        myButton = new JButton("Push Me!");
        myLabel = new JLabel("Pushes: " + Integer.toString(nbPushes));

        // let's register the event listener     
        myButton.addActionListener(new ButtonListener()); 

        myPanel = new JPanel();

        theWindow.add(myPanel);
        myPanel.add(myLabel);
        myPanel.add(myButton);
        
        theWindow.pack();
        theWindow.setVisible(true);
    }

    
   // here, the listener is an inner class
   private class ButtonListener implements ActionListener {
      public void actionPerformed (ActionEvent event) {
         nbPushes++;
         myLabel.setText("Pushes: " + Integer.toString(nbPushes));
      }
}
}

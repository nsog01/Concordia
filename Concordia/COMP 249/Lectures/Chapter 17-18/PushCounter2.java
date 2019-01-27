//********************************************************************
//  PushCounter2.java       Author: L. Kosseim 
//
//  Demonstrates the use of push buttons. 
//  This class still has no listener, but the frame is a separate class
// (will be useful when we add the listener)
// *****************************************************************

import javax.swing.*; 
import java.awt.*;  

public class PushCounter2 {
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
        theWindow = new JFrame("Push Counter that still does not count!");
        theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        nbPushes = 0;
        myButton = new JButton("Push Me!");
        myLabel = new JLabel("Pushes: " + Integer.toString(nbPushes));

        myPanel = new JPanel();

        theWindow.add(myPanel);
        myPanel.add(myLabel);
        myPanel.add(myButton);
        
        theWindow.pack();
        theWindow.setVisible(true);
    }
}

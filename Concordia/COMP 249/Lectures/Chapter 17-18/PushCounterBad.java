//********************************************************************
//  PushCounterBad       Author: L. Kosseim
//
//  Demonstrates the use of push buttons.
//  This class shows a bad example of how to implement a listener
//  It is cleaner to have an inner class
// *****************************************************************

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PushCounterBad {
    public static void main(String[] arg) {
        PushGUI myGui = new PushGUI();
    }
}

// the frame is itself the listener
 class PushGUI extends JFrame implements ActionListener{
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

        myButton.addActionListener(this);

        myLabel = new JLabel("Pushes: " + Integer.toString(nbPushes));

        myPanel = new JPanel();

        theWindow.add(myPanel);
        myPanel.add(myLabel);
        myPanel.add(myButton);

        theWindow.pack();
        theWindow.setVisible(true);
    }

    public void actionPerformed (ActionEvent event) {
        nbPushes++;
        myLabel.setText("Pushes: " + Integer.toString(nbPushes));
    }
}


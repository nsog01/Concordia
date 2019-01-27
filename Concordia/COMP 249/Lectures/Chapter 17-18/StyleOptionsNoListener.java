//********************************************************************
//  StyleOptionsNoListener.java       Author: Lewis/Loftus
//                          Modified by: L. Kosseim June 2005
//
//  Demonstrates the use of check boxes. 
// This class has no listener, so it only displays the widgets, but
// they are not active.
//********************************************************************

import java.awt.*;

import javax.swing.*;


public class StyleOptionsNoListener{
   //-----------------------------------------------------------------
   //  Creates and presents the program frame.
   //-----------------------------------------------------------------
   public static void main (String[] args){
      JFrame frame = new JFrame ("Style Options");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      StyleOptionsPanel panel = new StyleOptionsPanel();
      frame.add (panel);

      frame.setSize(300,150);
      frame.setVisible(true);
   }
}


class StyleOptionsPanel extends JPanel{
   private JLabel saying;
   private JCheckBox bold, italic;

   //-----------------------------------------------------------------
   //  Sets up a panel with a label and some check boxes that
   //  control the style of the label's font.
   //-----------------------------------------------------------------
   public StyleOptionsPanel(){
      saying = new JLabel ("Say it with style!");
      saying.setFont (new Font ("Helvetica", Font.PLAIN, 36));
      bold = new JCheckBox ("Bold");
      italic = new JCheckBox ("Italic");
      
      add(saying);
      add(bold);
      add(italic);

      setBackground(Color.cyan);
      setSize (300, 100);
   }
}

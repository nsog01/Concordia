//********************************************************************
//  LayoutDemo.java       Authors: Lewis/Loftus
//
//  Demonstrates the use of flow, border, grid, and box layouts. Whn you re-size the 
//  screen, you how the layout changes, often for the worse. This can be partly offset
//  by placing the buttons in panels - so that the panel backgrounds get re-sized,
//  but the buttons stay the same size.
//
//  Note as well that a tab pane has been used for the demo. A tab pane is very easy to use.
//  Basically, you just create a the tab pane and add panels to it.
//
//  The layouts are very easy to use. A couple of them take some extra arguments but they
//  are easy to figure out. The box layout, for example, uses a flag that indicates whether to 
//  lay it out horizontally or vertically.
//********************************************************************

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class LayoutDemo
{
   //-----------------------------------------------------------------
   //  Sets up a frame containing a tabbed pane. The panel on each
   //  tab demonstrates a different layout manager.
   //-----------------------------------------------------------------
   public static void main (String[] args)
   {
      JFrame frame = new JFrame ("Layout Manager Demo");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      // the tab pane widget
      JTabbedPane tp = new JTabbedPane();
      tp.addTab ("Intro", new IntroPanel());
      tp.addTab ("Flow", new FlowPanel());
      tp.addTab ("Border", new BorderPanel());
      tp.addTab ("Grid", new GridPanel());
      tp.addTab ("Box", new BoxPanel());

      frame.add(tp);
      frame.pack();
      frame.setVisible(true);
   }
}



class IntroPanel extends JPanel{
   //-----------------------------------------------------------------
   //  Sets up this panel with two labels.
   //  The JPanel defaults to flow layout
   //-----------------------------------------------------------------
   public IntroPanel()
   {
      setBackground (Color.green);

      JLabel l1 = new JLabel ("Layout Manager Demonstration");
      JLabel l2 = new JLabel ("Choose a tab to see an example of " +
                              "a layout manager.");

      add (l1);
      add (l2);
   }
}





class FlowPanel extends JPanel{
   //-----------------------------------------------------------------
   //  Sets up this panel with some buttons to show how flow layout
   //  affects their position. Elements are added left to right, top to
   //  bottom. When there isn't enough room, the element "wrap" to the
   //  next row.
   //-----------------------------------------------------------------
   public FlowPanel ()
   {
      setLayout (new FlowLayout());

      setBackground (Color.green);

      JButton b1 = new JButton ("BUTTON 1");
      JButton b2 = new JButton ("BUTTON 2");
      JButton b3 = new JButton ("BUTTON 3");
      JButton b4 = new JButton ("BUTTON 4");
      JButton b5 = new JButton ("BUTTON 5");

      add (b1);
      add (b2);
      add (b3);
      add (b4);
      add (b5);
   }
}



class BorderPanel extends JPanel {
   //-----------------------------------------------------------------
   //  Sets up this panel with a button in each area of a border
   //  layout to show how it affects their position, shape, and size.
   //  This layout has five regions: east west, north, south, and center.
   //  When you re-size, the center region will grow as much as necessary.
   //-----------------------------------------------------------------
   public BorderPanel()
   {
      setLayout (new BorderLayout());

      setBackground (Color.green);

      JButton b1 = new JButton ("BUTTON 1");
      JButton b2 = new JButton ("BUTTON 2");
      JButton b3 = new JButton ("BUTTON 3");
      JButton b4 = new JButton ("BUTTON 4");
      JButton b5 = new JButton ("BUTTON 5");

      add (b1, BorderLayout.CENTER);
      add (b2, BorderLayout.NORTH);
      add (b3, BorderLayout.SOUTH);
      add (b4, BorderLayout.EAST);
      add (b5, BorderLayout.WEST);
   }
}



class GridPanel extends JPanel{
   //-----------------------------------------------------------------
   //  Sets up this panel with some buttons to show how grid
   //  layout affects their position, shape, and size. The elements
   //  get added to the grid left to right, top to bottom
   //-----------------------------------------------------------------
   public GridPanel()
   {
   	  // a grid with 2 rows, 3 columns
      setLayout (new GridLayout (2, 3));

      setBackground (Color.green);

      JButton b1 = new JButton ("BUTTON 1");
      JButton b2 = new JButton ("BUTTON 2");
      JButton b3 = new JButton ("BUTTON 3");
      JButton b4 = new JButton ("BUTTON 4");
      JButton b5 = new JButton ("BUTTON 5");

      add (b1);
      add (b2);
      add (b3);
      add (b4);
      add (b5);
   }
}



class BoxPanel extends JPanel {
   //-----------------------------------------------------------------
   //  Sets up this panel with some buttons to show how a vertical
   //  box layout (and invisible components) affects their position.
   //-----------------------------------------------------------------
   public BoxPanel()
   {
   	  // the this argument refers to the parent object - the JPanel.
   	  // Boxlayout.Y_AXIS creates a column, BoxLayout.X_axis creates a row
      setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));

      setBackground (Color.green);

      JButton b1 = new JButton ("BUTTON 1");
      JButton b2 = new JButton ("BUTTON 2");
      JButton b3 = new JButton ("BUTTON 3");
      JButton b4 = new JButton ("BUTTON 4");
      JButton b5 = new JButton ("BUTTON 5");

      add (b1);  
      add (Box.createRigidArea (new Dimension (0, 10))); // specific size for white space
      add (b2);
      add (Box.createVerticalGlue()); // flexible region
      add (b3);
      add (b4);
      add (Box.createRigidArea (new Dimension (0, 20)));
      add (b5);
   }
}


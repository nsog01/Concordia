//********************************************************************
//  QuoteOptions.java       
//
//  Demonstrates the use of radio buttons. In this example, an actionListener
//  is registered with the radio buttons so that an action is taken as soon 
//  as the button is pushed. Contrast this with radioComplex.java, where the
//  no action is taken until a button is later pushed.
//********************************************************************

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class QuoteOptions{
   //-----------------------------------------------------------------
   //  Creates and presents the program frame.
   //-----------------------------------------------------------------
   public static void main (String[] args){
      JFrame frame = new JFrame ("Quote Options");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      QuoteOptionsPanel panel = new QuoteOptionsPanel();
      frame.add (panel);

      frame.pack();
      frame.setVisible(true);
   }
}


class QuoteOptionsPanel extends JPanel{
   private JLabel quote;
   private ButtonGroup group;
   private JRadioButton comedy, philosophy, carpentry;
   private String comedyQuote, philosophyQuote, carpentryQuote;

   //-----------------------------------------------------------------
   //  Sets up a panel with a label and a set of radio buttons
   //  that control its text.
   //-----------------------------------------------------------------
   public QuoteOptionsPanel(){
      comedyQuote = "Take my wife, please.";
      philosophyQuote = "I think, therefore I am.";
      carpentryQuote = "Measure twice. Cut once.";

      quote = new JLabel (comedyQuote);
      quote.setFont (new Font ("Helvetica", Font.BOLD, 24));

      comedy = new JRadioButton ("Comedy", true);
      comedy.setBackground (Color.green);
      philosophy = new JRadioButton ("Philosophy");
      philosophy.setBackground (Color.green);
      carpentry = new JRadioButton ("Carpentry");
      carpentry.setBackground (Color.green);

      ButtonGroup group = new ButtonGroup();
      group.add (comedy);
      group.add (philosophy);
      group.add (carpentry);

      QuoteListener listener = new QuoteListener();
      comedy.addActionListener (listener);
      philosophy.addActionListener (listener);
      carpentry.addActionListener (listener);
      
      add (quote);
      add (comedy);
      add (philosophy);
      add (carpentry);

      setBackground (Color.green);
      setPreferredSize (new Dimension(300, 100));
   }

   // *****************************************************************
   // Represents the listener for all radio buttons
   //*****************************************************************
   private class QuoteListener implements ActionListener{
   	 //--------------------------------------------------------------
   	 //  Sets the text of the label depending on which radio
   	 //  button was pressed.
   	 //--------------------------------------------------------------
   	  public void actionPerformed (ActionEvent event){
   		Object source = event.getSource();

   		if (source == comedy)
   			quote.setText (comedyQuote);
   		else
   			if (source == philosophy)
   				quote.setText (philosophyQuote);
   			else
   				quote.setText (carpentryQuote);
   	}
   }

}

//********************************************************************
//RadioComplex.java       
//
// Another example with radio buttons. Here, however, no action is taken when the 
// buttons are pushed...so there is no action listener(s) registered with the buttons 
// themselves. Instead, the action listener is registered with a button, which determines
// the radio button that has been selected. 
//
// 
// Here, the listener is an inner class, but every radio button share the same
// listener.  So the setActionCommand() method has been used with the buttons so 
// that the button's actionListener can determine which button has been pushed.
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
import javax.swing.JButton;
import javax.swing.JRadioButton;


public class RadioComplex{
	//-----------------------------------------------------------------
	//  Creates and presents the program frame.
	//-----------------------------------------------------------------
	public static void main (String[] args){
		JFrame frame = new JFrame ("Quote Options");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

		QuotePanel panel = new QuotePanel();
		frame.getContentPane().add (panel);

		frame.pack();
		frame.setVisible(true);
	}
}


class QuotePanel extends JPanel{
	
	private static final String COMEDY = "Comedy";
	private static final String PHILOSOPHY = "Philosophy";
	private static final String CARPENTRY = "Carpentry";
	
	private JLabel quote;
	private JButton check;
	private ButtonGroup group;
	private JRadioButton comedy, philosophy, carpentry;
	private String comedyQuote, philosophyQuote, carpentryQuote;

	//-----------------------------------------------------------------
	//  Sets up a panel with a label and a set of radio buttons
	//  that control its text.
	//-----------------------------------------------------------------
	public QuotePanel(){
		comedyQuote = "Take my wife, please.";
		philosophyQuote = "I think, therefore I am.";
		carpentryQuote = "Measure twice. Cut once.";

		quote = new JLabel ("Your quote goes here");
		quote.setFont (new Font ("Helvetica", Font.BOLD, 24));

		// create the radio buttons and set their "action command"
		comedy = new JRadioButton (COMEDY, true);
		comedy.setBackground (Color.green);
		comedy.setActionCommand(COMEDY);
		
		philosophy = new JRadioButton (PHILOSOPHY);
		philosophy.setBackground (Color.green);
		philosophy.setActionCommand(PHILOSOPHY);
		
		carpentry = new JRadioButton (CARPENTRY);
		carpentry.setBackground (Color.green);
		carpentry.setActionCommand(CARPENTRY);

		group = new ButtonGroup();
		group.add (comedy);
		group.add (philosophy);
		group.add (carpentry);

		check = new JButton("Selected?");
		check.addActionListener(new QuoteListener() );
		
  
		add (quote);
		add (comedy);
		add (philosophy);
		add (carpentry);
		add (check);

		setBackground (Color.green);
		setPreferredSize (new Dimension(300, 100));
	}

	//*****************************************************************
	//  Represents the listener for the "check" button
	//*****************************************************************
	private class QuoteListener implements ActionListener{
		//--------------------------------------------------------------
		//  Sets the text of the label depending on which radio
		//  button was pressed.
		//--------------------------------------------------------------
		public void actionPerformed (ActionEvent event){
			// use this line to find out which button has been pushed
			String selectedButton = group.getSelection().getActionCommand();
			
			if (selectedButton == COMEDY)
				quote.setText (comedyQuote);
			else
				if (selectedButton == PHILOSOPHY)
					quote.setText (philosophyQuote);
				else
					quote.setText (carpentryQuote);
		}
	}

}
